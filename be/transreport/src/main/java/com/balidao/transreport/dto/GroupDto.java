package com.balidao.transreport.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.balidao.transreport.domain.GroupStatus;

/**
 * Created by james on 16-12-9.
 */
public class GroupDto implements Serializable{

    private static final long serialVersionUID = 100484531584842525L;

    private Long id;
    
    private String groupName;
    
    private String groupDesc;

    private GroupStatus groupStatus;
    
    private UserDto createdBy;
    
    private Date createdAt;
    
    private Date inactivedAt;
    
    private List<GroupUserDto> users = new ArrayList<GroupUserDto>();

    public GroupDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public GroupStatus getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(GroupStatus groupStatus) {
        this.groupStatus = groupStatus;
    }

    public UserDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDto createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getInactivedAt() {
        return inactivedAt;
    }

    public void setInactivedAt(Date inactivedAt) {
        this.inactivedAt = inactivedAt;
    }

    public List<GroupUserDto> getUsers() {
        return users;
    }

    public void setUsers(List<GroupUserDto> users) {
        this.users = users;
    }

}
