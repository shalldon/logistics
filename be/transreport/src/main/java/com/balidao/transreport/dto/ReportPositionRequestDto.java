package com.balidao.transreport.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mark on 16-12-28.
 */
public class ReportPositionRequestDto implements Serializable {

    private static final long serialVersionUID = -5143637689755145619L;

    private Long id;

    private Integer totalRequest;

    private Integer answeredRequest;

    private List<ReportPositionActionDto> actions;

    private ChatEventDto chatEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalRequest() {
        return totalRequest;
    }

    public void setTotalRequest(Integer totalRequest) {
        this.totalRequest = totalRequest;
    }

    public Integer getAnsweredRequest() {
        return answeredRequest;
    }

    public void setAnsweredRequest(Integer answeredRequest) {
        this.answeredRequest = answeredRequest;
    }

    public List<ReportPositionActionDto> getActions() {
        return actions;
    }

    public void setActions(List<ReportPositionActionDto> actions) {
        this.actions = actions;
    }

    public ChatEventDto getChatEvent() {
        return chatEvent;
    }

    public void setChatEvent(ChatEventDto chatEvent) {
        this.chatEvent = chatEvent;
    }

}
