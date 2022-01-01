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
    private String phone;
    private String name;
    private String sex;
    private Integer age;
    private String birth;

    public Admin() {
    }

    public Admin(Integer id, String phone, String name, String sex, Integer age, String birth) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birth = birth;
    }

    public Admin(Integer id, String phone, String name, String sex, String birth) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birth='" + birth + '\'' +
                '}';
    }
}
