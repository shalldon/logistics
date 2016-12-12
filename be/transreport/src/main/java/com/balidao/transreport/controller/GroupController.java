package com.balidao.transreport.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balidao.transreport.common.CommonResponse;
import com.balidao.transreport.common.Constants;
import com.balidao.transreport.common.Messages;
import com.balidao.transreport.domain.*;
import com.balidao.transreport.dto.GroupDto;
import com.balidao.transreport.dto.UserDto;
import com.balidao.transreport.exception.TransreportException;
import com.balidao.transreport.parse.UserParser;
import com.balidao.transreport.service.IGroupService;

/**
 * Created by james on 16-12-9.
 */
@Controller
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @RequestMapping(value = "/getGroups", method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public CommonResponse getGroups() throws Exception {
        List<GroupDto> list = groupService.getAllGroups();
        if (list != null) {
            return CommonResponse.success(list);
        } else {
            return CommonResponse.fail(Messages.NO_GROUP_FOUND);
        }
    }

    @RequestMapping(value = "/getGroupByName", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CommonResponse getGroupByName(@RequestBody Group groupParameter) throws Exception {
        String groupName = groupParameter.getGroupName();
        List<GroupDto> list = groupService.getGroupByName(groupName);
        if (list != null) {
            return CommonResponse.success(list);
        } else {
            return CommonResponse.fail(Messages.NO_GROUP_FOUND);
        }
    }

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CommonResponse createGroup(@RequestBody Group groupParameter, HttpSession session) throws Exception {
        String groupName = groupParameter.getGroupName();
        UserDto userDto = (UserDto) session.getAttribute(Constants.USER_SESSION_KEY);
        if (userDto == null) {
            return CommonResponse.fail(Messages.SESSION_FAILED);
        }
        User user = UserParser.fromDtoToDomain(userDto);

        Group group = new Group();
        group.setGroupName(groupName);
        group.setGroupStatus(GroupStatus.ACTIVE);
        group.setCreatedAt(LocalDateTime.now());
        group.setCreatedBy(user);
        List<GroupUser> list = new ArrayList<>();
        GroupUser groupUser = new GroupUser();
        groupUser.setUser(user);
        groupUser.setGroup(group);
        groupUser.setGroupRole(GroupRole.MASTER);
        list.add(groupUser);

        group.setUsers(list);

        Long newGroupId = groupService.saveGroup(group).getId();
        if (newGroupId != null) {

            return CommonResponse.success(newGroupId);
        }
        return CommonResponse.fail(Messages.SAVE_GROUP_ERROR);
    }

    @RequestMapping(value = "/editGroup", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CommonResponse editGroup(@RequestBody Group groupParameter, HttpSession session) throws Exception {
        Long groupId = groupParameter.getId();
        String groupName = groupParameter.getGroupName();
        String groupDesc = groupParameter.getGroupDesc();
        UserDto userDto = (UserDto) session.getAttribute(Constants.USER_SESSION_KEY);
        if (userDto == null) {
            return CommonResponse.fail(Messages.SESSION_FAILED);
        }
        boolean canEdit = false;

        Group group = groupService.getGroupById(groupId);
        if (group != null) {
            for (GroupUser groupUser : group.getUsers()) {

                if (groupUser.getGroup().getId().equals(groupId) && GroupRole.MASTER.equals(groupUser.getGroupRole())) {
                    canEdit = true;
                    break;
                }
            }
        } else {
            return CommonResponse.fail(Messages.NO_GROUP_FOUND);
        }

        if (canEdit) {
            group.setGroupName(groupName);
            group.setGroupDesc(groupDesc);
            groupService.saveGroup(group);
            return CommonResponse.success(Messages.UPDATE_GROUP_SUCCESS);
        } else {
            return CommonResponse.fail(Messages.UPDATE_GROUP_ERROR);
        }

    }

    @RequestMapping(value = "/dismissGroup", method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public CommonResponse dismissGroup(Long groupId, HttpSession session) throws Exception {
        UserDto user = (UserDto) session.getAttribute(Constants.USER_SESSION_KEY);
        if (user != null) {
            try {
                GroupDto groupUserDto = groupService.dismissGroup(user.getId(), groupId);
                return CommonResponse.success(groupUserDto);
            } catch (TransreportException e) {
                return CommonResponse.fail(e);
            }

        } else {
            return CommonResponse.fail(Messages.NO_USER_FOUND);
        }
    }

    @RequestMapping(value = "/groupUserList", method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public CommonResponse groupUserList(Long groupId) throws Exception {
        return CommonResponse.success(groupService.listUsers(groupId));
    }
}
