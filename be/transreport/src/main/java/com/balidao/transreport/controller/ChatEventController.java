package com.balidao.transreport.controller;

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
import com.balidao.transreport.dto.CreateChatEventDto;
import com.balidao.transreport.dto.UserDto;
import com.balidao.transreport.exception.TransreportException;
import com.balidao.transreport.service.IChatEventService;

@Controller
public class ChatEventController {

    @Autowired
    private IChatEventService chatEventService;

    @RequestMapping(value = "/createTextEvent", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CommonResponse createTextEvent(@RequestBody CreateChatEventDto dto, HttpServletRequest request) {

        UserDto user = (UserDto) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        if (user == null) {
            return CommonResponse.fail(Messages.NO_USER_FOUND);
        }
        try {
            return CommonResponse.success(chatEventService.createTextEvent(dto.getContent(),  dto.getGroupId(), user.getId()));
        } catch (TransreportException e) {
            return CommonResponse.fail(e);
        }
    }
    
    @RequestMapping(value = "/removeEvent", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse createTextEvent(Long eventId, HttpServletRequest request) {

        UserDto user = (UserDto) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        if (user == null) {
            return CommonResponse.fail(Messages.NO_USER_FOUND);
        }
        try {
            return CommonResponse.success(chatEventService.removeEvent(eventId, user.getId()));
        } catch (TransreportException e) {
            return CommonResponse.fail(e);
        }
    }
    
    @RequestMapping(value = "/listAllEvents", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse listAllEvents(Long groupId, Integer startPage, Integer pageSize, HttpServletRequest request) {

        UserDto user = (UserDto) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        if (user == null) {
            return CommonResponse.fail(Messages.NO_USER_FOUND);
        }
        try {
            return CommonResponse.success(chatEventService.listAllEvents(groupId, user.getId(), startPage, pageSize));
        } catch (TransreportException e) {
            return CommonResponse.fail(e);
        }
    }
    
}
