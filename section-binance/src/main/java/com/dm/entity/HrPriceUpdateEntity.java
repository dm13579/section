package com.dm.entity;

import com.dm.enums.RequestType;
import com.dm.enums.RequestUrlType;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
  *@className HrPriceUpdateEntity
  *@description 24hr行情价格变动入参实例
  *@author dm
  *@date 2021/6/18 17:17
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Data
public class HrPriceUpdateEntity extends BaseApiEntity {

    /**
     * 序列化编号
     */
    private static final long serialVersionUID = -7226960218907117829L;

    /**
     * 交易对 例如BNBBTC
     */
    private String symbol;

    public HrPriceUpdateEntity() {
        super();
        setPost(false);
        setRequestType(RequestType.NONE);
        setRequestUrlType(RequestUrlType.HR_PRICE_UPDATE);
    }

}



