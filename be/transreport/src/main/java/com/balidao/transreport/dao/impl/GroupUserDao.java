package com.balidao.transreport.dao.impl;

import org.springframework.stereotype.Repository;

import com.balidao.transreport.dao.IGroupUserDao;
import com.balidao.transreport.domain.GroupUser;
import org.springframework.stereotype.Repository;

/**
 * Created by mark on 16-12-11.
 */
@Repository
public class GroupUserDao extends BaseDaoImpl<GroupUser> implements IGroupUserDao {

    @Override
    public GroupUser findGroupUser(Long userId, Long groupId) {
        return (GroupUser) findOneObject("from GroupUser where user.id = ? and group.id= ?",
                new Object[] { userId, groupId });
    }

    @Override
    public void removeMemberFromUser(Long userId, Long groupId) {
        GroupUser groupUser = (GroupUser) findOneObject("from GroupUser where user.id = ? and group.id= ?",
                new Object[] { userId, groupId });
        if (groupUser != null) {
            delete(groupUser.getId());
        }
    }
}
