package com.balidao.transreport.common;

import com.balidao.transreport.exception.TransreportException;

/**
 * Created by mark on 16-11-30.
 */
public class CommonResponse {

    private Status status;

    private Object responseBody;

    private String error;

    public static CommonResponse success() {
        CommonResponse response = new CommonResponse();
        response.status = Status.SUCCESS;
        return response;
    }

    public static CommonResponse success(Object responseBody) {
        CommonResponse response = new CommonResponse();
        response.status = Status.SUCCESS;
        response.responseBody = responseBody;
        return response;
    }

    public static CommonResponse fail(String error) {
        CommonResponse response = new CommonResponse();
        response.status = Status.FAIL;
        response.error = error;
        return response;
    }

    public static CommonResponse fail(TransreportException transreportException) {
        CommonResponse response = new CommonResponse();
        response.status = Status.FAIL;
        response.error = transreportException.getTransreportExceptionType().getMessageKey();
        return response;
    }

    public Status getStatus() {
        return status;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public String getError() {
        return error;
    }

    private enum Status {
        SUCCESS, FAIL
    }
}
