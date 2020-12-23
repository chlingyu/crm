package com.lingyu.login.model;

import java.util.Date;

public class User {
    private Integer uid;

    private Date addtime;

    private String phone;

    private String uname;

    private String pwd;

    private Byte vipRank;

    private String avatar;

    private String charm;

    private String openid;

    private Float money;

    private String status;

    private String sex;

    private String isResetPwd;



    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Byte getVipRank() {
        return vipRank;
    }

    public void setVipRank(Byte vipRank) {
        this.vipRank = vipRank;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCharm() {
        return charm;
    }

    public void setCharm(String charm) {
        this.charm = charm;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIsResetPwd() {
        return isResetPwd;
    }

    public void setIsResetPwd(String isResetPwd) {
        this.isResetPwd = isResetPwd;
    }
}