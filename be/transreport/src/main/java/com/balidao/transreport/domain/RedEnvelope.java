package com.balidao.transreport.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by mark on 16-12-15.
 */
@Entity
@SequenceGenerator(name = "seq_red_envelope", sequenceName = "seq_red_envelope")
@Table(name = "red_envelope")
public class RedEnvelope {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_red_envelope")
    private Long id;

    @Column(name = "total_value")
    private Long totalValue;

    @Column(name = "total_size")
    private Integer totalSize;

    @Column(name = "remain_value")
    private Long remainValue;

    @Column(name = "remain_size")
    private Integer remainSize;

    @Column(name = "is_expired")
    private Boolean isExpired;

    @Column(name = "red_envelope_type")
    @Enumerated(EnumType.ORDINAL)
    private RedEnvelopeType redEnvelopeType;

    @Column(name = "red_envelope_rule")
    @Enumerated(EnumType.ORDINAL)
    private RedEnvelopeRule redEnvelopeRule;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "redEnvelope", cascade = CascadeType.ALL)
    private List<RedEnvelopeAction> actions;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_event_id")
    private ChatEvent chatEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Long totalValue) {
        this.totalValue = totalValue;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Long getRemainValue() {
        return remainValue;
    }

    public void setRemainValue(Long remainValue) {
        this.remainValue = remainValue;
    }

    public Integer getRemainSize() {
        return remainSize;
    }

    public void setRemainSize(Integer remainSize) {
        this.remainSize = remainSize;
    }

    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }

    public RedEnvelopeType getRedEnvelopeType() {
        return redEnvelopeType;
    }

    public void setRedEnvelopeType(RedEnvelopeType redEnvelopeType) {
        this.redEnvelopeType = redEnvelopeType;
    }

    public RedEnvelopeRule getRedEnvelopeRule() {
        return redEnvelopeRule;
    }

    public void setRedEnvelopeRule(RedEnvelopeRule redEnvelopeRule) {
        this.redEnvelopeRule = redEnvelopeRule;
    }

    public List<RedEnvelopeAction> getActions() {
        return actions;
    }

    public void setActions(List<RedEnvelopeAction> actions) {
        this.actions = actions;
    }

    public ChatEvent getChatEvent() {
        return chatEvent;
    }

    public void setChatEvent(ChatEvent chatEvent) {
        this.chatEvent = chatEvent;
    }

}
