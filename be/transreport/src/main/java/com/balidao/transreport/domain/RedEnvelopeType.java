package com.balidao.transreport.domain;

import com.balidao.transreport.common.Messages;

/**
 * Created by mark on 16-12-15.
 */
public enum RedEnvelopeType {
    QUEST(Messages.RED_ENVELOPE_TYPE_QUEST),
    ORDINARY(Messages.RED_ENVELOPE_TYPE_ORDINARY);
    
    private String displayName;
    
    RedEnvelopeType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
