package com.health.entity;

import java.math.BigDecimal;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName ExpertRegistInfo.java
 * @Description TODO
 * @createTime 2021-12-23 21:01:45
 */
public class ExpertRegistInfo {
    private Integer id;
    private Integer hospitalId;
    private String hospital;
    private String departmentId;
    private String departmentName;
    private Integer doctorId;
    private String doctorName;
    private String sex;
    private String title;
    private Integer total;
    private Integer remain;
    private String registDate;
    private String time;
    private BigDecimal cost;

    public ExpertRegistInfo(Integer id, Integer hospitalId, String hospital,
                            String departmentId, String departmentName, Integer doctorId,
                            String doctorName, String sex, String title, Integer total,
                            Integer remain, String registDate, String time, BigDecimal cost) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.hospital = hospital;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.sex = sex;
        this.title = title;
        this.total = total;
        this.remain = remain;
        this.registDate = registDate;
        this.time = time;
        this.cost = cost;
    }

    public ExpertRegistInfo(Integer id,Integer doctorId,String departmentName,
                            String doctorName,Integer remain,String registDate,
                            String time, BigDecimal cost) {
        this.id = id;
        this.departmentName = departmentName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.remain = remain;
        this.registDate = registDate;
        this.time = time;
        this.cost = cost;
    }

    public ExpertRegistInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ExpertRegistInfo{" +
                "id=" + id +
                ", hospitalId=" + hospitalId +
                ", hospital='" + hospital + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", sex='" + sex + '\'' +
                ", title='" + title + '\'' +
                ", total=" + total +
                ", remain=" + remain +
                ", registDate='" + registDate + '\'' +
                ", time='" + time + '\'' +
                ", cost=" + cost +
                '}';
    }
}
