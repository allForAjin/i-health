package com.health.entity.response;

/**
 * @author lmk
 * @version 1.0.0
 * @ClassName ResponseResult.java
 * @Description TODO
 * @createTime 2021-12-07 11:11:58
 */
public class ResponseResult<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseResult(Code code,T data){
        this.code=code.getCode();
        this.message=code.getMessage();
        this.data=data;
    }

    public ResponseResult(){

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
