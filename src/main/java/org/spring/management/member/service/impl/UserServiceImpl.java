package org.spring.management.member.service.impl;

import java.util.List;

import org.spring.management.member.dao.UserDao;
import org.spring.management.member.model.User;
import org.spring.management.member.model.UserPaging;
import org.spring.management.member.model.UserSearch;
import org.spring.management.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    
    @Override
    public List<User> list(UserSearch search) {
        return this.userDao.list(search);
    }
    
    @Override
    public List<User> listAndGetPaging(UserSearch search, UserPaging userPage) {
        int maxCount = userDao.count(search);
        userPage.setMaxPage((maxCount/userPage.getCountPerPage())+1);
        return this.userDao.list(search, userPage);
    }

    @Override
    public User get(int id) {
        return this.userDao.get(id);
    }

    @Override
    public int insert(User user) {
        return this.userDao.insert(user);
    }

    @Override
    public void update(User user) {
        this.userDao.update(user);
    }

    @Override
    public void delete(User user) {
        this.userDao.delete(user);
    }

}
