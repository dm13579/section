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
  *@className ResultCode
  *@cescription 返回结果code
  *@author dm
  *@date 2021/5/26 17:05
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Getter
@AllArgsConstructor
public enum ResultCode implements EnumType {

    /**
     * 操作成功
     */
    SUCCESS("200", "操作成功"),

    /**
     * 操作失败
     */
    ERROR("500", "操作失败"),
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
