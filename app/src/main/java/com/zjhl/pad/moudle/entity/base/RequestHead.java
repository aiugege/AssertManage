package com.zjhl.pad.moudle.entity.base;

/**
 * @desc: RequestHead
 * @version: v1.0
 * @packagename: com.zjhl.pad.entity.base
 * @author: pluto
 * @create: 2018/4/18 11:59
 * @projectname: nnkj
 **/
public class RequestHead {
    private String channel = "3";
    private String language = "cn";
    private String token = "";
    private String currentId = "";

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    @Override
    public String toString() {
        return "RequestHead{" +
                "channel='" + channel + '\'' +
                ", language='" + language + '\'' +
                ", token='" + token + '\'' +
                ", currentId='" + currentId + '\'' +
                '}';
    }
}
