package com.balidao.transreport.dao.impl;

import org.springframework.stereotype.Repository;

import com.balidao.transreport.dao.IReportPositionActionDao;
import com.balidao.transreport.domain.ReportPositionAction;

/**
 * Created by mark on 16-12-28.
 */
@Repository
public class ReportPositionActionDao extends BaseDaoImpl<ReportPositionAction> implements IReportPositionActionDao {

    @Override
    public ReportPositionAction findReportPositionAction(Long userId, Long requestId) {
        return (ReportPositionAction) findOneObject("from ReportPositionAction where answerer.id = ? and request.id= ?",
                new Object[] { userId, requestId });
    }
}
