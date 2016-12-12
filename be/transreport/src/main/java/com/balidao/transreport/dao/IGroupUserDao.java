package com.balidao.transreport.dao;

import com.balidao.transreport.domain.GroupUser;

/**
 * Created by mark on 16-12-11.
 */
public interface IGroupUserDao extends IBaseDao<GroupUser> {

    GroupUser findGroupUser(Long userId, Long groupId);

    void removeMemberFromUser(Long userId, Long groupId);
}
