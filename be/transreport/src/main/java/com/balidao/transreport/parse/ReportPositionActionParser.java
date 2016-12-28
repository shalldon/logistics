package com.balidao.transreport.parse;

import com.balidao.transreport.common.DateUtil;
import com.balidao.transreport.domain.ReportPositionAction;
import com.balidao.transreport.dto.ReportPositionActionDto;
import com.balidao.transreport.dto.UserDto;

/**
 * Created by mark on 16-12-28.
 */
public class ReportPositionActionParser {

    public static ReportPositionActionDto fromDomainToDto(ReportPositionAction domain) {
        ReportPositionActionDto dto = new ReportPositionActionDto();
        dto.setId(domain.getId());
        dto.setAnsweredAt(DateUtil.getDateFromLocalDateTime(domain.getAnsweredAt()));
        UserDto user = new UserDto();
        user.setId(domain.getAnswerer().getId());
        user.setUserName(domain.getAnswerer().getUserName());
        user.setPhoneNumber(domain.getAnswerer().getPhoneNumber());
        dto.setAnswerer(user);
        dto.setAnsweredOrder(domain.getAnsweredOrder());
        dto.setPositionX(domain.getPositionX());
        dto.setPositionY(domain.getPositionY());
        dto.setAddress(domain.getAddress());
        return dto;
    }
}
