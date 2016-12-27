package com.balidao.transreport.dto;

import java.util.Date;

import com.balidao.transreport.domain.ChatEventType;

public class ChatEventDto {

    private Long id;
    
    private String content;
    
    private UserDto createdBy;
    
    private Date createdAt;
    
    private Boolean isDeleted;
    
    private Date deletedAt;
    
    private ChatEventType eventType;
    
    private RedEnvelopeDto redEnvelope;

    private GroupDto group;

    private Boolean isRedEnvelopEvent;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDto createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public ChatEventType getEventType() {
        return eventType;
    }

    public void setEventType(ChatEventType eventType) {
        this.eventType = eventType;
    }

    public RedEnvelopeDto getRedEnvelope() {
        return redEnvelope;
    }

    public void setRedEnvelope(RedEnvelopeDto redEnvelope) {
        this.redEnvelope = redEnvelope;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

    public Boolean getIsRedEnvelopEvent() {
        return isRedEnvelopEvent;
    }

    public void setIsRedEnvelopEvent(Boolean isRedEnvelopEvent) {
        this.isRedEnvelopEvent = isRedEnvelopEvent;
    }
    
    
}
