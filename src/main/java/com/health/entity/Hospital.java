package com.health.entity;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName Hospital.java
 * @Description TODO
 * @createTime 2021-11-24 14:42:50
 */
public class Hospital {
    private Integer id;
    private String name;
    private String phone;
    private String level;
    private String imgPath;

    public Hospital() {
    }

    public Hospital(Integer id, String name, String phone, String level) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.level = level;
    }

    public Hospital(Integer id, String name, String phone, String level, String imgPath) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.level = level;
        this.imgPath = imgPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", level='" + level + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
