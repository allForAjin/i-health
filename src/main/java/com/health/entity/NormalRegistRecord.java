package com.health.entity;

import java.math.BigDecimal;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName NormalRegistRecord.java
 * @Description TODO
 * @createTime 2021-11-28 14:31:09
 */
public class NormalRegistRecord extends Page{
    private Integer patientId;
    private Integer normalId;
    private BigDecimal cost;
    private String registDate;
    private String time;
    private String department;
    private String hospital;
    private String operateTime;
    private Integer payStatus = 0;
    private String patientName;
    private String phone;
    private String sex;
    private Integer age;
    private String orderId;

    public NormalRegistRecord() {
    }

    public NormalRegistRecord(Integer patientId, Integer normalId, BigDecimal cost, String registDate,
                              String time, String department, String hospital, String operateTime,
                              Integer payStatus) {
        this.patientId = patientId;
        this.normalId = normalId;
        this.cost = cost;
        this.registDate = registDate;
        this.time = time;
        this.department = department;
        this.hospital = hospital;
        this.operateTime = operateTime;
        if (payStatus!=null){
            this.payStatus = payStatus;
        }
    }

    public NormalRegistRecord(Integer patientId, Integer normalId,
                              BigDecimal cost, String registDate,
                              String time, String department, String hospital,
                              String operateTime, Integer payStatus,
                              String patientName, String phone, String sex,
                              Integer age, String orderId) {
        this.patientId = patientId;
        this.normalId = normalId;
        this.cost = cost;
        this.registDate = registDate;
        this.time = time;
        this.department = department;
        this.hospital = hospital;
        this.operateTime = operateTime;
        this.payStatus = payStatus;
        this.patientName = patientName;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
        this.orderId = orderId;
    }

    public NormalRegistRecord(Integer patientId, Integer normalId, BigDecimal cost,
                              String registDate, String time, String department,
                              String hospital, String operateTime, Integer payStatus,
                              String patientName, String phone,
                              String sex, Integer age) {
        this.patientId = patientId;
        this.normalId = normalId;
        this.cost = cost;
        this.registDate = registDate;
        this.time = time;
        this.department = department;
        this.hospital = hospital;
        this.operateTime = operateTime;
        this.payStatus = payStatus;
        this.patientName = patientName;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
    }





    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getNormalId() {
        return normalId;
    }

    public void setNormalId(Integer normalId) {
        this.normalId = normalId;
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

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        if (payStatus!=null){
            this.payStatus = payStatus;
        }
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "NormalRegistRecord{" +
                "patientId=" + patientId +
                ", normalId=" + normalId +
                ", cost=" + cost +
                ", registDate='" + registDate + '\'' +
                ", time='" + time + '\'' +
                ", department='" + department + '\'' +
                ", hospital='" + hospital + '\'' +
                ", operateTime='" + operateTime + '\'' +
                ", payStatus=" + payStatus +
                ", patientName='" + patientName + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", orderId=" + orderId +
                '}';
    }
}
