
package com.sc.jysc.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@ApiModel
@Entity
public class BasicUser implements UserDetails{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @ApiModelProperty("用户Id")
    private String uid;
    @ApiModelProperty("用户账号")
    private String account; //账号
    @ApiModelProperty("用户密码")
    private String password; //密码
    @ApiModelProperty("盐")
    private String salt;
    @ApiModelProperty("标记用户删除")
    private Boolean deleted;
    @ApiModelProperty("")
    private Boolean expired;
    @ApiModelProperty("标记用户锁定")
    private Boolean locked;
    @ApiModelProperty("")
    private Boolean pwdexpired;
    @ApiModelProperty("用户名称")
    private String displayname; //姓名
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("用户所在部门")
    private String department; //单位
    @ApiModelProperty("y用户单位")
    private String company;
    @ApiModelProperty("用户职称")
    private String title;
    @ApiModelProperty("用户办公电话")
    private String phoneoffice;
    @ApiModelProperty("传真")
    private String faxoffice;
    @ApiModelProperty("用户手机号码")
    private String phonemobile;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("")
    private String zip;
    @ApiModelProperty("创建时间")
    private Long createtimestamp;
    @ApiModelProperty("修改时间")
    private Long modifytimestamp;
    @ApiModelProperty("最后一次登录时间")
    private Long lastlogon;
    @ApiModelProperty("最后一次登出时间")
    private Long lastlogoff;
    @ApiModelProperty("最后一次登录的ip")
    private String lastlogonip;
    @ApiModelProperty("")
    private Integer badpwdcount;
    @ApiModelProperty("照片")
    private byte[] photo;

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<BasicRole> roles;

    public List<BasicRole> getRoles() {
        return roles;
    }

    public void setRoles(List<BasicRole> roles) {
        this.roles = roles;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<BasicRole> roles=this.getRoles();
        if(roles!=null){
            for (BasicRole role:roles){
                auths.add(new SimpleGrantedAuthority(role.getRole()));
            }
        }
        return auths;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getPwdexpired() {
        return pwdexpired;
    }

    public void setPwdexpired(Boolean pwdexpired) {
        this.pwdexpired = pwdexpired;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneoffice() {
        return phoneoffice;
    }

    public void setPhoneoffice(String phoneoffice) {
        this.phoneoffice = phoneoffice;
    }

    public String getFaxoffice() {
        return faxoffice;
    }

    public void setFaxoffice(String faxoffice) {
        this.faxoffice = faxoffice;
    }

    public String getPhonemobile() {
        return phonemobile;
    }

    public void setPhonemobile(String phonemobile) {
        this.phonemobile = phonemobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Long getCreatetimestamp() {
        return createtimestamp;
    }

    public void setCreatetimestamp(Long createtimestamp) {
        this.createtimestamp = createtimestamp;
    }

    public Long getModifytimestamp() {
        return modifytimestamp;
    }

    public void setModifytimestamp(Long modifytimestamp) {
        this.modifytimestamp = modifytimestamp;
    }

    public Long getLastlogon() {
        return lastlogon;
    }

    public void setLastlogon(Long lastlogon) {
        this.lastlogon = lastlogon;
    }

    public Long getLastlogoff() {
        return lastlogoff;
    }

    public void setLastlogoff(Long lastlogoff) {
        this.lastlogoff = lastlogoff;
    }

    public String getLastlogonip() {
        return lastlogonip;
    }

    public void setLastlogonip(String lastlogonip) {
        this.lastlogonip = lastlogonip;
    }

    public Integer getBadpwdcount() {
        return badpwdcount;
    }

    public void setBadpwdcount(Integer badpwdcount) {
        this.badpwdcount = badpwdcount;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
