package com.dm.binance.entity;

import com.dm.binance.enums.RequestType;
import com.dm.entity.BaseApiEntity;
import lombok.Data;

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
  *@className BaseBinanceApiEntity
  *@description TODO
  *@author dm
  *@date 2021/6/22 15:24
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Data
public class BaseBinanceApiEntity extends BaseApiEntity {

    /**
     * 序列化版本编号
     */
    private static final long serialVersionUID = -2093069510824240137L;

    /**
     * 请求类型
     */
    private RequestType requestType;
}
