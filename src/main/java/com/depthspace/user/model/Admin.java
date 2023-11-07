package com.depthspace.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Admin {
    private Integer userId;
    private String adminAcc;

    @JsonIgnore
    private String adminPwd;

    private Date createdDate;
    private Date lastModifiedDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return adminAcc;
    }

    public void setEmail(String email) {
        this.adminAcc = email;
    }

    public String getPassword() {
        return adminPwd;
    }

    public void setPassword(String password) {
        this.adminPwd = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}