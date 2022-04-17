package com.yz.utils.web;



import com.yz.utils.exception.IEnum;
import com.yz.utils.exception.SystemStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;






@Data
@ApiModel("返回结果")

public class ApiResult<T> {

    @ApiModelProperty(value ="状态")
    private int code;

    @ApiModelProperty(value ="信息")
    private String info;

    @ApiModelProperty(value ="对象")
    private T data;


//    public void setInfo(String info) {
//        this.info = info;
//    }
//
//
//    public ApiResult() {
//    }

    public ApiResult(T data) {
        this.code = SystemStatusEnum.SUCCESS.getCode();
        this.info = SystemStatusEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public ApiResult(int code, String msg) {
        this.code = code;
        this.info = msg;
    }

//    public ApiResult(IEnum iEnum) {
//        this.code = iEnum.getCode();
//        this.info = iEnum.getMsg();
//    }
//
//    public ApiResult(IEnum iEnum, T data) {
//        this.code = iEnum.getCode();
//        this.info = iEnum.getMsg();
//        this.data = data;
//    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(data);
    }

//    public static ApiResult<Void> error() {
//        return new ApiResult<>(SystemStatusEnum.UN_KNOW_ERROR);
//    }
//
//    public static ApiResult<Void> error(IEnum iEnum) {
//        return new ApiResult<>(iEnum);
//    }
//
//    public static <T> ApiResult<T> error(T data) {
//        return new ApiResult<>(SystemStatusEnum.BUSINESS_ERROR, data);
//    }
//
//    public static <T> ApiResult<T> error(IEnum iEnum, T data) {
//        return new ApiResult<>(iEnum, data);
//    }

    public static <T> ApiResult<T> error(int code, String msg) {
        return new ApiResult<>(code, msg);
    }
}
/*
*
*
*
@Data
public class ApiResult<T> {

private int code;


    private String info;


    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiResult() {
    }


    public ApiResult(T data) {
        this.code = SystemStatusEnum.SUCCESS.getCode();
        this.info = SystemStatusEnum.SUCCESS.getMsg();

        this.data = Aes.encrypt(JSON.toJSONString(data));
    }

    public ApiResult(int code, String msg) {
        this.code = code;
        this.info = msg;
    }

    public ApiResult(IEnum iEnum) {
        this.code = iEnum.getCode();
        this.info = iEnum.getMsg();
    }

    public ApiResult(IEnum iEnum, T data) {
        this.code = iEnum.getCode();
        this.info = iEnum.getMsg();
        this.data = data;
    }

    public static <T> ApiResult<T> success(T data) {

        return new ApiResult<>(data);
    }


    public static ApiResult<Void> error() {
        return new ApiResult<>(SystemStatusEnum.UN_KNOW_ERROR);
    }

    public static ApiResult<Void> error(IEnum iEnum) {
        return new ApiResult<>(iEnum);
    }

    public static <T> ApiResult<T> error(T data) {
        return new ApiResult<>(SystemStatusEnum.BUSINESS_ERROR, data);
    }

    public static <T> ApiResult<T> error(IEnum iEnum, T data) {
        return new ApiResult<>(iEnum, data);
    }

    public static <T> ApiResult<T> error(int code, String msg) {
        return new ApiResult<>(code, msg);
    }
}
*
*
*
*
*
*
*
* */