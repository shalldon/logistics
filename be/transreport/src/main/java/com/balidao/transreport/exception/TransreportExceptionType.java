package com.balidao.transreport.exception;

import com.balidao.transreport.common.Messages;

public enum TransreportExceptionType {

    VALIDATE_CODE_ALREADY_REQUESTED(Messages.VALIDATE_CODE_ALREADY_REQUESTED),
    VALIDATE_CODE_REQUIRED(Messages.VALIDATE_CODE_REQUIRED),
    VALIDATE_CODE_NOT_MATCH(Messages.VALIDATE_CODE_NOT_MATCH),
    NO_USER_FOUND(Messages.NO_USER_FOUND),
    ALREADY_JOINED_GROUP(Messages.ALREADY_JOINED_GROUP),
    NO_JOIN_GROUP_REQUEST_FOUND(Messages.NO_JOIN_GROUP_REQUEST_FOUND),
    NO_PRIVILEGE(Messages.NO_PRIVILEGE),
    NO_GROUP_USER_FOUND(Messages.NO_GROUP_USER_FOUND);
    
    private TransreportExceptionType(String messageKey) {
        this.messageKey = messageKey;
    }
    
    private String messageKey;

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }
    
    
}
