package com.balidao.transreport.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * Created by mark on 16-12-28.
 */
@Entity
@SequenceGenerator(name = "seq_report_position_request", sequenceName = "seq_report_position_request")
@Table(name = "report_position_request")
public class ReportPositionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_report_position_request")
    private Long id;

    @Column(name = "total_request")
    private Integer totalRequest;

    @Column(name = "answered_request")
    private Integer answeredRequest;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "request", cascade = CascadeType.ALL)
    private List<ReportPositionAction> actions;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_event_id")
    private ChatEvent chatEvent;

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

    public List<ReportPositionAction> getActions() {
        return actions;
    }

    public void setActions(List<ReportPositionAction> actions) {
        this.actions = actions;
    }

    public ChatEvent getChatEvent() {
        return chatEvent;
    }

    public void setChatEvent(ChatEvent chatEvent) {
        this.chatEvent = chatEvent;
    }

}
