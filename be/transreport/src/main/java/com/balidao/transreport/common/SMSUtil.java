package com.balidao.transreport.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.balidao.transreport.domain.Group;
import com.balidao.transreport.domain.User;
import com.balidao.transreport.dto.GroupDto;
import com.balidao.transreport.dto.UserDto;

public class SMSUtil {

    public static void sendValidateCode(String phoneNumber, String validateCode) {
        singleSend("【oTMS传跑】您的验证码是#code#。如非本人操作，请忽略本短信".replaceAll("#code#", validateCode), phoneNumber);
    }

    public static void sendJoinGroup(User user, Group group) {
        System.out.println(user.getUserName() + " requests to join group: " + group.getGroupName());
    }

    public static void sendGroupUserRequestApprovalRequest(Group group, boolean isApprove) {
        System.out.println("Your request to join group: " + group.getGroupName() + " has been "
                + (isApprove ? "approved" : "disapprove"));
    }

    public static void inviteUserToGroup(List<String> phoneList, UserDto user, GroupDto group) {
        String username = user.getUserName() != null ? user.getUserName() : user.getPhoneNumber();
        for (String phone : phoneList) {
            singleSend("【oTMS传跑】#str1#邀请您加入[传跑]，组号：#str2#点这里加入：http://chuanpao.otms.com".replaceAll("#str1#", username)
                    .replaceAll("#str2#", group.getGroupName() + " "), phone);
        }
    };

    public static void reportPosition(List<String> phoneList, String userName) {
        for (String phone : phoneList) {
            singleSend("【oTMS传跑】#str1#向您发出位置邀请，点这里上传位置：http://chuanpao.otms.com".replaceAll("#str1#", userName), phone);
        }
    }

    private static String singleSend(String text, String mobile) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", "c11478fce383e36dee3cdb8bf737adce");
        params.put("text", text);
        params.put("mobile", mobile);
        return post("https://sms.yunpian.com/v2/sms/single_send.json", params);
    }

    private static String post(String url, Map<String, String> params) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;

        HttpPost post = postForm(url, params);

        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();
        
        System.out.println("================================");
        System.out.println("sending message to: " + params.get("mobile"));
        System.out.println(params.get("text"));
        System.out.println(body);
        System.out.println("================================");
        return body;
    }

    private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost) {

        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);

        return body;
    }

    private static String paseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        try {
            body = EntityUtils.toString(entity);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

    private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost) {
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static HttpPost postForm(String url, Map<String, String> params) {

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httpost;
    }
}
