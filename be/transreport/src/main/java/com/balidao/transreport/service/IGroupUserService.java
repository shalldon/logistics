package com.balidao.transreport.service;

import com.balidao.transreport.domain.GroupRole;
import com.balidao.transreport.dto.GroupUserDto;
import com.balidao.transreport.exception.TransreportException;

/**
 * Created by mark on 16-12-11.
 */
public interface IGroupUserService {

    GroupUserDto addUserToGroup(Long userId, Long groupId, GroupRole groupRole) throws TransreportException;

    GroupUserDto approveGroupUserRequest(Long userId, Long groupId, Long masterUserId, boolean isApprove)
            throws TransreportException;

    GroupUserDto quitGroup(Long userId, Long groupId) throws TransreportException;

    Boolean isGroupMaster(Long userId, Long groupId);

}
