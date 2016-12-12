package com.balidao.transreport.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balidao.transreport.common.SMSUtil;
import com.balidao.transreport.dao.IGroupDao;
import com.balidao.transreport.dao.IGroupUserDao;
import com.balidao.transreport.dao.IUserDao;
import com.balidao.transreport.domain.Group;
import com.balidao.transreport.domain.GroupRole;
import com.balidao.transreport.domain.GroupUser;
import com.balidao.transreport.domain.User;
import com.balidao.transreport.dto.GroupUserDto;
import com.balidao.transreport.exception.TransreportException;
import com.balidao.transreport.exception.TransreportExceptionType;
import com.balidao.transreport.parse.GroupUserParser;
import com.balidao.transreport.service.IGroupUserService;

/**
 * Created by mark on 16-12-11.
 */
@Service
public class GroupUserService implements IGroupUserService {

    @Autowired
    private IGroupDao groupDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IGroupUserDao groupUserDao;

    @Override
    @Transactional
    public GroupUserDto addUserToGroup(Long userId, Long groupId, GroupRole groupRole) throws TransreportException {
        GroupUser groupUser = groupUserDao.findGroupUser(userId, groupId);
        if (groupUser != null && groupUser.getGroupRole() != GroupRole.INACTIVE) {
            throw new TransreportException(TransreportExceptionType.ALREADY_JOINED_GROUP);
        }
        User user = userDao.get(userId);
        Group group = groupDao.get(groupId);
        if (groupUser == null) {
            groupUser = new GroupUser();
            groupUser.setUser(user);
            groupUser.setGroup(group);
        }
        groupUser.setGroupRole(groupRole);
        groupUserDao.save(groupUser);
        SMSUtil.sendJoinGroup(user, group);
        return GroupUserParser.fromDomainToDto(groupUser);
    }

    @Override
    @Transactional
    public GroupUserDto approveGroupUserRequest(Long userId, Long groupId, Long masterUserId, boolean isApprove)
            throws TransreportException {

        GroupUser masterUser = groupUserDao.findGroupUser(masterUserId, groupId);
        if (masterUser == null || masterUser.getGroupRole() != GroupRole.MASTER) {
            throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
        }

        GroupUser groupUser = groupUserDao.findGroupUser(userId, groupId);
        if (groupUser == null || groupUser.getGroupRole() != GroupRole.PENDING_FOR_APPROVE) {
            throw new TransreportException(TransreportExceptionType.NO_JOIN_GROUP_REQUEST_FOUND);
        }

        if (isApprove) {
            groupUser.setGroupRole(GroupRole.MEMBER);
        } else {
            groupUser.setGroupRole(GroupRole.INACTIVE);
        }

        Group group = groupDao.get(groupId);
        SMSUtil.sendGroupUserRequestApprovalRequest(group, isApprove);

        return GroupUserParser.fromDomainToDto(groupUser);
    }

    @Override
    @Transactional
    public GroupUserDto quitGroup(Long userId, Long groupId) throws TransreportException {
        GroupUser groupUser = groupUserDao.findGroupUser(userId, groupId);
        if (groupUser == null) {
            throw new TransreportException(TransreportExceptionType.NO_GROUP_USER_FOUND);
        }
        if (groupUser.getGroupRole() == GroupRole.MASTER) {
            // master can't quit group
            throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
        }
        groupUser.setGroupRole(GroupRole.INACTIVE);
        return GroupUserParser.fromDomainToDto(groupUser);
    }

    @Override
    public Boolean isGroupMaster(Long userId, Long groupId) {
        GroupUser groupUser = groupUserDao.findGroupUser(userId, groupId);
        if (groupUser != null && GroupRole.MASTER.equals(groupUser.getGroupRole())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

}
