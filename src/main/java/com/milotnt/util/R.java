package com.milotnt.util;

import com.milotnt.consts.ConstsEnum;
import lombok.Data;

import java.util.Map;

@Data
public class R {

    private Integer code;

    private String msg;

    private Object data;

    public static Object OK(Object data) {
        R r = new R();
        r.code = ConstsEnum.SUCCESS.getCode();
        r.msg = ConstsEnum.SUCCESS.getMsg();
        r.data = data;
        return r;
    }


    public static Object Fail(ConstsEnum e) {
        R r = new R();
        r.code = e.getCode();
        r.msg = e.getMsg();
        return r;
    }
}
