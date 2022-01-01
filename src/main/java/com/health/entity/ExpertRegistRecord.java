package com.health.entity;

import java.math.BigDecimal;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName ExpertRegistRecord.java
 * @Description TODO
 * @createTime 2021-12-27 16:37:45
 */
public class ExpertRegistRecord {
    private Integer id;
    private String phone;
    private String orderId;
    private String patientName;
    private String sex;
    private Integer age;
    private BigDecimal cost;
    private String registDate;
    private String time;
    private String department;
    private String hospital;
    private Integer payStatus = 0;
    private String operateTime;
    private Integer patientId;
    private Integer expertId;
    private String expertName;


    public ExpertRegistRecord() {
    }

    public ExpertRegistRecord(Integer id, String phone, String orderId, String patientName, String sex, Integer age, BigDecimal cost, String registDate, String time, String department, String hospital, Integer payStatus, String operateTime, Integer patientId, Integer expertId, String expertName) {
        this.id = id;
        this.phone = phone;
        this.orderId = orderId;
        this.patientName = patientName;
        this.sex = sex;
        this.age = age;
        this.cost = cost;
        this.registDate = registDate;
        this.time = time;
        this.department = department;
        this.hospital = hospital;
        this.payStatus = payStatus;
        this.operateTime = operateTime;
        this.patientId = patientId;
        this.expertId = expertId;
        this.expertName = expertName;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    @Override
    public String toString() {
        return "ExpertRegistRecord{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", orderId='" + orderId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", cost=" + cost +
                ", registDate='" + registDate + '\'' +
                ", time='" + time + '\'' +
                ", department='" + department + '\'' +
                ", hospital='" + hospital + '\'' +
                ", payStatus=" + payStatus +
                ", operateTime='" + operateTime + '\'' +
                ", patientId=" + patientId +
                ", expertId=" + expertId +
                ", expertName='" + expertName + '\'' +
                '}';
    }
}
