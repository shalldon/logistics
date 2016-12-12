package com.balidao.transreport.dao.impl;

import com.balidao.transreport.dao.IGroupDao;
import com.balidao.transreport.dao.IMessageLogDao;
import com.balidao.transreport.domain.Group;
import com.balidao.transreport.domain.MessageLog;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by james on 16-12-9.
 */
@Repository
public class GroupDao extends BaseDaoImpl<Group> implements IGroupDao {

    @Override
    public Group findGroupById(Long groupId) {
        Map<String, Object> alias = new HashMap<String, Object>();
        alias.put("groupId", groupId);
        return (Group) findOneObject("from Group where id=:groupId");
    }
}
