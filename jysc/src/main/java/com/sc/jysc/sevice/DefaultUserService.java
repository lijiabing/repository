package com.sc.jysc.sevice;

import com.sc.jysc.dao.BasicUserDao;
import com.sc.jysc.entity.BasicUser;
import com.sc.jysc.util.CurrentPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jackbing on 2017/12/27.
 */
@Service
public class DefaultUserService {


    @Autowired
    @Qualifier("basicuserdao")
    private BasicUserDao<BasicUser> basicUserDao;

    public CurrentPage<BasicUser> getUsers(int pageNum,int pageSize){
        return basicUserDao.getUsers(pageNum,pageSize);
    }
}
