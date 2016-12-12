package com.balidao.transreport.dto;

import java.util.List;

/**
 * Created by double on 16-12-11.
 */
public class InviteUserDto {
    private Long groupId;
    private List<String> phoneList;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }
}
