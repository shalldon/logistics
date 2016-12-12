package com.balidao.transreport.dao;

import com.balidao.transreport.domain.User;

/**
 * Created by mark on 16-11-30.
 */
public interface IUserDao extends IBaseDao<User> {

    User findByPhoneNumber(String phoneNumber);
}
