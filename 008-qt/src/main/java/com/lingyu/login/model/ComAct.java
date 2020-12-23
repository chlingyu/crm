package com.lingyu.login.model;

import java.util.Date;

public class ComAct {
    private Integer comactid;

    private Integer actid;

    private Integer uid;

    private Float scores;

    private Date addtime;

    private String comcontent;

    public Integer getComactid() {
        return comactid;
    }

    public void setComactid(Integer comactid) {
        this.comactid = comactid;
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

    public Float getScores() {
        return scores;
    }

    public void setScores(Float scores) {
        this.scores = scores;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getComcontent() {
        return comcontent;
    }

    public void setComcontent(String comcontent) {
        this.comcontent = comcontent;
    }
}