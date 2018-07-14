package com.gov.xmxx.system.exception;

/**
 * @author hanyong
 */



public class RoleException extends RuntimeException {
    private Integer code;
    private String message;

    public RoleException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RoleException(String message, Integer code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public RoleException(String message, Throwable cause, Integer code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public RoleException(Throwable cause, Integer code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public RoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
    }

    @Override
    public String toString() {
        return "RoleException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
