package com.balidao.transreport.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.balidao.transreport.common.Constants;
import com.balidao.transreport.common.SMSUtil;
import com.balidao.transreport.dao.IGroupDao;
import com.balidao.transreport.dao.IUserDao;
import com.balidao.transreport.dao.impl.GroupUserDao;
import com.balidao.transreport.domain.Friend;
import com.balidao.transreport.domain.Group;
import com.balidao.transreport.domain.GroupRole;
import com.balidao.transreport.domain.GroupUser;
import com.balidao.transreport.domain.User;
import com.balidao.transreport.dto.GroupDto;
import com.balidao.transreport.dto.GroupUserDto;
import com.balidao.transreport.dto.UserDto;
import com.balidao.transreport.exception.TransreportException;
import com.balidao.transreport.exception.TransreportExceptionType;
import com.balidao.transreport.parse.GroupParser;
import com.balidao.transreport.parse.GroupUserParser;
import com.balidao.transreport.parse.UserParser;
import com.balidao.transreport.service.IUserService;

/**
 * Created by mark on 16-11-30.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    @Qualifier("redisStringTemplate")
    private RedisTemplate<String, String> redisStringTemplate;

    @Autowired
    @Qualifier("redisUserTemplate")
    private RedisTemplate<String, UserDto> redisUserTemplate;

    private ValueOperations<String, String> stringValueOperations;
    private ValueOperations<String, UserDto> userValueOperations;

    @PostConstruct
    private void init() {
        stringValueOperations = redisStringTemplate.opsForValue();
        userValueOperations = redisUserTemplate.opsForValue();
    }

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IGroupDao groupDao;
    @Autowired
    private GroupUserDao groupUserDao;

    @Override
    public String requestValidateCode(String phoneNumber) throws TransreportException {
        String key = Constants.VALIDATE_CODE_REDIS_KEY_PREFIX + phoneNumber;
        if (stringValueOperations.get(key) != null) {
            throw new TransreportException(TransreportExceptionType.VALIDATE_CODE_ALREADY_REQUESTED);
        }
        String validateCode = BigDecimal.valueOf(Math.random() * 9000 + 999).setScale(0, BigDecimal.ROUND_DOWN)
                .toString();
//        System.out.println(phoneNumber + " ==> " + validateCode + "   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        stringValueOperations.set(key, validateCode, 120, TimeUnit.SECONDS);
        SMSUtil.sendValidateCode(phoneNumber, validateCode);
        return validateCode;

    }

    @Override
    @Transactional
    public UserDto login(String phoneNumber, String validateCode) throws TransreportException {
        String validateCodeKey = Constants.VALIDATE_CODE_REDIS_KEY_PREFIX + phoneNumber;
        if (stringValueOperations.get(validateCodeKey) == null) {
            throw new TransreportException(TransreportExceptionType.VALIDATE_CODE_REQUIRED);
        } else if (!stringValueOperations.get(validateCodeKey).equals(validateCode)) {
            throw new TransreportException(TransreportExceptionType.VALIDATE_CODE_NOT_MATCH);
        } else {
            User user = userDao.findByPhoneNumber(phoneNumber);
            if (user == null) {
                user = new User();
                user.setPhoneNumber(phoneNumber);
                user.setRegisterTime(LocalDateTime.now());
                user.setLoginTime(LocalDateTime.now());
                user.setPoints(Constants.INITIAL_USER_POINTS);
                userDao.save(user);
            } else {
                user.setLoginTime(LocalDateTime.now());
            }
            String userSessionKey = Constants.USER_ID_IN_SESSION_KEY_PREFIX + user.getId();
            UserDto userDto = UserParser.fromDomainToDto(user);
            userValueOperations.set(userSessionKey, userDto);
            return userDto;
        }
    }

    @Override
    public UserDto getUserFromRedis(Long userId) throws TransreportException {
        String userSessionKey = Constants.USER_ID_IN_SESSION_KEY_PREFIX + userId;
        UserDto user = userValueOperations.get(userSessionKey);
        if (user == null) {
            throw new TransreportException(TransreportExceptionType.NO_USER_FOUND);
        } else {
            return user;
        }
    }

    @Override
    public UserDto getUserFromDB(Long userId) throws TransreportException {
        User user = userDao.get(userId);
        if (user == null) {
            throw new TransreportException(TransreportExceptionType.NO_USER_FOUND);
        } else {
            UserDto userDto = UserParser.fromDomainToDto(user);
            return userDto;
        }
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) throws TransreportException {
        User user = userDao.get(userDto.getId());
        if (user == null) {
            throw new TransreportException(TransreportExceptionType.NO_USER_FOUND);
        } else {
            user.setUserName(userDto.getUserName());
            user.setUserRole(userDto.getUserRole());
            userDao.save(user);
            return userDto;
        }
    }

    @Override
    public List<GroupUserDto> listGroups(Long userId) {
        User user = userDao.get(userId);
        List<GroupUserDto> groupList = new ArrayList<GroupUserDto>();
        for (GroupUser groupUser : user.getGroups()) {
            if (groupUser.getGroupRole() != GroupRole.INACTIVE) {
                groupList.add(GroupUserParser.fromDomainToDto(groupUser));
            }
        }
        return groupList;
    }

    @Override
    @Transactional
    public UserDto createUser(String phoneNumber) {
        User user = userDao.findByPhoneNumber(phoneNumber);
        if (user == null) {
            user = new User();
            user.setPhoneNumber(phoneNumber);
            user.setRegisterTime(LocalDateTime.now());
            userDao.save(user);
        }
        return UserParser.fromDomainToDto(user);
    }

    @Override
    @Transactional
    public GroupDto inviteUsersToGroup(UserDto masterDto, List<String> phoneList, Long groupId) {
        Group group = groupDao.get(groupId);
        for (String phoneNumber : phoneList) {
            User user = userDao.findByPhoneNumber(phoneNumber);
            if (user == null) {
                user = new User();
                user.setPhoneNumber(phoneNumber);
                user.setRegisterTime(LocalDateTime.now());
                user.setPoints(Constants.INITIAL_USER_POINTS);
                userDao.save(user);
            }
            GroupUser groupUser = groupUserDao.findGroupUser(user.getId(), groupId);
            if (groupUser == null) {
                groupUser = new GroupUser();
                groupUser.setUser(user);
                groupUser.setGroup(group);
            } else if (groupUser.getGroupRole() != GroupRole.INACTIVE) {
                continue;
            }
            groupUser.setGroupRole(GroupRole.MEMBER);
            groupUserDao.save(groupUser);

            // 保存好友
            User master = userDao.get(masterDto.getId());
            List<Friend> friends = master.getFriends();
            Friend friend = new Friend();
            friend.setUser(user);
            friends.add(friend);
            userDao.save(master);
        }
        return GroupParser.fromDomainToDto(group);
    }

    @Override
    @Transactional
    public void removeMemberFromGroup(Long userId, Long groupId) {
        groupUserDao.removeMemberFromUser(userId, groupId);
    }

}
