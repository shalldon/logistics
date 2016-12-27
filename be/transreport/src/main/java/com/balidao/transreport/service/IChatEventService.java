package com.balidao.transreport.service;

import java.util.List;

import com.balidao.transreport.domain.RedEnvelopeRule;
import com.balidao.transreport.domain.RedEnvelopeType;
import com.balidao.transreport.dto.ChatEventDto;
import com.balidao.transreport.dto.RedEnvelopeActionDto;
import com.balidao.transreport.exception.TransreportException;

/**
 * Created by mark on 16-12-26.
 */
public interface IChatEventService {

    public ChatEventDto createTextEvent(String content, Long groupId, Long userId) throws TransreportException;

    public ChatEventDto createRedEnvelopeEvent(String content, Long groupId, Long userId, Long totalValue,
            Integer totalSize, RedEnvelopeType type, RedEnvelopeRule rule) throws TransreportException;
    
    public RedEnvelopeActionDto pickRedEnvelope(Long redEnvelopeId, Long userId) throws TransreportException;
    
    public ChatEventDto removeEvent(Long eventId, Long userId) throws TransreportException;

    public List<ChatEventDto> listAllEvents(Long groupId, Long userId) throws TransreportException;
}
