package org.spring.management.member.dao;

import java.util.List;

import org.spring.management.member.model.User;
import org.spring.management.member.model.UserPaging;
import org.spring.management.member.model.UserSearch;

public interface UserDao {
    public List<User> list();
    public List<User> list(UserSearch search);
    public int count(UserSearch search); 
    public List<User> list(UserSearch search, UserPaging paging);
    public User get(int id);
    public int insert(User user);
    public void update(User user);
    public void delete(User user);
    public void deleteAll();
}
