package com.balidao.transreport.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by mark on 16-12-28.
 */
public class ReportPostionPostDto implements Serializable{

    private static final long serialVersionUID = 6061291871251083450L;

    private Long requestId;
    
    private BigDecimal positionX;
    
    private BigDecimal positionY;
    
    private String address;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
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

}
