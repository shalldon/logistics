package com.balidao.transreport.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balidao.transreport.common.CommonResponse;
import com.balidao.transreport.common.Messages;
import com.balidao.transreport.dao.IMessageLogDao;
import com.balidao.transreport.domain.MessageLog;
import com.balidao.transreport.dto.MessageLogDto;
import com.balidao.transreport.parse.MessageLogParser;
import com.balidao.transreport.service.IMessageLogService;

/**
 * Created by james on 16-12-4.
 */
@Service
public class MessageLogService implements IMessageLogService {

    @Autowired
    private IMessageLogDao messageLogDao;

    @Override
    @Transactional
    public CommonResponse saveMessageLog(MessageLog messageLog) {
        if (messageLog != null) {
            messageLog.setSendDate(LocalDateTime.now());
            messageLogDao.save(messageLog);
            MessageLogDto messageLogDto = MessageLogParser.fromDomainToDto(messageLog);
            return CommonResponse.success(messageLogDto);
        }

        return CommonResponse.fail(Messages.SAVE_MESSAGE_LOG_ERROR);
    }

    @Override
    public CommonResponse getAllMessageLogs() {
        List<MessageLog> list = messageLogDao.findList("from MessageLog");
        List<MessageLogDto> listDto = new ArrayList<MessageLogDto>();
        if (list != null && list.size() > 0) {
            for (MessageLog messageLog : list) {
                listDto.add(MessageLogParser.fromDomainToDto(messageLog));
            }
            return CommonResponse.success(listDto);
        } else {
            return CommonResponse.fail(Messages.NO_MESSAGE_LOG_FOUND);
        }
    }

}