package com.zjhl.pad.moudle.ventity;

/**
 *//**
  * | 
  * | 功能描述:
  * | 时　　间: 2018/5/3 10:08
  * | 代码创建: Pluto
  * | 版本信息: V1.0.0
  * | 代码修改:（修改人 - 修改时间）
  **/
public class SynshesizeBean implements BaseFilter {
    /**
     * 显示的name
     */
    private String showName;

    /**
     * 构造方法
     * @param showName
     */
    public SynshesizeBean(String showName) {
        this.showName = showName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    /**
     * 筛选的Str
     *
     * @return
     */
    @Override
    public String getFilterStr() {
        return showName;
    }
}
