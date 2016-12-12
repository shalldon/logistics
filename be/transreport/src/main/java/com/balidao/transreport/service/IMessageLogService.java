package com.balidao.transreport.service;

import com.balidao.transreport.common.CommonResponse;
import com.balidao.transreport.domain.MessageLog;

/**
 * Created by james on 16-12-4.
 */
public interface IMessageLogService {

    CommonResponse saveMessageLog(MessageLog messageLog);
    
    CommonResponse getAllMessageLogs();
}
