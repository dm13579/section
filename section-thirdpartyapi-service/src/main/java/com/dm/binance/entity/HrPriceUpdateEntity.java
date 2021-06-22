package com.dm.binance.entity;

import com.dm.binance.enums.RequestType;
import com.dm.binance.enums.BinanceRequestUrlType;
import lombok.*;

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
public class HrPriceUpdateEntity extends BaseBinanceApiEntity {

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
        setRequestUrlType(BinanceRequestUrlType.HR_PRICE_UPDATE);
    }

}



