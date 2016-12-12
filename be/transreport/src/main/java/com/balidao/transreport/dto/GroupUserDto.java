package com.balidao.transreport.dto;

import java.io.Serializable;

import com.balidao.transreport.domain.GroupRole;

/**
 * Created by mark on 16-12-9.
 */
public class GroupUserDto implements Serializable {

    private static final long serialVersionUID = 8995668312252647634L;

    private Long id;

    private GroupDto group;

    private UserDto user;

    private GroupRole groupRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public GroupRole getGroupRole() {
        return groupRole;
    }

    public void setGroupRole(GroupRole groupRole) {
        this.groupRole = groupRole;
    }
    

}
