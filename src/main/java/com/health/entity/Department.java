package com.health.entity;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName Department.java
 * @Description TODO
 * @createTime 2021-11-24 16:55:01
 */
public class Department {
    private Integer id;
    private String name;
    private String hospitalName;

    public Department() {
    }

    public Department(Integer id, String name, String hospitalName) {
        this.id = id;
        this.name = name;
        this.hospitalName = hospitalName;
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

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                '}';
    }
}
