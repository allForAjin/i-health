package com.health.entity.response;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName Code.java
 * @Description TODO
 * @createTime 2021-12-07 11:13:50
 */
public enum Code {
    /**
     * 成功状态码
     *
     * @Date 2021/12/7 11:17
     */
    SUCCESS(10000, "操作成功"),
    FAIL(20000, "操作失败"),
    EXISTED(30000, "资源已存在");

    private Integer code;
    private String message;

    Code(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    Code(){

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
