package com.zjhl.pad.moudle.entity.base;

/**
 * @desc: RequestBean
 * @version: v1.0
 * @packagename: com.zjhl.pad.entity.base
 * @author: pluto
 * @create: 2018/4/18 11:59
 * @projectname: nnkj
 **/
public class RequestBean<T> {
    /**
     * 请求头默认带的
     */
    public RequestHead header;
    /**
     * 请求的数据，不固定
     */
    public T body;

    public RequestBean() {
        this(null);
    }

    public RequestBean(T body) {
        this.body = body;
        this.header = new RequestHead();
    }


}
