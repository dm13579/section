package com.dm.binance.entity;

import com.dm.binance.enums.RequestType;
import com.dm.binance.enums.BinanceRequestUrlType;
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
  *@className HistoricalTradesEntity
  *@description TODO
  *@author dm
  *@date 2021/6/21 15:54
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Data
public class HistoricalTradesEntity extends BaseBinanceApiEntity {

    /**
     * 序列化编号
     */
    private static final long serialVersionUID = 7488196352882877605L;

    /**
     * 交易对 例如BNBBTC
     */
    private String symbol;

    /**
     * 默认值:500 最大值:1000.(非必填)
     */
    private Integer limit;

    /**
     * 从哪一条成交id开始返回. 缺省返回最近的成交记录非必填
     */
    private Long fromId;

    public HistoricalTradesEntity() {
        super();
        setPost(false);
        setRequestType(RequestType.MARKET_DATA);
        setRequestUrlType(BinanceRequestUrlType.HISTORICAL_TRADES);
    }
}
