package com.balidao.transreport.parse;

import com.balidao.transreport.common.DateUtil;
import com.balidao.transreport.domain.RedEnvelopeAction;
import com.balidao.transreport.dto.RedEnvelopeActionDto;
import com.balidao.transreport.dto.UserDto;

/**
 * Created by mark on 16-12-17.
 */
public class RedEnvelopeActionParser {

    public static RedEnvelopeActionDto fromDomainToDto(RedEnvelopeAction domain) {
        RedEnvelopeActionDto dto = new RedEnvelopeActionDto();
        dto.setId(domain.getId());
        dto.setPickedAt(DateUtil.getDateFromLocalDateTime(domain.getPickedAt()));
        UserDto user = new UserDto();
        user.setId(domain.getPickedBy().getId());
        user.setUserName(domain.getPickedBy().getUserName());
        user.setPhoneNumber(domain.getPickedBy().getPhoneNumber());
        dto.setPickedBy(user);
        dto.setPickedOrder(domain.getPickedOrder());
        dto.setPickedValue(domain.getPickedValue());
        return dto;
    }
}
