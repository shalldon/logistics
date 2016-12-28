package com.balidao.transreport.dao;

import com.balidao.transreport.domain.ReportPositionAction;

/**
 * Created by mark on 16-12-27.
 */
public interface IReportPositionActionDao extends IBaseDao<ReportPositionAction> {

    public ReportPositionAction findReportPositionAction(Long userId, Long requestId);
}
