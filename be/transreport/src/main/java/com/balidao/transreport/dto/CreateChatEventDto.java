package com.balidao.transreport.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mark on 16-12-26.
 */
public class CreateChatEventDto implements Serializable {

    private static final long serialVersionUID = -5942252316354003074L;

    private String content;
    
    private Long groupId;

    // report position request
    private List<Long> userIds;
    
    private boolean hasRedEnvelop;
    
    // red envelop
    private Long totalValue;
    
    private Integer totalSize;
    
    private Integer rule;
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public boolean isHasRedEnvelop() {
        return hasRedEnvelop;
    }

    public void setHasRedEnvelop(boolean hasRedEnvelop) {
        this.hasRedEnvelop = hasRedEnvelop;
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

    public Integer getRule() {
        return rule;
    }

    public void setRule(Integer rule) {
        this.rule = rule;
    }
    
}
