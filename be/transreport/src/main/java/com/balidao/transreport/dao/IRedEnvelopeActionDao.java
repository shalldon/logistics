package com.balidao.transreport.dao;

import com.balidao.transreport.domain.RedEnvelopeAction;

/**
 * Created by mark on 16-12-17.
 */
public interface IRedEnvelopeActionDao extends IBaseDao<RedEnvelopeAction> {

    public RedEnvelopeAction findRedEnvelopeAction(Long userId, Long redEnvelopeId);
    
}
