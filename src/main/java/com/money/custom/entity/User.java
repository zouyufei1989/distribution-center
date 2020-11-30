package com.money.custom.entity;

import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.MD5Utils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class User extends BaseEntity implements UserDetails {

    /**
     *
     */
    private int id;
    private String passengerId;
    private String password;
    private String username;
    private String nickName;
    private Integer roleId;

    private String roleName;
    private String lastLogin;
    private Integer isEnroll;
    private String cityCode;
    private String cityName;
    private Double cityLng;
    private Double cityLat;
    private Integer errPwdCount;
    private String itCode;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", passengerId='" + passengerId + '\'' +
                ", username='" + username + '\'' +
                ", roleId=" + roleId +
                ", status=" + getStatus() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return username.equals(user.username);
    }


    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public String getItCode() {
        return itCode;
    }

    public void setItCode(String itCode) {
        this.itCode = itCode;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public Double getCityLng() {
        return cityLng;
    }

    public void setCityLng(Double cityLng) {
        this.cityLng = cityLng;
    }

    public Double getCityLat() {
        return cityLat;
    }

    public void setCityLat(Double cityLat) {
        this.cityLat = cityLat;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getErrPwdCount() {
        return errPwdCount;
    }

    public void setErrPwdCount(Integer errPwdCount) {
        this.errPwdCount = errPwdCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return getStatus().equals(CommonStatusEnum.ENABLE.getValue());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getIsEnroll() {
        return isEnroll;
    }

    public void setIsEnroll(Integer isEnroll) {
        this.isEnroll = isEnroll;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public boolean pwdMatch(String pwd) {
        return MD5Utils.getMD5(pwd).equals(getPassword());
    }

}
