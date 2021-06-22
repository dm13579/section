package com.dm.binance.entity;

import com.dm.binance.enums.BinanceRequestUrlType;
import com.dm.binance.enums.RequestType;
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
  *@className CapitalConfigAllEntity
  *@description TODO
  *@author dm
  *@date 2021/6/21 17:53
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Data
public class CapitalConfigAllEntity extends BaseBinanceApiEntity {

    /**
     * 序列化编号
     */
    private static final long serialVersionUID = -5444982934155348390L;

    public CapitalConfigAllEntity() {
        super();
        setPost(false);
        setRequestType(RequestType.USER_DATA);
        setRequestUrlType(BinanceRequestUrlType.CAPITAL_CONFIG_GETALL);
    }
}
