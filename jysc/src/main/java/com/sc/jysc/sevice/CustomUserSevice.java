package com.sc.jysc.sevice;

import com.sc.jysc.entity.BasicUser;
import com.sc.jysc.repository.DefaultUserReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Jackbing on 2017/12/20.
 */
@Service
public class CustomUserSevice implements UserDetailsService{
    @Autowired
    private DefaultUserReposity userReposity;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        BasicUser basicUser=userReposity.findByAccount(s);
        if (basicUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("s:"+s);
        System.out.println("username:"+basicUser.getAccount()+";password:"+basicUser.getPassword());
        return basicUser;
    }

    public BasicUser loadMoreDetailUserByUsername(String s) throws UsernameNotFoundException{
        BasicUser basicUser=userReposity.findByAccount(s);
        if (basicUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("s:"+s);
        System.out.println("username:"+basicUser.getAccount()+";password:"+basicUser.getPassword());
        return basicUser;
    }
}
