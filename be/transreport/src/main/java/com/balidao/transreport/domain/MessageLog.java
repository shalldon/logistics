package com.balidao.transreport.domain;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by james on 16-11-30.
 */
@Entity
@SequenceGenerator(name = "seq_message_log", sequenceName = "seq_message_log")
@Table(name = "message_log")
public class MessageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_message_log")
    private Long id;
    @Column(name = "message_body")
    private String messageBody;
    @Column(name = "send_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime sendDate;
    @Column(name = "send_to_phone_num")
    private Long sendToPhoneNum;
    @Column(name = "send_to")
    private Long sendTo;// userId;
    @Column(name = "send_by")
    private Long sendBy;

    public MessageLog() {
        super();
    }

    public MessageLog(Long id, String messageBody, LocalDateTime sendDate, Long sendToPhoneNum, Long sendTo,
            Long sendBy) {
        this.id = id;
        this.messageBody = messageBody;
        this.sendDate = sendDate;
        this.sendToPhoneNum = sendToPhoneNum;
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
        return sendToPhoneNum;
    }

    public void setSend_to_phoneNum(Long sendToPhoneNum) {
        this.sendToPhoneNum = sendToPhoneNum;
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

    @Override
    public String toString() {
        return "MessageLog{" + "id=" + id + ", messageBody='" + messageBody + '\'' + ", sendDate=" + sendDate
                + ", sendToPhoneNum=" + sendToPhoneNum + ", sendTo=" + sendTo + ", sendBy=" + sendBy + '}';
    }
}
