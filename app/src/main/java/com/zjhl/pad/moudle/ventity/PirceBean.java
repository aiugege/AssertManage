package com.zjhl.pad.moudle.ventity;

/**
 * | 
 * | 功能描述:
 * | 时　　间: 2018/5/3 9:27
 * | 代码创建: Pluto
 * | 版本信息: V1.0.0
 * | 代码修改:（修改人 - 修改时间）
 **/
public class PirceBean implements BaseFilter{
    /**
     * 时间str
     */
    String timeStr;
    /**
     * 时间事件
     */
    String timeEvent;

    public PirceBean(String timeStr, String timeEvent) {
        this.timeStr = timeStr;
        this.timeEvent = timeEvent;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(String timeEvent) {
        this.timeEvent = timeEvent;
    }

    @Override
    public String getFilterStr() {
        return timeStr;
    }
}
