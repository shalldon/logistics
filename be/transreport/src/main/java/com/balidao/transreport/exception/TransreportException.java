package com.balidao.transreport.exception;

public class TransreportException extends Exception {

    private static final long serialVersionUID = 8667494415544427865L;

    private TransreportExceptionType transreportExceptionType;
    
    public TransreportException(TransreportExceptionType transreportExceptionType) {
        this.transreportExceptionType = transreportExceptionType;
    }

    public TransreportExceptionType getTransreportExceptionType() {
        return transreportExceptionType;
    }
    
}
