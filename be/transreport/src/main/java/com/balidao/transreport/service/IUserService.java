package com.balidao.transreport.service;

import java.util.List;

import com.balidao.transreport.dto.GroupDto;
import com.balidao.transreport.dto.GroupUserDto;
import com.balidao.transreport.dto.UserDto;
import com.balidao.transreport.exception.TransreportException;

/**
 * Created by mark on 16-11-30.
 */
public interface IUserService {

    String requestValidateCode(String phoneNumber) throws TransreportException;

    UserDto login(String phoneNumber, String validateCode) throws TransreportException;

    UserDto updateUser(UserDto user) throws TransreportException;

    UserDto getUserFromRedis(Long userId) throws TransreportException;

    UserDto getUserFromDB(Long userId) throws TransreportException;
    
    List<GroupUserDto> listGroups(Long userId);

    GroupDto inviteUsersToGroup(UserDto userDto, List<String> phoneList, Long groupId);

    void removeMemberFromGroup(Long userId, Long groupId);

    UserDto createUser(String phoneNumber);

}