package com.balidao.transreport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

/**
 * Created by mark on 16-12-15.
 */
@Entity
@SequenceGenerator(name = "seq_red_envelope_action", sequenceName = "seq_red_envelope_action")
@Table(name = "red_envelope_action")
public class RedEnvelopeAction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_red_envelope_action")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "picked_by")
    private User pickedBy;

    @Column(name = "picked_at")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime pickedAt;
    
    @Column(name="picked_value")
    private Long pickedValue;
    
    @Column(name="picked_order")
    private Integer pickedOrder;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "red_envelope_id", nullable = false)
    private RedEnvelope redEnvelope;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPickedBy() {
        return pickedBy;
    }

    public void setPickedBy(User pickedBy) {
        this.pickedBy = pickedBy;
    }

    public LocalDateTime getPickedAt() {
        return pickedAt;
    }

    public void setPickedAt(LocalDateTime pickedAt) {
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

    public RedEnvelope getRedEnvelope() {
        return redEnvelope;
    }

    public void setRedEnvelope(RedEnvelope redEnvelope) {
        this.redEnvelope = redEnvelope;
    }
    
}
