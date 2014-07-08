package org.spring.management.member.dao;

import java.util.Date;
import java.util.List;

import org.spring.management.member.model.User;
import org.spring.management.member.model.UserSearch;
import org.junit.Before;
import org.junit.Test;
import org.spring.management.member.dao.impl.UserDaoJdbc;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {
    static EmbeddedDatabase database;
    UserDao userDao;
    User user;
    User user1;
    User user2;
    
    static {
        database = new EmbeddedDatabaseBuilder().addDefaultScripts().build();        
    }
    
    @Before
    public void setup() {
        userDao = new UserDaoJdbc(database);
        userDao.deleteAll();
        
        assign_users();
        add_users();
    }
    
    @SuppressWarnings("deprecation")
    private void assign_users() {
        user = new User();
        user.setName("레이나");
        user.setBirthDay(new Date(2014, 7, 5));
        user.setMemberType(User.MEMBER_TYPE.GENERAL_MEMBER);

        user1 = new User();
        user1.setName("나나");
        user1.setBirthDay(new Date(2014, 7, 4));
        user1.setMemberType(User.MEMBER_TYPE.ADMIN);
        
        user2 = new User();
        user2.setName("리지");
        user2.setBirthDay(new Date(2014, 7, 3));
        user2.setMemberType(User.MEMBER_TYPE.SPECIAL_MEMBER);
    }
    
    private void add_users() {
        user.setId(userDao.insert(user));
        user1.setId(userDao.insert(user1));
        user2.setId(userDao.insert(user2));
    }
     
    @Test
    public void test_list_search_name() {
        UserSearch search = new UserSearch();
        search.setSearchName("나나");
        List<User> users = userDao.list(search);
        assertThat(users.size(), is(1));
        
        compare_user(users.get(0), user1);
    }
    
    @Test
    public void test_list_search_member_type() {
        UserSearch search = new UserSearch();
        search.setSearchName("나나");
        search.setSearchMemberType(User.MEMBER_TYPE.SPECIAL_MEMBER);
        
        List<User> users = userDao.list(search);
        assertThat(users.size(), is(0));
    } 
    
    @Test
    public void test_list_search_name_and_member_type() {
        UserSearch search = new UserSearch();
        search.setSearchMemberType(User.MEMBER_TYPE.SPECIAL_MEMBER);
        
        List<User> users = userDao.list(search);
        assertThat(users.size(), is(1));
        compare_user(users.get(0), user2);
    } 
    
    @Test
    public void test_search() {
        User searchedUser = userDao.get(user.getId());
        compare_user(user, searchedUser);
    }
    
    @Test
    public void test_update() {
        user.setName("테스트");
        user.setBirthDay(new Date(2014, 6, 5));
        user.setMemberType(User.MEMBER_TYPE.ADMIN);
        
        userDao.update(user);
        
        User updatedUser = userDao.get(user.getId());
        compare_user(user, updatedUser);
    }
    
    public void compare_user(User left_user, User right_user) {
        assertThat(left_user.getId(), is(right_user.getId()));
        assertThat(left_user.getName(), is(right_user.getName()));
        assertThat(left_user.getBirthDate(), is(right_user.getBirthDate()));
        assertThat(left_user.getMemberType(), is(right_user.getMemberType()));        
    }
    
    @Test(expected=EmptyResultDataAccessException.class)
    public void test_delete() {
        userDao.delete(user);
        
        userDao.get(user.getId());
    }
}
