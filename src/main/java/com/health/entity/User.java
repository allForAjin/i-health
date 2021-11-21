package com.health.entity;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2021-11-15 19:26:05
 */
public class User {
    public static final String PATIENT="patient";
    public static final String DOCTOR="doctor";
    public static final String ADMIN="admin";

    private Integer id;
    private String username;
    private String password;
    private String type=User.PATIENT;

    public User() {
    }

    public User(Integer id, String username, String password, String type) {
        this.id = id;
        this.username = username;
        this.password = password;
        if (type!=null){
            this.type = type;
        }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type!=null){
            this.type = type;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
