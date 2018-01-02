package com.sc.jysc.dao;

import com.sc.jysc.entity.BasicUser;
import com.sc.jysc.util.CurrentPage;
import com.sc.jysc.util.PaginationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jackbing on 2017/12/26.
 */
@Service("basicuserdao")
public class DefaultUserDao implements BasicUserDao<BasicUser>{

    @Autowired
    private JdbcTemplate jt;
    private PaginationHelper<BasicUser> helper=new PaginationHelper<BasicUser>();


    @Value("${sc.user.table-name:basic_user}")
    private String userTableName;
    @Value("${sc.user.uid-column:uid}")
    private String uidColumn;
    @Value("${sc.user.password-column:password}")
    private String passwordColumn;

    @Override
    public void createUser(BasicUser basicUser) {

    }

    @Override
    public void update(BasicUser basicUser) {

    }

    @Override
    public void deleteUser(String uid) {

    }

    @Override
    public BasicUser getUser(String uid, String password) {
        return null;
    }

    @Override
    public List<BasicUser> getAllUsers() {
        return null;
    }

    @Override
    public CurrentPage<BasicUser> getUsers(int pageNum, int pageSize) {
        String sql=String.format("select * from %s",this.userTableName);
        CurrentPage<BasicUser> currentPage=helper.fetchPage(sql,new Object[]{},jt,new BeanPropertyRowMapper(BasicUser.class),pageNum,pageSize);
        return currentPage;
    }

    @Override
    public CurrentPage<BasicUser> getUserLike(String key, String value) {
        return null;
    }

    @Override
    public CurrentPage<BasicUser> getUserMultiLike(String value, String... keys) {
        return null;
    }

    @Override
    public void changePassword(String currentUser, String oldPassword, String newPassword) {

    }
}
