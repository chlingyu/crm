package com.lingyu.login.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserInfo {
    private Integer infoid;

    private Integer uid;

    private Byte age;

    private String sex;

    private String address;

    private String height;

    private String income;

    private String education;

    private String weight;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private String marry;

    private String workArea;

    private String hasChild;

    private String wantChild;

    private String isSmoke;

    private String isDrink;

    public Integer getInfoid() {
        return infoid;
    }

    public void setInfoid(Integer infoid) {
        this.infoid = infoid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild;
    }

    public String getWantChild() {
        return wantChild;
    }

    public void setWantChild(String wantChild) {
        this.wantChild = wantChild;
    }

    public String getIsSmoke() {
        return isSmoke;
    }

    public void setIsSmoke(String isSmoke) {
        this.isSmoke = isSmoke;
    }

    public String getIsDrink() {
        return isDrink;
    }

    public void setIsDrink(String isDrink) {
        this.isDrink = isDrink;
    }
}