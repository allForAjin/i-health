package com.health.entity;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName Patient.java
 * @Description TODO
 * @createTime 2021-11-15 13:21:28
 */
public class Patient {
    private Integer id;
    private String phone;
    private String name;
    private String sex;
    private Integer age;

    public Patient() {
    }

    public Patient(Integer id, String phone, String name, String sex, Integer age) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
