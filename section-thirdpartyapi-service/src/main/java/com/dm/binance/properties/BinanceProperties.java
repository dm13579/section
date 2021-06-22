package com.dm.binance.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *                  ,;,,;
 *                ,;;'(
 *      __      ,;;' ' \
 *   /'  '\'~~'~' \ /'\.)
 * ,;(      )    /  |.
 *,;' \    /-.,,(   ) \
 *     ) /       ) / )|
 *     ||        ||  \)
 *    (_\       (_\
 *@className BinanceProperties
 *@description 币安配置类
 *@author dm
 *@date 2021/6/18 13:48
 *@slogan: 我自横刀向天笑，笑完我就去睡觉
 *@version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "binance")
public class BinanceProperties {

    /**
     * 币安apikey
     */
    public String apiKey;

    /**
     * 币安apiSercurity
     */
    public String secretKey;

    /**
     * 币安基础API接口
     */
    public String baseApi;

    /**
     * 响应时间窗口
     */
    public long receivingWindow;
}
