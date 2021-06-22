package com.dm.entity;

import com.dm.enums.RequestType;
import com.dm.enums.RequestUrlType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * ,;,,;
 * ,;;'(
 * __      ,;;' ' \
 * /'  '\'~~'~' \ /'\.)
 * ,;(      )    /  |.
 * ,;' \    /-.,,(   ) \
 * ) /       ) / )|
 * ||        ||  \)
 * (_\       (_\
 *
 * @author dm
 * @version 1.0
 * @className BaseApiParam
 * @description 基础API实例
 * @date 2021/6/17 16:54
 * @slogan: 我自横刀向天笑，笑完我就去睡觉
 **/
@Data
public class BaseApiEntity implements Serializable {

    /**
     * 序列化编号
     */
    private static final long serialVersionUID = 3066672189020781496L;

    /**
     * 访问链接
     */
    private String url = null;

    /**
     * POST请求
     */
    private Boolean post;

    /**
     * 访问连接类型
     */
    private RequestUrlType requestUrlType;

    /**
     * 请求类型
     */
    private RequestType requestType;

//    public BaseApiEntity(Boolean post, RequestUrlType requestUrlType, RequestType requestType) {
//        this.post = post;
//        this.requestUrlType = requestUrlType;
//        this.requestType = requestType;
//    }

}
