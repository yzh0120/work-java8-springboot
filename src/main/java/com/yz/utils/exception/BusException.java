package com.yz.utils.exception;

public class BusException extends RuntimeException  implements IEnum{
    private int    code;
    private String msg;

    public BusException(String msg) {
        super(msg);
        this.code = SystemStatusEnum.BUSINESS_ERROR.getCode();
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

