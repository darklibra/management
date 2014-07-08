package org.spring.management.member.service;

import java.util.List;

import org.spring.management.member.model.User;
import org.spring.management.member.model.UserPaging;
import org.spring.management.member.model.UserSearch;

public interface UserService {
    public List<User> list(UserSearch search);
    public List<User> listAndGetPaging(UserSearch search, UserPaging userPage);
    public User get(int id);
    public int insert(User user);
    public void update(User user);
    public void delete(User user);
}
