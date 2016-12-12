package com.balidao.transreport.parse;

import com.balidao.transreport.domain.MessageLog;
import com.balidao.transreport.dto.MessageLogDto;

/**
 * Created by james on 16-12-4.
 */
public class MessageLogParser {

    public static MessageLogDto fromDomainToDto(MessageLog source) {
        MessageLogDto messageLogDto = new MessageLogDto();
        messageLogDto.setId(source.getId());
        messageLogDto.setSendBy(source.getSendBy());
        messageLogDto.setSendTo(source.getSendTo());
        messageLogDto.setSendDate(source.getSendDate());
        messageLogDto.setMessageBody(source.getMessageBody());
        messageLogDto.setSend_to_phoneNum(source.getSend_to_phoneNum());
        return messageLogDto;
    }

    public static MessageLog fromDtoToDomain(MessageLogDto source) {
        MessageLog messageLog = new MessageLog();
        messageLog.setId(source.getId());
        messageLog.setSendBy(source.getSendBy());
        messageLog.setSendTo(source.getSendTo());
        messageLog.setSendDate(source.getSendDate());
        messageLog.setMessageBody(source.getMessageBody());
        messageLog.setSend_to_phoneNum(source.getSend_to_phoneNum());
        return messageLog;
    }
}
