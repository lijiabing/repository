package com.sc.jysc.authenticate;

import com.sc.jysc.config.DefaultServiceException;
import com.sc.jysc.entity.BasicUser;
import com.sc.jysc.sevice.CustomUserSevice;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by Jackbing on 2017/12/20.
 */
@Component
public class SCAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private SaltSource saltSource;
    private String userNotFoundEncodedPassword;
    @Autowired
    CustomUserSevice userSevice;
    private PasswordEncoder passwordEncoder;
    public SCAuthenticationProvider(){
        this.setPasswordEncoder((PasswordEncoder)(new PlaintextPasswordEncoder()));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object salt = null;

        BasicUser basicUser=this.userSevice.loadMoreDetailUserByUsername(userDetails.getUsername());
        if(basicUser.getSalt()==null){
            this.logger.debug("salt is null");
            throw new InternalAuthenticationServiceException("salt must not be null");
        }else{
            salt=basicUser.getSalt();
        }
        if(authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String presentedPassword = rawPasswordToDigest(authentication.getCredentials().toString(),salt);
            if(!userDetails.getPassword().equals(presentedPassword)) {
                this.logger.debug("Authentication failed: password does not match stored value");
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
        }
    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        UserDetails loadedUser;
        try {
            loadedUser = this.getUserSevice().loadUserByUsername(s);
        } catch (UsernameNotFoundException var6) {
            if(usernamePasswordAuthenticationToken.getCredentials() != null) {
                String presentedPassword = usernamePasswordAuthenticationToken.getCredentials().toString();
                this.passwordEncoder.isPasswordValid(this.userNotFoundEncodedPassword, presentedPassword, (Object)null);
            }

            throw var6;
        } catch (Exception var7) {
            throw new InternalAuthenticationServiceException(var7.getMessage(), var7);
        }

        if(loadedUser == null) {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        } else {
            return loadedUser;
        }




    }



    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.userNotFoundEncodedPassword = passwordEncoder.encodePassword("userNotFoundPassword", (Object)null);
        this.passwordEncoder = passwordEncoder;
    }

    public SaltSource getSaltSource() {
        return saltSource;
    }

    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    public String getUserNotFoundEncodedPassword() {
        return userNotFoundEncodedPassword;
    }

    public void setUserNotFoundEncodedPassword(String userNotFoundEncodedPassword) {
        this.userNotFoundEncodedPassword = userNotFoundEncodedPassword;
    }

    public CustomUserSevice getUserSevice() {
        return userSevice;
    }

    public void setUserSevice(CustomUserSevice userSevice) {
        this.userSevice = userSevice;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public static String rawPasswordToDigest(String rawPassword, Object salt) {
        return DigestUtils.sha256Hex(DigestUtils.sha256Hex(rawPassword + salt));
    }
}
