package com.balidao.transreport.parse;

import java.util.ArrayList;
import java.util.List;

import com.balidao.transreport.common.DateUtil;
import com.balidao.transreport.domain.GroupUser;
import com.balidao.transreport.domain.User;
import com.balidao.transreport.dto.GroupUserDto;
import com.balidao.transreport.dto.UserDto;

public class UserParser {

    public static UserDto fromDomainToDto(User domain) {
        UserDto user = new UserDto();
        user.setId(domain.getId());
        user.setPhoneNumber(domain.getPhoneNumber());
        user.setUserName(domain.getUserName());
        user.setPoints(domain.getPoints());
        user.setLoginTime(DateUtil.getDateFromLocalDateTime(domain.getLoginTime()));
        user.setRegisterTime(DateUtil.getDateFromLocalDateTime(domain.getRegisterTime()));
        user.setUserRole(domain.getUserRole());
        List<GroupUserDto> list = new ArrayList<>();

        if (domain.getGroups() != null) {

            for (GroupUser groupUser : domain.getGroups()) {

                list.add(GroupUserParser.fromDomainToDto(groupUser));
            }
        }
        user.setGroups(list);

        return user;
    }

    public static User fromDtoToDomain(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setUserName(userDto.getUserName());
        user.setPoints(userDto.getPoints());
        user.setLoginTime(DateUtil.getLocalDateTimeFromDate(userDto.getLoginTime()));
        user.setRegisterTime(DateUtil.getLocalDateTimeFromDate(userDto.getRegisterTime()));
        user.setUserRole(userDto.getUserRole());

        return user;
    }
}
