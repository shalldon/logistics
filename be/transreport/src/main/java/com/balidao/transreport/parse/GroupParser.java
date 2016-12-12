package com.balidao.transreport.parse;

import java.util.ArrayList;
import java.util.List;

import com.balidao.transreport.domain.Group;
import com.balidao.transreport.domain.GroupUser;
import com.balidao.transreport.dto.GroupDto;
import com.balidao.transreport.dto.GroupUserDto;
import org.joda.time.LocalDateTime;

/**
 * Created by james on 16-12-9.
 */
public class GroupParser {

    public static GroupDto fromDomainToDto(Group groupDomain) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(groupDomain.getId());
        groupDto.setGroupStatus(groupDomain.getGroupStatus());
        groupDto.setGroupDesc(groupDomain.getGroupDesc());
        groupDto.setGroupName(groupDomain.getGroupName());
        groupDto.setCreatedBy(UserParser.fromDomainToDto(groupDomain.getCreatedBy()));
        groupDto.setCreatedAt(groupDomain.getCreatedAt().toDate());
        groupDto.setInactivedAt(groupDomain.getInactivedAt() == null ? null : groupDomain.getInactivedAt().toDate());
        List<GroupUserDto> users = new ArrayList<GroupUserDto>();
        for (GroupUser mapping : groupDomain.getUsers()) {
            users.add(GroupUserParser.fromDomainToDto(mapping));
        }
        groupDto.setUsers(users);
        return groupDto;
    }

    public static Group fromDtoToDomain(GroupDto groupDto) {
        Group group = new Group();
        group.setId(groupDto.getId());
        group.setGroupStatus(groupDto.getGroupStatus());
        group.setGroupDesc(groupDto.getGroupDesc());
        group.setGroupName(groupDto.getGroupName());
        group.setCreatedBy(UserParser.fromDtoToDomain(groupDto.getCreatedBy()));
        group.setCreatedAt(LocalDateTime.fromDateFields(groupDto.getCreatedAt()));
        group.setInactivedAt(LocalDateTime.fromDateFields(groupDto.getInactivedAt()));
        List<GroupUser> users = new ArrayList<GroupUser>();
        for (GroupUserDto mapping : groupDto.getUsers()) {
            users.add(GroupUserParser.fromDtoToDomain(mapping));
        }
        group.setUsers(users);
        return group;
    }
}
