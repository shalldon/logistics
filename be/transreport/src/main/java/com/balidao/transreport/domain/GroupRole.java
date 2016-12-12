package com.balidao.transreport.domain;

import com.balidao.transreport.common.Messages;

/**
 * Created by james on 16-12-9.
 */
public enum GroupRole {
    MASTER(Messages.GROUP_ROLE_MASTER, true, true, true), 
    MEMBER(Messages.GROUP_ROLE_MEMBER, false, true, true),
    PENDING_FOR_APPROVE(Messages.GROUP_ROLE_PENDINGFORAPPROVE, false, false, false),
    INACTIVE(Messages.GROUP_ROLE_INACTIVE, false, false, false);

    private String displayName;
    private boolean canRequestRequestPosition;
    private boolean canWriteSomething;
    private boolean canGiveRedEnvelopes;

    GroupRole() {
    }

    GroupRole(String displayName, boolean canRequestRequestPosition, boolean canWriteSomething,
            boolean canGiveRedEnvelopes) {
        this.displayName = displayName;

    }
    
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isCanRequestRequestPosition() {
        return canRequestRequestPosition;
    }

    public void setCanRequestRequestPosition(boolean canRequestRequestPosition) {
        this.canRequestRequestPosition = canRequestRequestPosition;
    }

    public boolean isCanWriteSomething() {
        return canWriteSomething;
    }

    public void setCanWriteSomething(boolean canWriteSomething) {
        this.canWriteSomething = canWriteSomething;
    }

    public boolean isCanGiveRedEnvelopes() {
        return canGiveRedEnvelopes;
    }

    public void setCanGiveRedEnvelopes(boolean canGiveRedEnvelopes) {
        this.canGiveRedEnvelopes = canGiveRedEnvelopes;
    }

}
