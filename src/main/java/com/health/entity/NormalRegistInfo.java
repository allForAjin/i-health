package com.health.entity;

import java.math.BigDecimal;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName NormalRegistInfo.java
 * @Description TODO
 * @createTime 2021-11-25 18:37:00
 */
public class NormalRegistInfo extends Page{
    private Integer id;
    private Integer hospitalId;
    private String hospitalName;
    private String level;
    private Integer departmentId;
    private String departmentName;
    private Integer total;
    private Integer remain;
    private String registDate;
    private String time;
    private BigDecimal cost;

    public NormalRegistInfo() {
    }

    public NormalRegistInfo(Integer id, Integer hospitalId, String hospitalName, String level,
                            Integer departmentId, String departmentName, Integer total,
                            Integer remain, String registDate, String time, BigDecimal cost) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.level = level;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.total = total;
        this.remain = remain;
        this.registDate = registDate;
        this.time = time;
        this.cost = cost;
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

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
        return "NormalRegistInfo{" +
                "id=" + id +
                ", hospitalId=" + hospitalId +
                ", hospitalName='" + hospitalName + '\'' +
                ", level='" + level + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", total=" + total +
                ", remain=" + remain +
                ", registDate='" + registDate + '\'' +
                ", time='" + time + '\'' +
                ", cost=" + cost +
                '}';
    }
}
