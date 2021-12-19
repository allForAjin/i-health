package com.health.entity;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName Admin.java
 * @Description TODO
 * @createTime 2021-12-08 21:33:27
 */
public class Admin {
    private Integer id;
    private String username;
    private String name;

    public Admin() {
    }

    public Admin(Integer id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
