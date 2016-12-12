package com.balidao.transreport.dto;

import org.joda.time.LocalDateTime;

import java.io.Serializable;

/**
 * Created by james on 16-12-4.
 */
public class MessageLogDto implements Serializable {

    private static final long serialVersionUID = 3706008579631817846L;

    private Long id;
    private String messageBody;
    private LocalDateTime sendDate;
    private Long send_to_phoneNum;
    private Long sendTo;
    private Long sendBy;

    public MessageLogDto() {
        super();
    }

    public MessageLogDto(Long id, String messageBody, LocalDateTime sendDate, Long send_to_phoneNum, Long sendTo,
            Long sendBy) {
        this.id = id;
        this.messageBody = messageBody;
        this.sendDate = sendDate;
        this.send_to_phoneNum = send_to_phoneNum;
        this.sendTo = sendTo;
        this.sendBy = sendBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public Long getSend_to_phoneNum() {
        return send_to_phoneNum;
    }

    public void setSend_to_phoneNum(Long send_to_phoneNum) {
        this.send_to_phoneNum = send_to_phoneNum;
    }

    public Long getSendTo() {
        return sendTo;
    }

    public void setSendTo(Long sendTo) {
        this.sendTo = sendTo;
    }

    public Long getSendBy() {
        return sendBy;
    }

    public void setSendBy(Long sendBy) {
        this.sendBy = sendBy;
    }
}
