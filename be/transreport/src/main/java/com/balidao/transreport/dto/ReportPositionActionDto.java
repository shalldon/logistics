package com.balidao.transreport.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mark on 16-12-28.
 */
public class ReportPositionActionDto implements Serializable {

    private static final long serialVersionUID = 5366501086972502651L;

    private Long id;

    private UserDto answerer;

    private Date answeredAt;

    private BigDecimal positionX;

    private BigDecimal positionY;

    private String address;

    private Integer answeredOrder;

    private ReportPositionRequestDto request;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getAnswerer() {
        return answerer;
    }

    public void setAnswerer(UserDto answerer) {
        this.answerer = answerer;
    }

    public Date getAnsweredAt() {
        return answeredAt;
    }

    public void setAnsweredAt(Date answeredAt) {
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

    public ReportPositionRequestDto getRequest() {
        return request;
    }

    public void setRequest(ReportPositionRequestDto request) {
        this.request = request;
    }

}
