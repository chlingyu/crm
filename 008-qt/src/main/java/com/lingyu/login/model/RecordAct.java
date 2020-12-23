package com.lingyu.login.model;

import java.util.Date;

public class RecordAct {
    private String ractid;

    private Integer actid;

    private Integer uid;

    private String state;

    private Date addtime;

    public String getRactid() {
        return ractid;
    }

    public void setRactid(String ractid) {
        this.ractid = ractid;
    }

    public Integer getActid() {
        return actid;
    }

    public void setActid(Integer actid) {
        this.actid = actid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}