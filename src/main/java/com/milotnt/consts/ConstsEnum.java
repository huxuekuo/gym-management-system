package com.milotnt.consts;

public enum ConstsEnum {

    SUCCESS(10001,"OK"),
    MEMBER_NOT_EXIST(10002,"会员不存在！");

    int code;
    String msg;

    ConstsEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
