package com.balidao.transreport.dao.impl;

import org.springframework.stereotype.Repository;

import com.balidao.transreport.dao.IUserDao;
import com.balidao.transreport.domain.User;

/**
 * Created by mark on 16-11-30.
 */
@Repository
public class UserDao extends BaseDaoImpl<User> implements IUserDao {

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return (User)findOneObject("from User where phoneNumber = ?", phoneNumber);
    }


}
