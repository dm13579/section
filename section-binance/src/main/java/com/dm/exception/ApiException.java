package com.dm.exception;

import com.dm.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
  *@className BusinessException
  *@cescription 业务异常逻辑
  *@author dm
  *@date 2021/5/26 17:14
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ApiException extends Exception {

    /**
     * 序列化版本编号
     */
    private static final long serialVersionUID = -7639262485980659320L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误消息
     */
    private String message;

    public ApiException() {
        this.code = ResultCode.ERROR.getTypeValue();
        this.message = ResultCode.ERROR.getTypeName();
    }

    public ApiException(String message) {
        this.code = ResultCode.ERROR.getTypeValue();
        this.message = message;
    }

    public ApiException(ResultCode resultCode) {
        this.code = resultCode.getTypeValue();
        this.message = resultCode.getTypeName();
    }
}
