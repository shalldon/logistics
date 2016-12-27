package com.balidao.transreport.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balidao.transreport.common.Constants;
import com.balidao.transreport.dao.IChatEventDao;
import com.balidao.transreport.dao.IGroupDao;
import com.balidao.transreport.dao.IGroupUserDao;
import com.balidao.transreport.dao.IRedEnvelopeActionDao;
import com.balidao.transreport.dao.IRedEnvelopeDao;
import com.balidao.transreport.dao.IUserDao;
import com.balidao.transreport.domain.ChatEvent;
import com.balidao.transreport.domain.ChatEventType;
import com.balidao.transreport.domain.Group;
import com.balidao.transreport.domain.GroupUser;
import com.balidao.transreport.domain.RedEnvelope;
import com.balidao.transreport.domain.RedEnvelopeAction;
import com.balidao.transreport.domain.RedEnvelopeRule;
import com.balidao.transreport.domain.RedEnvelopeType;
import com.balidao.transreport.domain.User;
import com.balidao.transreport.dto.ChatEventDto;
import com.balidao.transreport.dto.RedEnvelopeActionDto;
import com.balidao.transreport.exception.TransreportException;
import com.balidao.transreport.exception.TransreportExceptionType;
import com.balidao.transreport.parse.ChatEventParser;
import com.balidao.transreport.parse.RedEnvelopeActionParser;
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

    @Autowired
    private IRedEnvelopeDao redEnvelopeDao;

    @Autowired
    private IRedEnvelopeActionDao redEnvelopeActionDao;

    @Override
    @Transactional
    public ChatEventDto createTextEvent(String content, Long groupId, Long userId) throws TransreportException {
        User user = userDao.get(userId);
        Group group = groupDao.get(groupId);
        try {
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
    public ChatEventDto createRedEnvelopeEvent(String content, Long groupId, Long userId, Long totalValue,
            Integer totalSize, RedEnvelopeType type, RedEnvelopeRule rule) throws TransreportException {
        User user = userDao.get(userId);
        Group group = groupDao.get(groupId);
        try {
            GroupUser groupUser = groupUserDao.findGroupUser(userId, groupId);
            if (groupUser == null) {
                throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
            }
        } catch (Exception e) {
            throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
        }
        if (user.getPoints() < totalValue) {
            throw new TransreportException(TransreportExceptionType.NOT_ENOUGH_POINTS);
        }
        if (totalValue.intValue() < totalSize) {
            throw new TransreportException(TransreportExceptionType.NOT_ENOUGH_POINTS);
        }
        ChatEvent chatEvent = new ChatEvent();
        chatEvent.setContent(content);
        chatEvent.setCreatedAt(LocalDateTime.now());
        chatEvent.setCreatedBy(user);
        chatEvent.setEventType(ChatEventType.TEXT);
        chatEvent.setGroup(group);
        chatEvent.setIsDeleted(Boolean.FALSE);
        RedEnvelope redEnvelope = new RedEnvelope();
        redEnvelope.setIsExpired(Boolean.FALSE);
        redEnvelope.setRedEnvelopeRule(rule);
        redEnvelope.setRedEnvelopeType(type);
        redEnvelope.setRemainSize(totalSize);
        redEnvelope.setRemainValue(totalValue);
        redEnvelope.setTotalSize(totalSize);
        redEnvelope.setTotalValue(totalValue);
        chatEvent.setRedEnvelope(redEnvelope);
        chatEventDao.save(chatEvent);
        user.setPoints(user.getPoints() - totalValue);
        userDao.save(user);
        return ChatEventParser.fromDomainToDto(chatEvent);
    }

    @Override
    @Transactional
    public RedEnvelopeActionDto pickRedEnvelope(Long redEnvelopeId, Long userId) throws TransreportException {
        RedEnvelopeAction pAction = redEnvelopeActionDao.findRedEnvelopeAction(userId, redEnvelopeId);
        if (pAction != null) {
            throw new TransreportException(TransreportExceptionType.ALREADY_JOINED_GROUP);
        }
        RedEnvelope redEnvelope = redEnvelopeDao.get(redEnvelopeId);
        if (redEnvelope.getIsExpired() == Boolean.TRUE) {
            throw new TransreportException(TransreportExceptionType.EXPIRED_RED_ENVELOPE);
        }
        if (redEnvelope.getRemainSize() < 0) {
            throw new TransreportException(TransreportExceptionType.NO_RED_ENVELOPE_LEFT);
        }
        User user = userDao.get(userId);
        RedEnvelopeAction action = new RedEnvelopeAction();
        action.setPickedAt(LocalDateTime.now());
        action.setPickedBy(user);
        action.setPickedOrder(redEnvelope.getTotalSize() - redEnvelope.getRemainSize() + 1);
        action.setPickedValue(getRedEnvelopePickedValue(redEnvelope));
        action.setRedEnvelope(redEnvelope);
        List<RedEnvelopeAction> actions = redEnvelope.getActions();
        if(actions == null) {
            actions = new ArrayList<RedEnvelopeAction>();
        }
        actions.add(action);
        redEnvelope.setRemainValue(redEnvelope.getRemainValue() - action.getPickedValue());
        redEnvelope.setRemainSize(redEnvelope.getRemainSize() -1);
        return RedEnvelopeActionParser.fromDomainToDto(action);
    }

    private Long getRedEnvelopePickedValue(RedEnvelope redEnvelope) {
        if (redEnvelope.getRemainSize() == 1) {
            return redEnvelope.getRemainValue();
        }
        if (RedEnvelopeRule.DIVIDE_EQUALLY == redEnvelope.getRedEnvelopeRule()) {
            return redEnvelope.getTotalValue() / redEnvelope.getTotalSize();
        }
        if (RedEnvelopeRule.RANDOM == redEnvelope.getRedEnvelopeRule()) {
            Long maxValue1 = redEnvelope.getRemainValue() - (redEnvelope.getRemainSize() - 1);
            Long maxValue2 = (redEnvelope.getTotalValue() / redEnvelope.getTotalSize()) * 2;
            Long maxValue = maxValue1 > maxValue2 ? maxValue2 : maxValue1;
            return BigDecimal.valueOf(Math.random() * maxValue).setScale(0, BigDecimal.ROUND_DOWN).longValue();
        }
        return 1l;
    }

    @Override
    @Transactional
    public ChatEventDto removeEvent(Long eventId, Long userId) throws TransreportException {
        User user = userDao.get(userId);
        ChatEvent chatEvent = chatEventDao.get(eventId);
        if (user.getId() != chatEvent.getCreatedBy().getId()) {
            throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
        }
        chatEvent.setDeletedAt(LocalDateTime.now());
        chatEvent.setIsDeleted(Boolean.TRUE);
        return ChatEventParser.fromDomainToDto(chatEvent);
    }

    @Override
    public List<ChatEventDto> listAllEvents(Long groupId, Long userId) throws TransreportException {
        try {
            GroupUser groupUser = groupUserDao.findGroupUser(userId, groupId);
            if (groupUser == null) {
                throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
            }
        } catch (Exception e) {
            throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
        }
        List<ChatEvent> events = chatEventDao.findList(
                "from ChatEvent where isDeleted = false and groupId = ? order by createdAt desc",
                Constants.MAX_CHAT_EVENTS_NUMBER, groupId);
        List<ChatEventDto> dtos = new ArrayList<ChatEventDto>();
        for (ChatEvent event : events) {
            dtos.add(ChatEventParser.fromDomainToDto(event));
        }
        return dtos;
    }
}
