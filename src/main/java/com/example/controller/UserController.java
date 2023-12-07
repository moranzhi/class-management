package com.example.controller;

import com.github.pagehelper.PageInfo;
import com.example.domain.UserInfo;
import com.example.entity.JWTToken;
import com.example.entity.Result;
import com.example.entity.ResultCode;
import com.example.service.IUserService;
import com.example.utils.JwtUtil;
import com.example.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author example
 * @version V1.0
 * @Package com.example.controller
 * @date 2020/3/21 下午5:37
 * @Copyright ©
 */
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/user/login")
    public Result login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        password = new Md5Hash(password, username, 3).toString();
        System.out.println(password);
        Map<String, Object> m = new HashMap<>();
        m.put("username", username);
        m.put("password", password);
//        UserInfo userInfo = userService.findByUsername(username);
//        if (userInfo.getPassword().equals(password)) {
        String token = jwtUtil.createJwt(null, username, m);
        JWTToken jwtToken = new JWTToken(token);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(jwtToken);
//            System.out.println("token:" + token);
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", token);
            return new Result(ResultCode.SUCCESS, tokenMap);
        } catch (Exception e) {
            return new Result(ResultCode.USERNAME_PASSWORD_ERROR);
        }
//        } else {
//            return new Result(ResultCode.USERNAME_PASSWORD_ERROR);
//        }


//        if (userInfo.getPassword().equals(password)) {
//            Map<String, Object> m = new HashMap<>();
//            m.put("userId", userInfo.getId());
//            String token = jwtUtil.createJwt(userInfo.getId(), username, m);
//            System.out.println("token:" + token);
//            Map<String, String> tokenMap = new HashMap<>();
//            tokenMap.put("token", token);
//            return new Result(ResultCode.SUCCESS, tokenMap);
//        } else {
//            return new Result(ResultCode.USERNAME_PASSWORD_ERROR);
//        }
    }

    @GetMapping("/user/info")
    public Result info(@RequestParam("token") String token) {
        // 获取session中的安全数据
//        Subject subject = SecurityUtils.getSubject();
        //1.subject获取所有的安全数据集合
//        PrincipalCollection principals = subject.getPrincipals();
        // 获取安全数据
//        UserInfo result = (UserInfo) principals.getPrimaryPrincipal();
//        String up = (String) principals.getPrimaryPrincipal();
//        System.out.println(up);
//        UserInfo result = (UserInfo) redisUtil.get(up);
//        System.out.println(result);
        System.out.println("aaaaaaa");
        Claims claims = jwtUtil.parseJwt(token);
        System.out.println(claims);
        System.out.println(claims.get("username"));
        UserInfo userInfo = userService.findById((String) claims.get("username"));
//        request.getSession().setAttribute("username",userInfo.getUsername());
        userInfo.setPassword("");
//        return new Result(ResultCode.SUCCESS, result);
        return new Result(ResultCode.SUCCESS, userInfo);
    }
    @PostMapping("/user/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAIL);
        }
    }

    @GetMapping("/users")
//    @RequiresPermissions("/user/findAll")
    public Result findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        List<UserInfo> list = userService.findAll(page, limit);
        PageInfo pageInfo = new PageInfo(list);
//        Map<String,Object> map = new HashMap<>();
//        map.put("list",list);
//        map.put("total",list.size());
        return new Result(ResultCode.SUCCESS, pageInfo);
    }

    @PostMapping("/user")
//    @RequiresPermissions("/user/save")
    public Result save(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo);
        try {
            userService.save(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.INFORMATION_NOT_STANDARD);
        }
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/user/upload")
//    @RequiresPermissions("/user/upload")
    public Result upload(@RequestParam(name = "file") MultipartFile file) {
        // 保存后，获取到图片地址回显
        String encode = null;
        try {
            encode = "data:image/png;base64," + Base64.encode(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SUCCESS, encode);
    }

    @GetMapping("/user/{id}")
//    @RequiresPermissions("/user/findById")
    public Result findById(@PathVariable("id") String id) {
        return new Result(ResultCode.SUCCESS, userService.findById(id));
    }


    @GetMapping("/unauthorized")
    public Result unauthorized(){
        return new Result(ResultCode.UNAUTHORISE);
    }
    @GetMapping("/unauthenticated")
    public Result unauthenticated(){
        return new Result(ResultCode.UNAUTHENTICATED);
    }
//    @PutMapping("/user/{id}")
//    public Result update(@PathVariable("id") Integer id,@RequestBody UserInfo userInfo){
//        userInfo.setId(id);
//        userService.update(userInfo);
//        return new Result(ResultCode.SUCCESS);
//    }
//
//    @DeleteMapping("/user/{id}")
//    public Result deleteById(@PathVariable("id") Integer id){
//        userService.deleteById(id);
//        return new Result(ResultCode.SUCCESS);
//    }

}

