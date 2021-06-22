package com.dm.entity;

import com.dm.enums.RequestType;
import com.dm.enums.RequestUrlType;

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
public class CapitalConfigAllEntity extends BaseApiEntity {

    /**
     * 序列化编号
     */
    private static final long serialVersionUID = -5444982934155348390L;

    public CapitalConfigAllEntity() {
        super();
        setPost(false);
        setRequestType(RequestType.USER_DATA);
        setRequestUrlType(RequestUrlType.CAPITAL_CONFIG_GETALL);
    }
}
