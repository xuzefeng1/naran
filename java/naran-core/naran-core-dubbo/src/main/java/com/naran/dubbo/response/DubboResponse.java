package com.naran.dubbo.response;

import java.io.Serializable;

/**
 * DUBBO统一返回封装
 * 
 * @author zefeng.xu
 */
public class DubboResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private DubboResponseCode code = DubboResponseCode.SUCCESS; // 业务执行返回码

    private String msg; // 业务执行返回描述

    private T data; // 业务执行返回对象

    public DubboResponseCode getCode() {
	return code;
    }

    public void setCode(DubboResponseCode code) {
	this.code = code;
    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }

    public T getData() {
	return data;
    }

    public void setData(T data) {
	this.data = data;
    }

}
