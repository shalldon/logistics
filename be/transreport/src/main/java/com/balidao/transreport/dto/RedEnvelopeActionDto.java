package com.balidao.transreport.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mark on 16-12-16.
 */
public class RedEnvelopeActionDto implements Serializable {

    private static final long serialVersionUID = 4009086255002774500L;

    private Long id;
    
    private UserDto pickedBy;
    
    private Date pickedAt;
    
    private Long pickedValue;
    
    private Integer pickedOrder;
    
    private RedEnvelopeDto redEnvelope;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getPickedBy() {
        return pickedBy;
    }

    public void setPickedBy(UserDto pickedBy) {
        this.pickedBy = pickedBy;
    }

    public Date getPickedAt() {
        return pickedAt;
    }

    public void setPickedAt(Date pickedAt) {
        this.pickedAt = pickedAt;
    }

    public Long getPickedValue() {
        return pickedValue;
    }

    public void setPickedValue(Long pickedValue) {
        this.pickedValue = pickedValue;
    }

    public Integer getPickedOrder() {
        return pickedOrder;
    }

    public void setPickedOrder(Integer pickedOrder) {
        this.pickedOrder = pickedOrder;
    }

    public RedEnvelopeDto getRedEnvelope() {
        return redEnvelope;
    }

    public void setRedEnvelope(RedEnvelopeDto redEnvelope) {
        this.redEnvelope = redEnvelope;
    }
    
 }
