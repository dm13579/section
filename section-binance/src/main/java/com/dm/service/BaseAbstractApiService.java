package com.dm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dm.config.BinanceProperties;
import com.dm.entity.BaseApiEntity;
import com.dm.enums.RequestType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * ,;,,;
 * ,;;'(
 * __      ,;;' ' \
 * /'  '\'~~'~' \ /'\.)
 * ,;(      )    /  |.
 * ,;' \    /-.,,(   ) \
 * ) /       ) / )|
 * ||        ||  \)
 * (_\       (_\
 *
 * @author dm
 * @version 1.0
 * @className BaseAbstractApiService
 * @description TODO
 * @date 2021/6/17 17:37
 * @slogan: 我自横刀向天笑，笑完我就去睡觉
 **/
@Slf4j
public abstract class BaseAbstractApiService<E extends BaseApiEntity> {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private BinanceProperties securityProperties;

    /**
     * API数据获取
     */
    public Object getData(E entity) {
        // 数据API调用
        return syncData(entity, getParam(entity));
    }

    /**
     * 业务参数生成
     *
     * @param entity 参数
     * @return 请求参数
     */
    protected JSONObject getParam(E entity) {

        // 链接参数处理
        entity.setUrl(securityProperties.getBaseApi() + entity.getRequestUrlType().getTypeValue());

        JSONObject invokeEntity = (JSONObject) JSONObject.parse(JSON.toJSONString(entity));

        // 移除通用api参数
        invokeEntity.remove("url");
        invokeEntity.remove("post");
        invokeEntity.remove("requestUrlType");
        invokeEntity.remove("requestType");

        return invokeEntity;
    }

    /**
     * API接口处理结果解析
     *
     * @param result      处理返回结果
     * @param requestInfo 访问信息
     * @return 处理返回结果
     */
    protected Object getResult(String result, String requestInfo) {
        log.info("========result=========" + result);
        log.info("========requestInfo=========" + requestInfo);
        return JSON.parse(result);
    }

    /**
     * 数据获取
     *
     * @param entity       API调用参数
     * @param invokeEntity 业务参数
     * @return 返回数据
     */
    private Object syncData(BaseApiEntity entity, JSONObject invokeEntity) {

        // 链接地址
        if (StringUtils.isEmpty(entity.getUrl())) {
            log.error("API接口链接为空");
        }

        // 请求返回的结果
        String result = handlerRequest(entity, invokeEntity);

        // 访问信息
        String requestInfo = getRequestLogMsg(entity, invokeEntity, result);

        // API调用日志记录
        log.error(requestInfo);

        // 访问数据结果返回
        return getResult(result, requestInfo);
    }

    protected String handlerRequest(BaseApiEntity entity, JSONObject invokeEntity) {
        // 处理post请求
        if (entity.getPost()) {
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            handlerRequestType(entity, headers, invokeEntity);
            HttpEntity<String> formEntity = new HttpEntity<>(JSON.toJSONString(invokeEntity), headers);
            return restTemplate.postForObject(entity.getUrl(), formEntity, String.class);
            // 处理get请求
        } else {
            HttpHeaders headers = new HttpHeaders();
            handlerRequestType(entity, headers, invokeEntity);
            HttpEntity request = new HttpEntity(headers);

            // 构建queryParam
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(entity.getUrl());
            for (Map.Entry<String, Object> entry : getParamMap(invokeEntity).entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }

            HttpEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, String.class);
//            ResponseEntity<String> exchange = restTemplate.exchange(entity.getUrl(), HttpMethod.GET, request, String.class, getParamMap(invokeEntity));
            return response.getBody();
        }
    }

    private void handlerRequestType(BaseApiEntity entity, HttpHeaders headers, JSONObject invokeEntity) {
        // 需要有效的 API-Key
        if (entity.getRequestType().equals(RequestType.USER_STREAM)
                || entity.getRequestType().equals(RequestType.MARKET_DATA)) {
            headers.add("X-MBX-APIKEY", securityProperties.apiKey);
            // 需要有效的 API-Key 和签名
        } else if (entity.getRequestType().equals(RequestType.TRADE)
                || entity.getRequestType().equals(RequestType.MARGIN)
                || entity.getRequestType().equals(RequestType.USER_DATA)) {
            headers.add("X-MBX-APIKEY", securityProperties.apiKey);

            Mac hmacSha256 = null;
            try {
                hmacSha256 = Mac.getInstance("HmacSHA256");
                SecretKeySpec secKey = new SecretKeySpec(securityProperties.secretKey.getBytes(), "HmacSHA256");
                hmacSha256.init(secKey);
            } catch (Exception e) {
                log.error("[Signature] No such algorithm:");
            }
            // 添加参数
            invokeEntity.put("recvWindow", Long.toString(securityProperties.receivingWindow));
            invokeEntity.put("timestamp", Long.toString(System.currentTimeMillis()));

            String signature = new String(Hex.encodeHex(hmacSha256.doFinal(getParamStrUrlEncode(invokeEntity).getBytes())));
            invokeEntity.put("signature", signature);



        }
    }

    /**
     * JSON参数转字符串参数URL编码
     *
     * @param entity JSON参数
     * @return 字符串参数
     */
    protected final String getParamStrUrlEncode(JSONObject entity) {

        String addChart = "&";
        String equChart = "=";

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : entity.entrySet()) {
//            sb.append(addChart).append(urlEncoder(entry.getKey(), "UTF-8")).append(equChart).append(urlEncoder(entry.getValue().toString(), "UTF-8"));
            sb.append(addChart).append(entry.getKey()).append(equChart).append(entry.getValue().toString());
        }

        // 字符串参数返回
        return sb.toString();
    }

    /**
     * JSON参数转Map
     *
     * @param entity JSON参数
     * @return 字符串参数
     */
    protected final Map<String, Object> getParamMap(JSONObject entity) {

        Map<String, Object> map = new HashMap<>(32);
        for (Map.Entry<String, Object> entry : entity.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }

        // 字符串参数返回
        return map;
    }

//    /**
//     * 文本URL编码
//     *
//     * @entity value   文本
//     * @entity charset 字符集
//     * @return 编码结果
//     */
//    protected final String urlEncoder(String value, String charset) {
//
//        try {
//            // 文本URL编码
//            return URLEncoder.encode(value, charset);
//            // 发生异常
//        } catch (UnsupportedEncodingException e) {
//            // API调用日志记录
//            log.error("URL编码失败，编码值：" + value + ";编码集合：" + charset, e);
//            return "";
//        }
//    }

    /**
     * API调用日志信息获取
     *
     * @param entity       原始参数
     * @param invokeEntity JSON参数
     * @param result       返回结果
     * @return 处理返回结果
     */
    protected final String getRequestLogMsg(BaseApiEntity entity, JSONObject invokeEntity, String result) {
        // API调用日志拼接
        return "API调用信息记录：链接地址：" + entity.getUrl() + "；访问参数：" + invokeEntity + "；返回结果：" + result;
    }
}
