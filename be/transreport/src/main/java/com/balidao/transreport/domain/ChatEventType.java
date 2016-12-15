package com.balidao.transreport.domain;

import com.balidao.transreport.common.Messages;

/**
 * Created by mark on 16-12-15.
 */
public enum ChatEventType {
    TEXT(Messages.CHAT_EVENT_TYPE_TEXT),
    RED_ENVELOPE(Messages.CHAT_EVENT_TYPE_RED_ENVELOPE),
    REPORT_POSITION(Messages.CHAT_EVENT_TYPE_REPORT_POSITION);
    
    private String displayName;
    
    ChatEventType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
