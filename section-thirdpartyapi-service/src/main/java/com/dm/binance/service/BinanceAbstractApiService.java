package com.dm.binance.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dm.binance.entity.BaseBinanceApiEntity;
import com.dm.binance.enums.RequestType;
import com.dm.binance.properties.BinanceProperties;
import com.dm.service.BaseAbstractApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
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
public abstract class BinanceAbstractApiService<E extends BaseBinanceApiEntity> extends BaseAbstractApiService<E> {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private BinanceProperties binanceProperties;

    /**
     * API数据获取
     */
    @Override
    protected JSONObject handlerParam(E entity) {
        // 链接参数处理
        entity.setUrl(binanceProperties.getBaseApi() + entity.getRequestUrlType().getTypeValue());

        JSONObject invokeEntity = (JSONObject) JSONObject.parse(JSON.toJSONString(entity));

        // 移除通用api参数
        invokeEntity.remove("url");
        invokeEntity.remove("post");
        invokeEntity.remove("requestUrlType");
        invokeEntity.remove("requestType");

        return invokeEntity;
    }

    @Override
    protected String handlerRequest(BaseBinanceApiEntity entity, JSONObject invokeEntity) {
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
            return response.getBody();
        }
    }

    private void handlerRequestType(BaseBinanceApiEntity entity, HttpHeaders headers, JSONObject invokeEntity) {
        // 需要有效的 API-Key
        if (entity.getRequestType().equals(RequestType.USER_STREAM)
                || entity.getRequestType().equals(RequestType.MARKET_DATA)) {
            headers.add("X-MBX-APIKEY", binanceProperties.apiKey);
            // 需要有效的 API-Key 和签名
        } else if (entity.getRequestType().equals(RequestType.TRADE)
                || entity.getRequestType().equals(RequestType.MARGIN)
                || entity.getRequestType().equals(RequestType.USER_DATA)) {
            headers.add("X-MBX-APIKEY", binanceProperties.apiKey);

            Mac hmacSha256 = null;
            try {
                hmacSha256 = Mac.getInstance("HmacSHA256");
                SecretKeySpec secKey = new SecretKeySpec(binanceProperties.secretKey.getBytes(), "HmacSHA256");
                hmacSha256.init(secKey);
            } catch (Exception e) {
                log.error("[Signature] No such algorithm:");
            }
            // 添加参数
            invokeEntity.put("recvWindow", Long.toString(binanceProperties.receivingWindow));
            invokeEntity.put("timestamp", Long.toString(System.currentTimeMillis()));

            String signature = new String(Hex.encodeHex(hmacSha256.doFinal(getParamStrUrlEncode(invokeEntity).getBytes())));
            invokeEntity.put("signature", signature);
        }
    }
}
