package com.balidao.transreport.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balidao.transreport.dao.IChatEventDao;
import com.balidao.transreport.dao.IGroupDao;
import com.balidao.transreport.dao.IGroupUserDao;
import com.balidao.transreport.dao.IUserDao;
import com.balidao.transreport.dao.pager.Pager;
import com.balidao.transreport.dao.pager.PagerContext;
import com.balidao.transreport.domain.ChatEvent;
import com.balidao.transreport.domain.ChatEventType;
import com.balidao.transreport.domain.Group;
import com.balidao.transreport.domain.GroupUser;
import com.balidao.transreport.domain.User;
import com.balidao.transreport.dto.ChatEventDto;
import com.balidao.transreport.exception.TransreportException;
import com.balidao.transreport.exception.TransreportExceptionType;
import com.balidao.transreport.parse.ChatEventParser;
import com.balidao.transreport.service.IChatEventService;

/**
 * Created by mark on 16-12-26.
 */
@Service
public class ChatEventService implements IChatEventService {

    @Autowired
    private IUserDao userDao;
    
    @Autowired
    private IGroupDao groupDao;
    
    @Autowired
    private IChatEventDao chatEventDao;
    
    @Autowired
    private IGroupUserDao groupUserDao;
    
    @Override
    @Transactional
    public ChatEventDto createTextEvent(String content, Long groupId, Long userId) throws TransreportException {
        User user = userDao.get(userId);
        Group group = groupDao.get(groupId);
        try{
            GroupUser groupUser = groupUserDao.findGroupUser(userId, groupId);
            if (groupUser == null) {
                throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
            }
        } catch (Exception e) {
            throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
        }
        ChatEvent chatEvent = new ChatEvent();
        chatEvent.setContent(content);
        chatEvent.setCreatedAt(LocalDateTime.now());
        chatEvent.setCreatedBy(user);
        chatEvent.setEventType(ChatEventType.TEXT);
        chatEvent.setGroup(group);
        chatEvent.setIsDeleted(Boolean.FALSE);
        chatEventDao.save(chatEvent);
        return ChatEventParser.fromDomainToDto(chatEvent);
    }
    
    @Override
    @Transactional
    public ChatEventDto removeEvent(Long eventId, Long userId) throws TransreportException {
        User user = userDao.get(userId);
        ChatEvent chatEvent = chatEventDao.get(eventId);
        if(user.getId() != chatEvent.getCreatedBy().getId()) {
            throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
        }
        chatEvent.setDeletedAt(LocalDateTime.now());
        chatEvent.setIsDeleted(Boolean.TRUE);
        return ChatEventParser.fromDomainToDto(chatEvent);
    }
    
    @Override
    public List<ChatEventDto> listAllEvents(Long groupId, Long userId, Integer startPage, Integer pageSize) throws TransreportException {
        try{
            GroupUser groupUser = groupUserDao.findGroupUser(userId, groupId);
            if (groupUser == null) {
                throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
            }
        } catch (Exception e) {
            throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
        }
        PagerContext.setStartPage(startPage);
        PagerContext.setPageSize(pageSize);
        Pager<ChatEvent> pager = chatEventDao.findPager("from ChatEvent where isDeleted = false and groupId = ?", groupId);
        List<ChatEvent> events = pager.getDatas();
        List<ChatEventDto> dtos = new ArrayList<ChatEventDto>();
        for(ChatEvent event : events) {
            dtos.add(ChatEventParser.fromDomainToDto(event));
        }
        return dtos;
    }
}
