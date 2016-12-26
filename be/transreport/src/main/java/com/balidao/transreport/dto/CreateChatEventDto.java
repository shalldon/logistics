package com.balidao.transreport.dto;

/**
 * Created by mark on 16-12-26.
 */
public class CreateChatEventDto {

    private String content;
    
    private Long groupId;

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
    
    
}
