package com.gov.xmxx.pojo;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String islockout;

    private String lastlogintime;

    private String lastloginip;

    private Integer psdwrongtime;

    private String locktime;

    private String createtime;

    private String telephone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIslockout() {
        return islockout;
    }

    public void setIslockout(String islockout) {
        this.islockout = islockout == null ? null : islockout.trim();
    }

    public String getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(String lastlogintime) {
        this.lastlogintime = lastlogintime == null ? null : lastlogintime.trim();
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip == null ? null : lastloginip.trim();
    }

    public Integer getPsdwrongtime() {
        return psdwrongtime;
    }

    public void setPsdwrongtime(Integer psdwrongtime) {
        this.psdwrongtime = psdwrongtime;
    }

    public String getLocktime() {
        return locktime;
    }

    public void setLocktime(String locktime) {
        this.locktime = locktime == null ? null : locktime.trim();
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", islockout='" + islockout + '\'' +
                ", lastlogintime='" + lastlogintime + '\'' +
                ", lastloginip='" + lastloginip + '\'' +
                ", psdwrongtime=" + psdwrongtime +
                ", locktime='" + locktime + '\'' +
                ", createtime='" + createtime + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }
}