package com.balidao.transreport.parse;

import java.util.ArrayList;
import java.util.List;

import com.balidao.transreport.domain.RedEnvelope;
import com.balidao.transreport.domain.RedEnvelopeAction;
import com.balidao.transreport.dto.ChatEventDto;
import com.balidao.transreport.dto.RedEnvelopeActionDto;
import com.balidao.transreport.dto.RedEnvelopeDto;

/**
 * Created by mark on 16-12-17.
 */
public class RedEnvelopeParser {

    public static RedEnvelopeDto fromDomainToDto(RedEnvelope domain) {
        RedEnvelopeDto dto = new RedEnvelopeDto();
        dto.setId(domain.getId());
        dto.setIsExpired(domain.getIsExpired());
        dto.setRedEnvelopeRule(domain.getRedEnvelopeRule());
        dto.setRedEnvelopeType(domain.getRedEnvelopeType());
        dto.setRemainSize(domain.getRemainSize());
        dto.setRemainValue(domain.getRemainValue());
        dto.setTotalSize(domain.getTotalSize());
        dto.setTotalValue(domain.getTotalValue());
        if (domain.getActions() != null) {
            List<RedEnvelopeActionDto> actions = new ArrayList<RedEnvelopeActionDto>();
            for (RedEnvelopeAction actionDomain : domain.getActions()) {
                RedEnvelopeActionDto actionDto = RedEnvelopeActionParser.fromDomainToDto(actionDomain);
                actions.add(actionDto);
            }
            dto.setActions(actions);
        }
        ChatEventDto chat = new ChatEventDto();
        chat.setId(domain.getChatEvent().getId());
        dto.setChatEvent(chat);
        return dto;
    }
}
