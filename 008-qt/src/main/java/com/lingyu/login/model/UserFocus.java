package com.lingyu.login.model;

import java.util.Date;

public class UserFocus {
    private Integer focusid;

    private Integer uid1;

    private Integer uid2;

    private Date addtime;

    public Integer getFocusid() {
        return focusid;
    }

    public void setFocusid(Integer focusid) {
        this.focusid = focusid;
    }

    public Integer getUid1() {
        return uid1;
    }

    public void setUid1(Integer uid1) {
        this.uid1 = uid1;
    }

    public Integer getUid2() {
        return uid2;
    }

    public void setUid2(Integer uid2) {
        this.uid2 = uid2;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}