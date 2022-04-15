package com.yz.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemStatusEnum implements IEnum{
    SUCCESS(200, "成功"),
    BAD_REQUEST(400, "请求错误"),
    UNAUTHORIZED(401, "没有权限"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "请求未找到"),
    METHOD_NOT_ALLOWED(405, "不支持的请求方法"),
    PROXY_AUTHENTICATION_REQUIRED(407, "需要代理认证"),
    REQUEST_TIMEOUT(408, "请求超时"),
    NPE(10022, "空指针异常"),
    LOGIN_ERROR(10030, "登录失败："),
    BUSINESS_ERROR(10040, "业务异常"),
    UN_KNOW_ERROR(10050, "系统异常"),
    SYSTEM_ERROR(10060, "系统异常"),
    PARAM_MISSING(10070, "参数缺失"),
    PARAM_ERROR(10071, "参数不正确"),
    PARAM_ILLEGAL_ERROR(10072, "参数非法"),
    PARAM_BINDING_ERROR(10073, "参数绑定错误"),
    REQUEST_CONVERT_ERROR(10080, "请求转换错误"),
    ENCRYPT_ERROR(10090, "加解密异常"),
    CACHE_ERROR(10110, "缓存异常"),
    SQL_ERROR(10129, "SQL异常"),
    DATABASE_ERROR(10130, "数据库异常"),
    MAXIMUM_UPLOAD_SIZE_ERROR(10140, "上传文件超出限制"),
    COMPANY_FORBID_PASS(10001, "企业未准入或者准入未通过"),
    ACCEPTANCE_NOT_EXIST(10003, "授信不存在"),
    ACCEPTANCE_NOT_AUDITING(10004, "授信审核中"),
    ACCEPTANCE_NOT_PASS(10004, "授信审核不通过"),
    ACCEPTANCE_EXPIRE(10002, "授信已过期"),
    ACCEPTANCE_AMOUNT_LESS(10002, "授信额度不足"),
    ;

    private final int code;
    private final String msg;
}
