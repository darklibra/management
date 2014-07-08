package org.spring.management.member.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.spring.management.member.dao.UserDao;
import org.spring.management.member.model.User;
import org.spring.management.member.model.UserPaging;
import org.spring.management.member.model.UserSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class UserDaoJdbc implements UserDao {
    JdbcTemplate jdbcTemplate;
    RowMapper<User> mapper;

    {
        mapper = new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("NAME"));
                user.setBirthDay(rs.getDate("BIRTH_DATE"));
                user.setMemberType(User.MEMBER_TYPE.valueOf(rs.getString("MEMBER_TYPE")));
                return user;
            }
        };
    }
    
    @Autowired
    public UserDaoJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }
    
    @Override
    public List<User> list() {
        return this.jdbcTemplate.query("select * from user order by id desc", mapper);
    }
    
    @Override
    public List<User> list(UserSearch search) {
        return this.jdbcTemplate.query("select * from user where name like ? and member_type like ? order by id desc", mapper
                , "%"+search.getSearchName()+"%", "%"+search.getSearchMemberType().getValue()+"%");
    }

    @Override
    public int count(UserSearch search) {
        return this.jdbcTemplate.queryForInt("select count(*) from user where name like ? and member_type like ?"
                , "%"+search.getSearchName()+"%", "%"+search.getSearchMemberType().getValue()+"%");    
    }
    
    @Override
    public List<User> list(UserSearch search, UserPaging paging) {
        int sPage = (paging.getCurrentPage() - 1) * paging.getCountPerPage();
        int ePage = paging.getCountPerPage();
        
        return this.jdbcTemplate.query("select limit ? ? * from user where name like ? and member_type like ? order by id desc", mapper
                , sPage, ePage, "%"+search.getSearchName()+"%", "%"+search.getSearchMemberType().getValue()+"%");
    }
    
    @Override
    public User get(int id) {
        return this.jdbcTemplate.queryForObject("select * from user where id=?", new Object[] {id}, mapper);
    }
   
    @Override
    public int insert(final User user) {
        final String sql = "insert into user(name, birth_date, member_type) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setDate(2, new Date(user.getBirthDate().getTime()));
                ps.setString(3, user.getMemberType().getValue());
                
                return ps;
            }
        };
        
        jdbcTemplate.update(psc, keyHolder);
        
        Number key = keyHolder.getKey();
        
        return key.intValue();
    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update("update user set name=?, birth_date=?, member_type=? where id = ?"
                , user.getName(), user.getBirthDate(), user.getMemberType().getValue(), user.getId());
    }

    @Override
    public void delete(User user) {
        this.jdbcTemplate.update("delete from user where id=?", user.getId());
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update("delete from user");
    }
}
