package com.balidao.transreport.parse;

import com.balidao.transreport.domain.GroupUser;
import com.balidao.transreport.dto.GroupDto;
import com.balidao.transreport.dto.GroupUserDto;
import com.balidao.transreport.dto.UserDto;

/**
 * Created by mark on 16-12-9.
 */
public class GroupUserParser {

    public static GroupUserDto fromDomainToDto(GroupUser domain) {
        GroupUserDto dto = new GroupUserDto();
        dto.setId(domain.getId());
        dto.setGroupRole(domain.getGroupRole());
        GroupDto group = new GroupDto();
        group.setId(domain.getGroup().getId());
        group.setGroupName(domain.getGroup().getGroupName());
        UserDto user = new UserDto();
        user.setId(domain.getUser().getId());
        user.setUserName(domain.getUser().getUserName());
        dto.setGroup(group);
        dto.setUser(user);
        return dto;
    }

    // public static GroupUserMapping fromDtoToDomain(GroupUserMappingDto
    // mappingDto) {
    // GroupUserMapping dto = new GroupUserMapping();
    // dto.setId(mappingDto.getId());
    // dto.setGroupRole(mappingDto.getGroupRole());
    // Group group = new Group();
    // group.setId(mappingDto.getGroup().getId());
    // group.setGroupName(mappingDto.getGroup().getGroupName());
    // User user = new User();
    // user.setId(mappingDto.getUser().getId());
    // user.setUserName(mappingDto.getUser().getUserName());
    // dto.setGroup(group);
    // dto.setUser(user);
    // return dto;
    // }
    public static GroupUser fromDtoToDomain(GroupUserDto groupUserDto) {
        GroupUser groupUser = new GroupUser();
        groupUser.setId(groupUserDto.getId());
        groupUser.setGroupRole(groupUserDto.getGroupRole());
        GroupDto group = new GroupDto();
        group.setId(groupUserDto.getGroup().getId());
        group.setGroupName(groupUserDto.getGroup().getGroupName());
        UserDto user = new UserDto();
        user.setId(groupUserDto.getUser().getId());
        user.setUserName(groupUserDto.getUser().getUserName());
        groupUser.setGroup(GroupParser.fromDtoToDomain(groupUserDto.getGroup()));
        groupUser.setUser(UserParser.fromDtoToDomain(groupUserDto.getUser()));
        return groupUser;
    }
}
