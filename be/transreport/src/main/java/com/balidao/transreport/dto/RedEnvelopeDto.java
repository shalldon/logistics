package com.balidao.transreport.dto;

import java.io.Serializable;
import java.util.List;

import com.balidao.transreport.domain.RedEnvelopeRule;
import com.balidao.transreport.domain.RedEnvelopeType;

/**
 * Created by mark on 16-12-16.
 */
public class RedEnvelopeDto implements Serializable {

    private static final long serialVersionUID = 2092330326849537207L;

    private Long id;

    private Long totalValue;

    private Integer totalSize;

    private Long remainValue;

    private Integer remainSize;

    private Boolean isExpired;

    private RedEnvelopeType redEnvelopeType;

    private RedEnvelopeRule redEnvelopeRule;

    private List<RedEnvelopeActionDto> actions;

    private ChatEventDto chatEvent;

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

    public List<RedEnvelopeActionDto> getActions() {
        return actions;
    }

    public void setActions(List<RedEnvelopeActionDto> actions) {
        this.actions = actions;
    }

    public ChatEventDto getChatEvent() {
        return chatEvent;
    }

    public void setChatEvent(ChatEventDto chatEvent) {
        this.chatEvent = chatEvent;
    }

}
