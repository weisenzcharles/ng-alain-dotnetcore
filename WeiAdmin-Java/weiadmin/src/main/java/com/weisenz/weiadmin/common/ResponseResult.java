package com.weisenz.weiadmin.common;

import com.weisenz.weiadmin.utils.Util;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

public class ResponseResult<T> {

    // 默认 200 为应答成功
    private int code = ResponseStatusEnum.SUCCESS.getCode();
    private T data;
    private String msg;

    public ResponseResult() {
    }

    public ResponseResult(int code) {
        this.code = code;
    }

    public ResponseResult(int code, T data) {
        this.data = data;
        this.code = code;
    }

    /**
     * constructor.
     */
    public ResponseResult(int code, T data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return Util.reflectToString(this);
    }

    public int getCode() {
        return code;
    }

    /**
     * 设置返回码.
     */
    public void setCode(int code) {
        this.code = code;
        if (code != ResponseStatusEnum.SUCCESS.getCode()) {
            try {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public static ResponseResult successResult(int code, Object data, String msg) {
        return new ResponseResult(code, data, msg);
    }

    public static ResponseResult successResult(Object data) {
        return new ResponseResult(data);
    }

    public static ResponseResult excetionResult(ResponseStatusEnum responseStatusEnum) {
        return new ResponseResult(responseStatusEnum.getCode(), responseStatusEnum.getDescription());
    }


}
