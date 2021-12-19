package com.health.entity;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName Normal.java
 * @Description TODO
 * @createTime 2021-12-19 10:20:36
 */
public class Normal {
    private Integer id;
    private Integer departmentId;
    private Integer total;
    private Integer remain;
    private String registDate;
    private String time;

    public Normal() {
    }

    public Normal(Integer id, Integer departmentId, Integer total, Integer remain, String registDate, String time) {
        this.id = id;
        this.departmentId = departmentId;
        this.total = total;
        this.remain = remain;
        this.registDate = registDate;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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

    @Override
    public String toString() {
        return "Normal{" +
                "id=" + id +
                ", departmentId=" + departmentId +
                ", total=" + total +
                ", remain=" + remain +
                ", registDate='" + registDate + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
