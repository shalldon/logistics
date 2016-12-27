package com.balidao.transreport.domain;

import com.balidao.transreport.common.Messages;

/**
 * Created by mark on 16-12-15.
 */
public enum RedEnvelopeRule {
    DIVIDE_EQUALLY(Messages.RED_ENVELOPE_RULE_DIVIDE_EQUALLY),
    RANDOM(Messages.RED_ENVELOPE_RULE_RANDOM),
    FIRST_COME_GET_MORE(Messages.RED_ENVELOPE_RULE_FIRST_COME_GET_MORE);
    
    private String displayName;
    
    RedEnvelopeRule(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public static RedEnvelopeRule byId(int id) {
        for(RedEnvelopeRule rule : RedEnvelopeRule.values()) {
            if(rule.ordinal() == id) {
                return rule;
            }
        }
        return null;
    }
}
