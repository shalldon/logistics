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
    NO_GROUP_USER_FOUND(Messages.NO_GROUP_USER_FOUND),
    NO_GROUP_FOUND(Messages.NO_GROUP_FOUND),
    NOT_ENOUGH_POINTS(Messages.NOT_ENOUGH_POINTS),
    ALREADY_PICKED_RED_ENVELOPE(Messages.ALREADY_PICKED_RED_ENVELOPE),
    EXPIRED_RED_ENVELOPE(Messages.EXPIRED_RED_ENVELOPE),
    NO_RED_ENVELOPE_LEFT(Messages.NO_RED_ENVELOPE_LEFT),
    NO_NEED_TO_REPORT_POSITION(Messages.NO_NEED_TO_REPORT_POSITION);
    
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
