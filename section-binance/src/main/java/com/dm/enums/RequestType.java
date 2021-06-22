package com.dm.enums;

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
public enum RequestType implements EnumType {

    /**
     * 不需要鉴权的接口
     */
    NONE("NONE", "NONE"),

    /**
     * 需要有效的 API-Key 和签名
     */
    TRADE("TRADE", "TRADE"),

    /**
     * 需要有效的 API-Key 和签名
     */
    MARGIN("MARGIN", "MARGIN"),

    /**
     * 需要有效的 API-Key 和签名
     */
    USER_DATA("USER_DATA", "USER_DATA"),

    /**
     * 需要有效的 API-Key
     */
    USER_STREAM("USER_STREAM", "USER_STREAM"),

    /**
     * 需要有效的 API-Key
     */
    MARKET_DATA("MARKET_DATA", "MARKET_DATA"),;

    /**
     * 枚举值
     */
    private String typeValue;

    /**
     * 枚举名称
     */
    private String typeName;

}
