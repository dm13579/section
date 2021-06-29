package com.dm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dm.entity.BaseApiEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

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
 * @date 2021/6/22 15:34
 * @slogan: 我自横刀向天笑，笑完我就去睡觉
 **/
@Slf4j
@Data
public abstract class BaseAbstractApiService<E extends BaseApiEntity> {

    /**
     * API数据获取
     *
     * @param entity 参数
     */
    public Object invoke(E entity) {
        // 数据API调用
        return invoke(entity, handlerParam(entity));
    }

    /**
     * 处理传入参数（可以包括数据校验，参数基本字段剔除）
     *
     * @param entity 参数
     * @return 请求参数
     */
    protected abstract JSONObject handlerParam(E entity);

    /**
     * API接口处理结果解析
     *
     * @param result      处理返回结果
     * @param requestInfo 访问信息
     * @return 处理返回结果
     */
    protected abstract Object handlerResult(String result, String requestInfo);

    /**
     * 数据获取
     *
     * @param entity       API调用参数
     * @param invokeEntity 业务参数
     * @return 返回数据
     */
    private Object invoke(E entity, JSONObject invokeEntity) {

        // 链接地址
        if (StringUtils.isEmpty(entity.getUrl())) {
            log.error("API接口链接为空");
        }

        // 请求返回的结果
        String result = handlerRequest(entity, invokeEntity);

        // 处理访问数据结果
        return handlerResult(result, getRequestLogMsg(entity, invokeEntity, result));
    }

    /**
     * 处理请求
     *
     * @param entity       API调用参数
     * @param invokeEntity 业务参数
     * @return 返回数据
     */
    protected abstract String handlerRequest(E entity, JSONObject invokeEntity);

    /**
     * JSON参数转字符串参数URL编码
     *
     * @param entity JSON参数
     * @return 字符串参数
     */
    protected String getParamStrUrlEncode(JSONObject entity) {

        String addChart = "&";
        String equChart = "=";

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : entity.entrySet()) {
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
    protected Map<String, Object> getParamMap(JSONObject entity) {

        Map<String, Object> map = new HashMap<>(32);
        for (Map.Entry<String, Object> entry : entity.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }

        // 字符串参数返回
        return map;
    }

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
