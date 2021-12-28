package com.health.entity;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName Expert.java
 * @Description TODO
 * @createTime 2021-12-23 21:04:22
 */
public class Expert {
    private Integer id;
    private String phone;
    private String name;
    private String sex;
    private String birth;
    private Integer departmentId;
    private String career;
    private String description;
    private Integer registCount;
    private String imgPath;

    public Expert() {
    }

    public Expert(Integer id, String phone, String name, String sex, String birth, Integer departmentId, String career, String description, Integer registCount, String imgPath) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.departmentId = departmentId;
        this.career = career;
        this.description = description;
        this.registCount = registCount;
        this.imgPath = imgPath;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRegistCount() {
        return registCount;
    }

    public void setRegistCount(Integer registCount) {
        this.registCount = registCount;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Expert{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", departmentId=" + departmentId +
                ", career='" + career + '\'' +
                ", description='" + description + '\'' +
                ", registCount='" + registCount + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
