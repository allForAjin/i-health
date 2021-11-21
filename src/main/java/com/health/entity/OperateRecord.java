package com.health.entity;

import java.util.Date;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName OperateRecord.java
 * @Description TODO
 * @createTime 2021-11-20 11:11:31
 */
public class OperateRecord extends Page {
    private Integer id;
    private String username;
    private String ip;
    private String time;
    private String type;

    public static final String LOGIN = "login";
    public static final String REGIST = "regist";
    public static final String LOGOUT = "logout";

    public OperateRecord() {
    }

    public OperateRecord(Integer id, String username, String ip, String time, String type) {
        this.id = id;
        this.username = username;
        this.ip = ip;
        this.time = time;
        this.type = type;
    }

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
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OperateRecord{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
