package com.example.service;


import com.example.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author example
 * @version V1.0
 * @Package com.example.service
 * @date 2020/3/21 下午5:42
 * @Copyright ©
 */
public interface IUserService {

    UserInfo findByUsername(String username);

    List<UserInfo> findAll(Integer page,Integer pageSize);

    void save (UserInfo userInfo) throws Exception;

    UserInfo findById(String id);

}
