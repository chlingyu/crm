package com.lingyu.login.model;

import java.util.Date;

public class UserAndComAct {
    private Integer comactid;

    private Float scores;

    private Date addtime;

    private String comcontent;

    private String uname;

    private String avatar;


    public Integer getComactid() {
        return comactid;
    }

    public void setComactid(Integer comactid) {
        this.comactid = comactid;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
