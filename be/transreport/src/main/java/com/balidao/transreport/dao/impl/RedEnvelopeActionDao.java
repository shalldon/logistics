package com.balidao.transreport.dao.impl;

import org.springframework.stereotype.Repository;

import com.balidao.transreport.dao.IRedEnvelopeActionDao;
import com.balidao.transreport.domain.RedEnvelopeAction;

/**
 * Created by mark on 16-12-17.
 */
@Repository
public class RedEnvelopeActionDao extends BaseDaoImpl<RedEnvelopeAction>  implements IRedEnvelopeActionDao {

    @Override
    public RedEnvelopeAction findRedEnvelopeAction(Long userId, Long redEnvelopeId) {
        return (RedEnvelopeAction) findOneObject("from RedEnvelopeAction where pickedBy.id = ? and redEnvelope.id= ?",
                new Object[] { userId, redEnvelopeId });
    }

}
