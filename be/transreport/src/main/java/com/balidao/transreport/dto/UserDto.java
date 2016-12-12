package com.balidao.transreport.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.balidao.transreport.domain.UserRole;

/**
 * Created by mark on 16-12-01.
 */
public class UserDto implements Serializable {

    private static final long serialVersionUID = 5078041706898252319L;

    private Long id;

    private String phoneNumber;

    private String userName;

    private Date registerTime;

    private Date loginTime;

    private Long points;

    private UserRole userRole;

    private List<GroupUserDto> groups = new ArrayList<GroupUserDto>();

    public UserDto() {
    }

    public UserDto(Long id, String phoneNumber, String userName, Date registerTime, Date loginTime, Long points,
            UserRole userRole, List<GroupUserDto> groups) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.registerTime = registerTime;
        this.loginTime = loginTime;
        this.points = points;
        this.userRole = userRole;
        this.groups = groups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<GroupUserDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupUserDto> groups) {
        this.groups = groups;
    }

}
