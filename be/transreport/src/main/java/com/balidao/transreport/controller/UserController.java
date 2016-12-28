package com.balidao.transreport.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balidao.transreport.common.CommonResponse;
import com.balidao.transreport.common.Constants;
import com.balidao.transreport.common.Messages;
import com.balidao.transreport.common.SMSUtil;
import com.balidao.transreport.domain.GroupRole;
import com.balidao.transreport.domain.UserRole;
import com.balidao.transreport.dto.GroupDto;
import com.balidao.transreport.dto.GroupUserDto;
import com.balidao.transreport.dto.InviteUserDto;
import com.balidao.transreport.dto.LoginDto;
import com.balidao.transreport.dto.UserDto;
import com.balidao.transreport.exception.TransreportException;
import com.balidao.transreport.service.IGroupUserService;
import com.balidao.transreport.service.IUserService;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupUserService groupUserService;

    @RequestMapping(value = "/requestValidateCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse requestValidateCode(String phoneNumber) {
        try {
            userService.requestValidateCode(phoneNumber);
            return CommonResponse.success();
        } catch (TransreportException e) {
            return CommonResponse.fail(e);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CommonResponse login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        try {
            UserDto user = userService.login(loginDto.getPhoneNumber(), loginDto.getValidateCode());
            request.getSession().setAttribute(Constants.USER_SESSION_KEY, user);
            return CommonResponse.success(user);
        } catch (TransreportException e) {
            return CommonResponse.fail(e);
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CommonResponse updateUser(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        try {
            UserDto user = (UserDto) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
            user.setUserName((String)params.get("userName"));
            user.setUserRole(UserRole.byId((Integer)params.get("userRole")));
            user = userService.updateUser(user);
            request.getSession().setAttribute(Constants.USER_SESSION_KEY, user);
            return CommonResponse.success(user);
        } catch (TransreportException e) {
            return CommonResponse.fail(e);
        }
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public CommonResponse getUser(HttpServletRequest request) throws Exception {
        UserDto user = (UserDto) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        if (user != null) {
            return CommonResponse.success(user);
        } else {
            return CommonResponse.fail(Messages.NO_USER_FOUND);
        }
    }

    @RequestMapping(value = "/joinGroup", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse joinGroup(Long groupId, HttpServletRequest request) throws Exception {
        UserDto user = (UserDto) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        if (user != null) {
            try {
                GroupUserDto groupUserDto = groupUserService.addUserToGroup(user.getId(), groupId,
                        GroupRole.PENDING_FOR_APPROVE);
                return CommonResponse.success(groupUserDto);
            } catch (TransreportException e) {
                return CommonResponse.fail(e);
            }

        } else {
            return CommonResponse.fail(Messages.NO_USER_FOUND);
        }
    }

    @RequestMapping(value = "/approveGroupUserRequest", method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public CommonResponse approveJoinGroup(Long userId, Long groupId, boolean isApprove, HttpServletRequest request)
            throws Exception {
        UserDto user = (UserDto) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        if (user != null) {
            try {
                GroupUserDto groupUserDto = groupUserService.approveGroupUserRequest(userId, groupId, user.getId(),
                        isApprove);
                return CommonResponse.success(groupUserDto);
            } catch (TransreportException e) {
                return CommonResponse.fail(e);
            }

        } else {
            return CommonResponse.fail(Messages.NO_USER_FOUND);
        }
    }

    @RequestMapping(value = "/quitGroup", method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public CommonResponse quitGroup(Long groupId, HttpServletRequest request) throws Exception {
        UserDto user = (UserDto) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        if (user != null) {
            try {
                GroupUserDto groupUserDto = groupUserService.quitGroup(user.getId(), groupId);
                return CommonResponse.success(groupUserDto);
            } catch (TransreportException e) {
                return CommonResponse.fail(e);
            }

        } else {
            return CommonResponse.fail(Messages.NO_USER_FOUND);
        }
    }

    @RequestMapping(value = "/userGroupList", method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public CommonResponse userGroupList(HttpServletRequest request) {
        UserDto user = (UserDto) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        if (user != null) {
            return CommonResponse.success(userService.listGroups(user.getId()));

        } else {
            return CommonResponse.fail(Messages.NO_USER_FOUND);
        }
    }

    @RequestMapping(value = "/inviteUser", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CommonResponse inviteUser(InviteUserDto inviteUserDto, HttpServletRequest request) {
        UserDto user = (UserDto) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        Boolean isGroupMaster = groupUserService.isGroupMaster(user.getId(), inviteUserDto.getGroupId());
        if (!isGroupMaster) {
            return CommonResponse.fail(Messages.NO_PRIVILEGE);
        }

        GroupDto groupDto = userService.inviteUsersToGroup(user, inviteUserDto.getPhoneList(),
                inviteUserDto.getGroupId());

        SMSUtil.invateUserToGroup(inviteUserDto.getPhoneList(), user, groupDto);
        return CommonResponse.success();
    }

    @RequestMapping(value = "/removeMember", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CommonResponse removeMember(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        UserDto user = (UserDto) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        Long groupId = (Long)params.get("groupId");
        Long memberId = (Long)params.get("memberId");
        Boolean isGroupMaster = groupUserService.isGroupMaster(user.getId(), groupId);
        if (!isGroupMaster) {
            return CommonResponse.fail(Messages.NO_PRIVILEGE);
        }

        userService.removeMemberFromGroup(memberId, groupId);

        return CommonResponse.success();
    }
}
