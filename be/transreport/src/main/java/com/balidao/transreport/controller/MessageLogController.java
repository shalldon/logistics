package com.balidao.transreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balidao.transreport.common.CommonResponse;
import com.balidao.transreport.domain.MessageLog;
import com.balidao.transreport.service.IMessageLogService;

/**
 * Created by james on 16-12-4.
 */
@Controller
public class MessageLogController {

    @Autowired
    private IMessageLogService messageLogService;

    @RequestMapping(value = "/getAllMessageLogs", method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public CommonResponse getAllMessageLogs() throws Exception {
        return messageLogService.getAllMessageLogs();
    }

    @RequestMapping(value = "/saveMessageLog", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CommonResponse saveMessageLog(@RequestBody final MessageLog messageLog) throws Exception {
        return messageLogService.saveMessageLog(messageLog);
    }
}
