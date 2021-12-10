package com.homework.lovedog.service;



public enum ResultCode {
    SUCCESS("000000", "Success"),

    ;

    private String result;
    private String message;
    ResultCode(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResultCode parse(String result) {
        for (ResultCode resultCode : values()) {
            if (resultCode.getResult().equals(result)) {
                return resultCode;
            }
        }
        return null;
    }

}
