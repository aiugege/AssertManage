package com.zjhl.pad.view.listener;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

/**
 * @desc: OnBaseActivityListener
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.listener
 * @author: pluto
 * @create: 2018/6/6 19:29
 * @projectname: nnkj
 **/
public interface OnBaseActivityListener{
    void positive(ResponseBean responseBean, String isSelect);
    void negative(String isSelect);
}
