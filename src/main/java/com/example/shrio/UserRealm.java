package com.example.shrio;

import com.example.domain.UserInfo;
import com.example.entity.JWTToken;
import com.example.service.IUserService;
import com.example.utils.JwtUtil;
import com.example.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author example
 * @version V1.0
 * @Package com.example.shrio
 * @date 2020/3/28 下午6:45
 * @Copyright ©
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void setName(String name) {
        super.setName("userRealm");
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取安全数据
//        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
//        String up = (String) principalCollection.getPrimaryPrincipal();
//        UserInfo userInfo = (UserInfo) redisUtil.get(up);
        //2.获取权限信息
//        Set<Role> roles = userInfo.getRoles();
//        Set<String> ps = new HashSet<>();
//        for (Role role : roles) {
//            List<Permission> permissions = role.getPermissions();
//            for (Permission permission : permissions) {
//                ps.add(permission.getUrl());
//            }
//        }
//        System.out.println(ps);

//        Set<String> rs = new HashSet<>();
//        for (Role role : roles) {
//            rs.add(role.getRoleName());
//        }
        //3.构造权限数据，返回值
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setRoles(rs);
//        info.setStringPermissions(ps);
//        return info;
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        Claims claims = jwtUtil.parseJwt(token);
        String username = (String) claims.get("username");
        String password = (String) claims.get("password");

        UserInfo user = userService.findByUsername(username);
        if(user.getPassword().equals(password)){
//            return new SimpleAuthenticationInfo(user, token, user.getUsername());
            redisUtil.set(username+password,user);
            return new SimpleAuthenticationInfo(username+password, token, user.getUsername());
        }
        return null;
    }

}

