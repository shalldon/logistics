package com.balidao.transreport.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balidao.transreport.dao.IGroupDao;
import com.balidao.transreport.dao.IGroupUserDao;
import com.balidao.transreport.domain.Group;
import com.balidao.transreport.domain.GroupRole;
import com.balidao.transreport.domain.GroupStatus;
import com.balidao.transreport.domain.GroupUser;
import com.balidao.transreport.dto.GroupDto;
import com.balidao.transreport.dto.GroupUserDto;
import com.balidao.transreport.exception.TransreportException;
import com.balidao.transreport.exception.TransreportExceptionType;
import com.balidao.transreport.parse.GroupParser;
import com.balidao.transreport.parse.GroupUserParser;
import com.balidao.transreport.service.IGroupService;

/**
 * Created by james on 16-12-9.
 */
@Service
public class GroupService implements IGroupService {

    @Autowired
    private IGroupDao groupDao;

    @Autowired
    private IGroupUserDao groupUserDao;
    
    @Override
    @Transactional
    public GroupDto saveGroup(Group group) {

        try {

            if (group.getId() == null) {
                groupDao.save(group);

            } else {
                groupDao.update(group);
            }
        } catch (Exception e) {
            throw e;
        }
        return GroupParser.fromDomainToDto(group);
    }

    public List<GroupDto> getGroupByName(String groupName) {
        List<Group> list = groupDao.findList("from Group as group where group.name  like '" + groupName + "%'");
        List<GroupDto> listDto = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (Group group : list) {
                listDto.add(GroupParser.fromDomainToDto(group));
            }
        }
        return listDto;
    }

    @Override
    public List<GroupDto> getAllGroups() {
        List<Group> list = groupDao.findList("from Group");
        List<GroupDto> listDto = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (Group group : list) {
                listDto.add(GroupParser.fromDomainToDto(group));
            }
        }
        return listDto;
    }
    
    @Override
    @Transactional
    public GroupDto dismissGroup(Long userId, Long groupId) throws TransreportException {
        GroupUser groupUser = groupUserDao.findGroupUser(userId, groupId);
        if (groupUser == null || groupUser.getGroupRole() != GroupRole.MASTER) {
            throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
        }
        Group group = groupDao.get(groupId);
        group.setGroupStatus(GroupStatus.INACTIVE);
        group.setInactivedAt(LocalDateTime.now());
        groupUser.setGroupRole(GroupRole.INACTIVE);
        return GroupParser.fromDomainToDto(group);
    }
    
    @Override
    public List<GroupUserDto> listUsers(Long groupId) {
        Group group = groupDao.get(groupId);
        List<GroupUserDto> userList = new ArrayList<GroupUserDto>();
        for(GroupUser groupUser : group.getUsers()) {
            if(groupUser.getGroupRole() != GroupRole.INACTIVE) {
                userList.add(GroupUserParser.fromDomainToDto(groupUser));
            }
        }
        return userList;
    }

    @Override
    public Group getGroupById(Long groupId) {

        return groupDao.get(groupId);
    }
}
