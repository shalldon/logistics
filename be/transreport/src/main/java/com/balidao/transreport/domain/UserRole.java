package com.balidao.transreport.domain;

/**
 * Created by james on 16-12-8.
 */
public enum UserRole {

    SHIPPER("sr", true, true), CARRIER("vendor", false, true), RECEIVER("receiver", true, true);

    private String messageKey;

    private boolean canCreateGroup;
    private boolean canJoinGroup;

    UserRole() {
    }

    UserRole(String messageKey, boolean canCreateGroup, boolean canJoinGroup) {
        this.messageKey = messageKey;
        this.canCreateGroup = canCreateGroup;
        this.canJoinGroup = canJoinGroup;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public boolean isCanCreateGroup() {
        return canCreateGroup;
    }

    public void setCanCreateGroup(boolean canCreateGroup) {
        this.canCreateGroup = canCreateGroup;
    }

    public boolean isCanJoinGroup() {
        return canJoinGroup;
    }

    public void setCanJoinGroup(boolean canJoinGroup) {
        this.canJoinGroup = canJoinGroup;
    }

    public static UserRole byId(int id) {
        for(UserRole role : UserRole.values()) {
            if(role.ordinal() == id) {
                return role;
            }
        }
        return null;
    }
}
