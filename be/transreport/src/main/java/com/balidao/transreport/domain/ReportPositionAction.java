package com.balidao.transreport.domain;

import java.math.BigDecimal;

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
 * Created by mark on 16-12-28.
 */
@Entity
@SequenceGenerator(name = "seq_report_position_action", sequenceName = "seq_report_position_action")
@Table(name = "report_position_action")
public class ReportPositionAction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_report_position_action")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answerer")
    private User answerer;

    @Column(name = "answered_at")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime answeredAt;
    
    @Column(name = "position_x")
    private BigDecimal positionX;
    
    @Column(name = "position_y")
    private BigDecimal positionY;
    
    @Column(name = "address")
    private String address;
    
    @Column(name="answered_order")
    private Integer answeredOrder;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_position_request_id", nullable = false)
    private ReportPositionRequest request;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAnswerer() {
        return answerer;
    }

    public void setAnswerer(User answerer) {
        this.answerer = answerer;
    }

    public LocalDateTime getAnsweredAt() {
        return answeredAt;
    }

    public void setAnsweredAt(LocalDateTime answeredAt) {
        this.answeredAt = answeredAt;
    }

    public BigDecimal getPositionX() {
        return positionX;
    }

    public void setPositionX(BigDecimal positionX) {
        this.positionX = positionX;
    }

    public BigDecimal getPositionY() {
        return positionY;
    }

    public void setPositionY(BigDecimal positionY) {
        this.positionY = positionY;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAnsweredOrder() {
        return answeredOrder;
    }

    public void setAnsweredOrder(Integer answeredOrder) {
        this.answeredOrder = answeredOrder;
    }

    public ReportPositionRequest getRequest() {
        return request;
    }

    public void setRequest(ReportPositionRequest request) {
        this.request = request;
    }
    
    
}
