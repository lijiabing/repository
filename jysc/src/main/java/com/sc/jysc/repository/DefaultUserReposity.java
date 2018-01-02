package com.sc.jysc.repository;

import com.sc.jysc.entity.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jackbing on 2017/12/20.
 */
@Repository
public interface DefaultUserReposity extends JpaRepository<BasicUser,String>{

    BasicUser findByAccount(String account);
}
