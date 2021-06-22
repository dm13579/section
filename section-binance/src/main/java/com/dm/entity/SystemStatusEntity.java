package com.dm.entity;

import com.dm.enums.RequestType;
import com.dm.enums.RequestUrlType;
import lombok.Builder;
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
  *@className SystemStatusEntity
  *@description 系统状态入参实例
  *@author dm
  *@date 2021/6/17 16:56
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
public class SystemStatusEntity extends BaseApiEntity {

    /**
     * 序列化版本编号
     */
    private static final long serialVersionUID = -1476371174012279999L;

    public SystemStatusEntity() {
        super();
        setPost(false);
        setRequestType(RequestType.NONE);
        setRequestUrlType(RequestUrlType.SYSTEM_STATUS);
    }
}
