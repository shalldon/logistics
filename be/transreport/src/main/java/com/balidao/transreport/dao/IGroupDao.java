package com.balidao.transreport.dao;

import com.balidao.transreport.domain.Group;

/**
 * Created by james on 16-12-9.
 */
public interface IGroupDao extends IBaseDao<Group> {

    Group findGroupById(Long groupId);
}
