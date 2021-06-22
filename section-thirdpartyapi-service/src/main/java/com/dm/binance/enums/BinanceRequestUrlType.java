package com.dm.binance.enums;

import com.dm.enums.BaseRequestUrlType;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
  *@className RequestUrlType
  *@description TODO
  *@author dm
  *@date 2021/6/17 16:03
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Getter
@AllArgsConstructor
public enum BinanceRequestUrlType implements BaseRequestUrlType {

    /**
     * 获取系统状态
     */
    SYSTEM_STATUS("/sapi/v1/system/status", "系统状态"),

    /**
     * 24hr 价格变动情况
     */
    HR_PRICE_UPDATE("/api/v3/ticker/24hr","24hr 价格变动情况"),

    /**
     * 查询历史成交
     */
    HISTORICAL_TRADES("/api/v3/historicalTrades","查询历史成交"),

    /**
     * 获取所有币信息
     */
    CAPITAL_CONFIG_GETALL("/sapi/v1/capital/config/getall","获取所有币信息"),
    ;

    /**
     * 枚举值
     */
    private String typeValue;

    /**
     * 枚举名称
     */
    private String typeName;

}
