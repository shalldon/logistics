package com.balidao.transreport.service;

import java.util.List;

import com.balidao.transreport.domain.Group;
import com.balidao.transreport.dto.GroupDto;
import com.balidao.transreport.dto.GroupUserDto;
import com.balidao.transreport.exception.TransreportException;

/**
 * Created by james on 16-12-9.
 */
public interface IGroupService {

    GroupDto saveGroup(Group group);

    List<GroupDto> getAllGroups();

    GroupDto dismissGroup(Long userId, Long groupId) throws TransreportException;

    List<GroupUserDto> listUsers(Long groupId);

    List<GroupDto> getGroupByName(String groupName);

    Group getGroupById(Long groupId);

}
