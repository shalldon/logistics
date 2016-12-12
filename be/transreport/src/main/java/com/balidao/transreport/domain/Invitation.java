package com.balidao.transreport.domain;

import org.joda.time.LocalDateTime;

/**
 * Created by james on 16-11-29.
 */
public class Invitation {

    private Long id;
    private User inviter;
    private User invitee;
    private LocalDateTime invitationTime;
    private Enum status;
    //多对一
    private Group Group;

}
