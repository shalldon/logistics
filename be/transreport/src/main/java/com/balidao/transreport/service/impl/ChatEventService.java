package com.balidao.transreport.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balidao.transreport.common.Constants;
import com.balidao.transreport.common.SMSUtil;
import com.balidao.transreport.dao.IChatEventDao;
import com.balidao.transreport.dao.IGroupDao;
import com.balidao.transreport.dao.IGroupUserDao;
import com.balidao.transreport.dao.IRedEnvelopeActionDao;
import com.balidao.transreport.dao.IRedEnvelopeDao;
import com.balidao.transreport.dao.IReportPositionActionDao;
import com.balidao.transreport.dao.IReportPositionRequestDao;
import com.balidao.transreport.dao.IUserDao;
import com.balidao.transreport.domain.ChatEvent;
import com.balidao.transreport.domain.ChatEventType;
import com.balidao.transreport.domain.Group;
import com.balidao.transreport.domain.GroupUser;
import com.balidao.transreport.domain.RedEnvelope;
import com.balidao.transreport.domain.RedEnvelopeAction;
import com.balidao.transreport.domain.RedEnvelopeRule;
import com.balidao.transreport.domain.RedEnvelopeType;
import com.balidao.transreport.domain.ReportPositionAction;
import com.balidao.transreport.domain.ReportPositionRequest;
import com.balidao.transreport.domain.User;
import com.balidao.transreport.dto.ChatEventDto;
import com.balidao.transreport.dto.RedEnvelopeActionDto;
import com.balidao.transreport.dto.ReportPositionActionDto;
import com.balidao.transreport.exception.TransreportException;
import com.balidao.transreport.exception.TransreportExceptionType;
import com.balidao.transreport.parse.ChatEventParser;
import com.balidao.transreport.parse.RedEnvelopeActionParser;
import com.balidao.transreport.parse.ReportPositionActionParser;
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

    @Autowired
    private IReportPositionRequestDao reportPositionRequestDao;

    @Autowired
    private IReportPositionActionDao reportPositionActionDao;

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
        GroupUser groupUser = groupUserDao.findGroupUser(userId, groupId);
        if (groupUser == null) {
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
        chatEvent.setEventType(ChatEventType.RED_ENVELOPE);
        chatEvent.setGroup(group);
        chatEvent.setIsDeleted(Boolean.FALSE);
        chatEventDao.save(chatEvent);
        RedEnvelope redEnvelope = createRedEnvelope(totalValue, totalSize, type, rule, chatEvent);
        redEnvelopeDao.save(redEnvelope);
        chatEvent.setRedEnvelope(redEnvelope);
        user.setPoints(user.getPoints() - totalValue);
        userDao.save(user);
        return ChatEventParser.fromDomainToDto(chatEvent);
    }

    private RedEnvelope createRedEnvelope(Long totalValue, Integer totalSize, RedEnvelopeType type,
            RedEnvelopeRule rule, ChatEvent chatEvent) {
        RedEnvelope redEnvelope = new RedEnvelope();
        redEnvelope.setIsExpired(Boolean.FALSE);
        redEnvelope.setRedEnvelopeRule(rule);
        redEnvelope.setRedEnvelopeType(type);
        redEnvelope.setRemainSize(totalSize);
        redEnvelope.setRemainValue(totalValue);
        redEnvelope.setTotalSize(totalSize);
        redEnvelope.setTotalValue(totalValue);
        redEnvelope.setChatEvent(chatEvent);
        return redEnvelope;
    }

    @Override
    public RedEnvelopeActionDto pickRedEnvelope(Long redEnvelopeId, Long userId) throws TransreportException {
        return pickRedEnvelope(redEnvelopeId, userId, false);
    }
    
    @Transactional
    public RedEnvelopeActionDto pickRedEnvelope(Long redEnvelopeId, Long userId, boolean forcePick) throws TransreportException {
        RedEnvelopeAction pAction = redEnvelopeActionDao.findRedEnvelopeAction(userId, redEnvelopeId);
        if (pAction != null) {
            throw new TransreportException(TransreportExceptionType.ALREADY_PICKED_RED_ENVELOPE);
        }
        RedEnvelope redEnvelope = redEnvelopeDao.get(redEnvelopeId);
        if (redEnvelope.getIsExpired() == Boolean.TRUE) {
            throw new TransreportException(TransreportExceptionType.EXPIRED_RED_ENVELOPE);
        }
        if (redEnvelope.getRemainSize() < 0) {
            throw new TransreportException(TransreportExceptionType.NO_RED_ENVELOPE_LEFT);
        }
        if (redEnvelope.getRedEnvelopeType() == RedEnvelopeType.QUEST && !forcePick) {
            throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
        }
        User user = userDao.get(userId);
        RedEnvelopeAction action = new RedEnvelopeAction();
        action.setPickedAt(LocalDateTime.now());
        action.setPickedBy(user);
        action.setPickedOrder(redEnvelope.getTotalSize() - redEnvelope.getRemainSize() + 1);
        action.setPickedValue(getRedEnvelopePickedValue(redEnvelope));
        action.setRedEnvelope(redEnvelope);
        List<RedEnvelopeAction> actions = redEnvelope.getActions();
        if (actions == null) {
            actions = new ArrayList<RedEnvelopeAction>();
        }
        actions.add(action);
        user.setPoints(user.getPoints() + action.getPickedValue());
        redEnvelope.setRemainValue(redEnvelope.getRemainValue() - action.getPickedValue());
        redEnvelope.setRemainSize(redEnvelope.getRemainSize() - 1);
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
    public ChatEventDto createReportPositionRequestEvent(String content, Long groupId, Long userId, List<Long> userIds,
            boolean hasRedEnvelop, Long totalValue, RedEnvelopeType type, RedEnvelopeRule rule)
            throws TransreportException {
        User user = userDao.get(userId);
        Group group = groupDao.get(groupId);
        GroupUser groupUser = groupUserDao.findGroupUser(userId, groupId);
        if (groupUser == null) {
            throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
        }
        if (hasRedEnvelop) {
            if (user.getPoints() < totalValue) {
                throw new TransreportException(TransreportExceptionType.NOT_ENOUGH_POINTS);
            }
            if (totalValue.intValue() < userIds.size()) {
                throw new TransreportException(TransreportExceptionType.NOT_ENOUGH_POINTS);
            }
        }
        List<String> phoneList = new ArrayList<String>();
        ChatEvent chatEvent = new ChatEvent();
        chatEvent.setContent(content);
        chatEvent.setCreatedAt(LocalDateTime.now());
        chatEvent.setCreatedBy(user);
        chatEvent.setEventType(ChatEventType.REPORT_POSITION);
        chatEvent.setGroup(group);
        chatEvent.setIsDeleted(Boolean.FALSE);
        chatEventDao.save(chatEvent);
        ReportPositionRequest reportPositionRequest = createReportPositionRequest(userIds, chatEvent);
        reportPositionRequestDao.save(reportPositionRequest);
        chatEvent.setReportPostionRequest(reportPositionRequest);
        List<ReportPositionAction> actions = new ArrayList<ReportPositionAction>();
        for (Long uid : userIds) {
            User answerer = userDao.get(uid);
            ReportPositionAction reportPositionAction = new ReportPositionAction();
            reportPositionAction.setAnswerer(answerer);
            reportPositionAction.setRequest(reportPositionRequest);
            reportPositionActionDao.save(reportPositionAction);
            actions.add(reportPositionAction);
            phoneList.add(answerer.getPhoneNumber());
        }
        reportPositionRequest.setActions(actions);
        if (hasRedEnvelop) {
            RedEnvelope redEnvelope = createRedEnvelope(totalValue, userIds.size(), type, rule, chatEvent);
            redEnvelopeDao.save(redEnvelope);
            chatEvent.setRedEnvelope(redEnvelope);
            user.setPoints(user.getPoints() - totalValue);
            userDao.save(user);
        }
        String username = user.getUserName() != null ? user.getUserName() : user.getPhoneNumber();
        SMSUtil.reportPosition(phoneList, username);
        return ChatEventParser.fromDomainToDto(chatEvent);
    }

    @Override
    @Transactional
    public ReportPositionActionDto reportPosition(Long requestId, BigDecimal positionX, BigDecimal positionY,
            String address, Long userId) throws TransreportException{
        System.out.println(requestId + " >>> " + userId);
        ReportPositionAction pAction = reportPositionActionDao.findReportPositionAction(userId, requestId);
        if (pAction == null) {
            throw new TransreportException(TransreportExceptionType.NO_NEED_TO_REPORT_POSITION);
        }

        ReportPositionRequest request = reportPositionRequestDao.get(requestId);
        pAction.setAnsweredAt(LocalDateTime.now());
        pAction.setPositionX(positionX);
        pAction.setPositionY(positionY);
        pAction.setAddress(address);
        pAction.setAnsweredOrder(request.getAnsweredRequest() + 1);
        reportPositionActionDao.save(pAction);
        request.setAnsweredRequest(request.getAnsweredRequest() + 1);
        reportPositionRequestDao.save(request);
        if (request.getChatEvent().getRedEnvelope() != null) {
            pickRedEnvelope(request.getChatEvent().getRedEnvelope().getId(), userId, true);
        }
        return ReportPositionActionParser.fromDomainToDto(pAction);

    }

    private ReportPositionRequest createReportPositionRequest(List<Long> userIds, ChatEvent chatEvent) {
        ReportPositionRequest reportPositionRequest = new ReportPositionRequest();
        reportPositionRequest.setAnsweredRequest(0);
        reportPositionRequest.setTotalRequest(userIds.size());
        reportPositionRequest.setChatEvent(chatEvent);
        return reportPositionRequest;
    }

    @Override
    public ChatEventDto getEvent(Long eventId, Long userId) throws TransreportException {
        ChatEvent chatEvent = chatEventDao.get(eventId);
        Group group = chatEvent.getGroup();
        GroupUser groupUser = groupUserDao.findGroupUser(userId, group.getId());
        if (groupUser == null) {
            throw new TransreportException(TransreportExceptionType.NO_PRIVILEGE);
        }
        return ChatEventParser.fromDomainToDto(chatEvent, true);
    }

    @Override
    @Transactional
    public ChatEventDto removeEvent(Long eventId, Long userId) throws TransreportException {
        ChatEvent chatEvent = chatEventDao.get(eventId);
        if (userId != chatEvent.getCreatedBy().getId()) {
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
                "from ChatEvent where isDeleted = false and group.id = ? order by createdAt desc",
                Constants.MAX_CHAT_EVENTS_NUMBER, groupId);
        List<ChatEventDto> dtos = new ArrayList<ChatEventDto>();
        for (ChatEvent event : events) {
            dtos.add(ChatEventParser.fromDomainToDto(event));
        }
        return dtos;
    }
}
