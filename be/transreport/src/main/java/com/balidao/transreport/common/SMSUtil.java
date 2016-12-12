package com.balidao.transreport.common;

import com.balidao.transreport.domain.Group;
import com.balidao.transreport.domain.User;
import com.balidao.transreport.dto.GroupDto;
import com.balidao.transreport.dto.UserDto;

import java.util.List;

public class SMSUtil {

    public static void sendValidateCode(String phoneNumber, String validateCode) {
        System.out.println(phoneNumber + " ==> " + validateCode);
    }

    public static void sendJoinGroup(User user, Group group) {
        System.out.println(user.getUserName() + " requests to join group: " + group.getGroupName());
    }

    public static void sendGroupUserRequestApprovalRequest(Group group, boolean isApprove) {
        System.out.println("Your request to join group: " + group.getGroupName() + " has been "
                + (isApprove ? "approved" : "disapprove"));
    }

    public static void invateUserToGroup(List<String> phoneList, UserDto user, GroupDto group) {
        System.out.println(user.getUserName() + " invate u to join " + group.getGroupName());
    };
}
