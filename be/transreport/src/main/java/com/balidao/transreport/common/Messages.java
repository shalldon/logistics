package com.balidao.transreport.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class Messages {

    public static String VALIDATE_CODE_ALREADY_REQUESTED;

    public static String VALIDATE_CODE_REQUIRED;

    public static String VALIDATE_CODE_NOT_MATCH;

    public static String NO_USER_FOUND;

    // MessageLog
    public static String SAVE_MESSAGE_LOG_ERROR;
    public static String NO_MESSAGE_LOG_FOUND;

    // Group
    public static String SAVE_GROUP_ERROR;
    public static String SAVE_GROUP_SUCCESS;
    public static String UPDATE_GROUP_ERROR;
    public static String UPDATE_GROUP_SUCCESS;
    public static String NO_GROUP_FOUND;
    public static String NO_GROUP_NAME;

    // UserRole
    public static String USER_ROLE_SHIPPER;
    public static String USER_ROLE_CARRIER;
    public static String USER_ROLE_RECEIVER;

    // GroupRole
    public static String GROUP_ROLE_MASTER;
    public static String GROUP_ROLE_MEMBER;
    public static String GROUP_ROLE_PENDINGFORAPPROVE;
    public static String GROUP_ROLE_INACTIVE;

    // GroupUser
    public static String ALREADY_JOINED_GROUP;
    public static String NO_JOIN_GROUP_REQUEST_FOUND;
    public static String NO_GROUP_USER_FOUND;

    // Chat Event
    public static String CHAT_EVENT_TYPE_TEXT;
    public static String CHAT_EVENT_TYPE_RED_ENVELOPE;
    public static String CHAT_EVENT_TYPE_REPORT_POSITION;
    public static String RED_ENVELOPE_TYPE_QUEST;
    public static String RED_ENVELOPE_TYPE_ORDINARY;
    public static String RED_ENVELOPE_RULE_DIVIDE_EQUALLY;
    public static String RED_ENVELOPE_RULE_RANDOM;
    public static String RED_ENVELOPE_RULE_FIRST_COME_GET_MORE;
    public static String NOT_ENOUGH_POINTS;
    public static String ALREADY_PICKED_RED_ENVELOPE;
    public static String EXPIRED_RED_ENVELOPE;
    public static String NO_RED_ENVELOPE_LEFT;
    public static String NO_NEED_TO_REPORT_POSITION;
    
    // Privilege
    public static String NO_PRIVILEGE;

    // Session
    public static String SESSION_FAILED;

    private Properties messagesProp = new Properties();

    @PostConstruct
    public void init() {
        initLocale();
        VALIDATE_CODE_ALREADY_REQUESTED = getMessage("validate.code.already.request");
        VALIDATE_CODE_REQUIRED = getMessage("validate.code.required");
        VALIDATE_CODE_NOT_MATCH = getMessage("validate.code.not.match");
        NO_USER_FOUND = getMessage("no.user.found");

        SAVE_MESSAGE_LOG_ERROR = getMessage("save.message.log.error");
        NO_MESSAGE_LOG_FOUND = getMessage("no.message.log.found");

        SAVE_GROUP_ERROR = getMessage("save.group.error");
        SAVE_GROUP_SUCCESS = getMessage("save.group.success");
        UPDATE_GROUP_ERROR = getMessage("update.group.error");
        UPDATE_GROUP_SUCCESS = getMessage("update.group.success");
        NO_GROUP_FOUND = getMessage("no.group.found");
        NO_GROUP_NAME = getMessage("no.group.name");

        USER_ROLE_SHIPPER = getMessage("user.role.shipper");
        USER_ROLE_CARRIER = getMessage("user.role.carrier");
        USER_ROLE_RECEIVER = getMessage("user.role.receiver");

        GROUP_ROLE_MASTER = getMessage("group.role.master");
        GROUP_ROLE_MEMBER = getMessage("group.role.member");
        GROUP_ROLE_PENDINGFORAPPROVE = getMessage("group.role.pendingforapprove");
        GROUP_ROLE_INACTIVE = getMessage("group.role.inactive");

        ALREADY_JOINED_GROUP = getMessage("groupuser.already.joined");
        NO_JOIN_GROUP_REQUEST_FOUND = getMessage("groupuser.no.join.group.request.found");
        NO_GROUP_USER_FOUND = getMessage("groupuser.no.group.user.found");

        CHAT_EVENT_TYPE_TEXT = getMessage("chat.event.type.text");
        CHAT_EVENT_TYPE_RED_ENVELOPE = getMessage("chat.event.type.red.envelope");
        CHAT_EVENT_TYPE_REPORT_POSITION = getMessage("chat.event.type.report.position");
        
        RED_ENVELOPE_TYPE_QUEST = getMessage("red.envelope.type.quest");
        RED_ENVELOPE_TYPE_ORDINARY = getMessage("red.envelope.type.ordinary");
        
        RED_ENVELOPE_RULE_DIVIDE_EQUALLY = getMessage("red.envelope.rule.divide.equally");
        RED_ENVELOPE_RULE_RANDOM = getMessage("red.envelope.rule.random");
        RED_ENVELOPE_RULE_FIRST_COME_GET_MORE = getMessage("red.envelope.rule.first.come.get.more");
        NOT_ENOUGH_POINTS = getMessage("not.enough.points");
        ALREADY_PICKED_RED_ENVELOPE = getMessage("already.picked.red.envelope");
        EXPIRED_RED_ENVELOPE = getMessage("expired.red.envelope");
        NO_RED_ENVELOPE_LEFT = getMessage("no.red.envelope.left");
        NO_NEED_TO_REPORT_POSITION = getMessage("no.need.to.report.position");
        
        NO_PRIVILEGE = getMessage("permission.noprivilege");
    }

    private String getMessage(String messageKey) {
        String value = messagesProp.getProperty(messageKey);
        if (value == null || value.trim().equals("")) {
            return messageKey;
        }
        return value;
    }

    private void initLocale() {
        try {
            messagesProp.load(new InputStreamReader(
                    Messages.class.getClassLoader().getResourceAsStream("resource/locale.properties"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
