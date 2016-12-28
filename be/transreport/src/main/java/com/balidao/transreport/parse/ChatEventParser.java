package com.balidao.transreport.parse;

import com.balidao.transreport.common.DateUtil;
import com.balidao.transreport.domain.ChatEvent;
import com.balidao.transreport.dto.ChatEventDto;
import com.balidao.transreport.dto.GroupDto;
import com.balidao.transreport.dto.RedEnvelopeDto;
import com.balidao.transreport.dto.ReportPositionRequestDto;
import com.balidao.transreport.dto.UserDto;

/**
 * Created by mark on 16-12-26.
 */
public class ChatEventParser {

    public static ChatEventDto fromDomainToDto(ChatEvent domain, boolean detail) {
        ChatEventDto dto = new ChatEventDto();
        dto.setId(domain.getId());
        dto.setContent(domain.getContent());
        dto.setCreatedAt(DateUtil.getDateFromLocalDateTime(domain.getCreatedAt()));
        UserDto user = new UserDto();
        user.setId(domain.getCreatedBy().getId());
        user.setUserName(domain.getCreatedBy().getUserName());
        user.setPhoneNumber(domain.getCreatedBy().getPhoneNumber());
        dto.setCreatedBy(user);
        dto.setDeletedAt(DateUtil.getDateFromLocalDateTime(domain.getDeletedAt()));
        dto.setEventType(domain.getEventType());
        GroupDto group = new GroupDto();
        group.setId(domain.getGroup().getId());
        group.setGroupName(domain.getGroup().getGroupName());
        dto.setGroup(group);
        dto.setIsDeleted(domain.getIsDeleted());
        if (domain.getRedEnvelope() != null) {
            if (detail) {
                dto.setRedEnvelope(RedEnvelopeParser.fromDomainToDto(domain.getRedEnvelope()));
            } else {
                RedEnvelopeDto redEnvelope = new RedEnvelopeDto();
                redEnvelope.setId(domain.getRedEnvelope().getId());
                redEnvelope.setTotalSize(domain.getRedEnvelope().getTotalSize());
                redEnvelope.setRemainSize(domain.getRedEnvelope().getRemainSize());
                dto.setRedEnvelope(redEnvelope);
            }
        }
        if (domain.getReportPostionRequest() != null) {
            if (detail) {
                dto.setReportPositionRequest(
                        ReportPositionRequestParser.fromDomainToDto(domain.getReportPostionRequest()));
            } else {
                ReportPositionRequestDto reportPositionRequest = new ReportPositionRequestDto();
                reportPositionRequest.setId(domain.getReportPostionRequest().getId());
                reportPositionRequest.setTotalRequest(domain.getReportPostionRequest().getTotalRequest());
                reportPositionRequest.setAnsweredRequest(domain.getReportPostionRequest().getAnsweredRequest());
                dto.setReportPositionRequest(reportPositionRequest);
            }
        }
        return dto;
    }

    public static ChatEventDto fromDomainToDto(ChatEvent domain) {
        return fromDomainToDto(domain, false);
    }
}
