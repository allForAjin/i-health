package com.health.entity;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName PayInfo.java
 * @Description TODO
 * @createTime 2021-12-01 17:21:42
 */
public class PayInfo {
    private Integer id;
    private String phone;
    private String cost;
    private String hospital;
    private String department;
    private String registDate;
    private String time;

    public PayInfo() {
    }

    public PayInfo(Integer id, String phone, String cost,
                   String hospital, String department, String registDate,
                   String time) {
        this.id = id;
        this.phone = phone;
        this.cost = cost;
        this.hospital = hospital;
        this.department = department;
        this.registDate = registDate;
        this.time = time;
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PayInfo{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", cost='" + cost + '\'' +
                ", hospital='" + hospital + '\'' +
                ", department='" + department + '\'' +
                ", registDate='" + registDate + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
