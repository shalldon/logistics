package com.balidao.transreport.parse;

import java.util.ArrayList;
import java.util.List;

import com.balidao.transreport.common.DateUtil;
import com.balidao.transreport.domain.ReportPositionAction;
import com.balidao.transreport.domain.ReportPositionRequest;
import com.balidao.transreport.dto.ChatEventDto;
import com.balidao.transreport.dto.ReportPositionActionDto;
import com.balidao.transreport.dto.ReportPositionRequestDto;
import com.balidao.transreport.dto.UserDto;

/**
 * Created by mark on 16-12-28.
 */
public class ReportPositionRequestParser {

    public static ReportPositionRequestDto fromDomainToDto(ReportPositionRequest domain) {
        ReportPositionRequestDto dto = new ReportPositionRequestDto();
        dto.setId(domain.getId());
        dto.setTotalRequest(domain.getTotalRequest());
        dto.setAnsweredRequest(domain.getAnsweredRequest());
        if (domain.getActions() != null) {
            List<ReportPositionActionDto> actions = new ArrayList<ReportPositionActionDto>();
            for (ReportPositionAction actionDomain : domain.getActions()) {
                ReportPositionActionDto actionDto = new ReportPositionActionDto();
                actionDto.setId(actionDomain.getId());
                actionDto.setAnsweredAt(DateUtil.getDateFromLocalDateTime(actionDomain.getAnsweredAt()));
                UserDto user = new UserDto();
                user.setId(actionDomain.getAnswerer().getId());
                user.setUserName(actionDomain.getAnswerer().getUserName());
                user.setPhoneNumber(actionDomain.getAnswerer().getPhoneNumber());
                actionDto.setAnswerer(user);
                actionDto.setAnsweredOrder(actionDomain.getAnsweredOrder());
                actionDto.setPositionX(actionDomain.getPositionX());
                actionDto.setPositionY(actionDomain.getPositionY());
                actionDto.setAddress(actionDomain.getAddress());
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
