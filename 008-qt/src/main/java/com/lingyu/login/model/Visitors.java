package com.lingyu.login.model;

import java.util.Date;

public class Visitors {
    private Integer vid;

    private Integer fUid;

    private Integer tUid;

    private Date addtime;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getfUid() {
        return fUid;
    }

    public void setfUid(Integer fUid) {
        this.fUid = fUid;
    }

    public Integer gettUid() {
        return tUid;
    }

    public void settUid(Integer tUid) {
        this.tUid = tUid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}