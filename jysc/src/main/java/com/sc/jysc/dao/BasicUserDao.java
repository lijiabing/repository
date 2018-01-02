package com.sc.jysc.dao;

import com.sc.jysc.util.CurrentPage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jackbing on 2017/12/26.
 */
public interface BasicUserDao<T> {


     void createUser(T t);

     void update(T t);

     void deleteUser(String uid);

     /**
      * 通过用户id和密码获取用户
      * @param uid
      * @param password
      * @return
      */
     T getUser(String uid,String password);

     List<T> getAllUsers();

     /**
      * 分页获取所有用户
      * @param pageNum
      * @param pageSize
      * @return
      */
     CurrentPage<T> getUsers(int pageNum,int pageSize);

     /**
      *
      * @param key 单个用户属性
      * @param value 属性值
      * @return
      */
     CurrentPage<T> getUserLike(String key,String value);

     /**
      *
      * @param value 属性值
      * @param keys 多个用户属性
      * @return
      */
     CurrentPage<T> getUserMultiLike(String value,String... keys);

     void changePassword(String currentUser,String oldPassword,String newPassword);



}
