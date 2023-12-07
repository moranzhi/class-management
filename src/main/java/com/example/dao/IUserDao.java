package com.example.dao;


import com.example.domain.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author example
 * @version V1.0
 * @Package PACKAGE_NAME
 * @date 2020/3/21 下午6:49
 * @Copyright ©
 */
@Repository("userDao")
public interface IUserDao {
    @Select("select * from tb_user where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id")
    }
    )
    UserInfo findByUsername(String username);

    //    @Select("select id,email,username,phone_num,status from tb_user limit #{arg0},#{arg1}")
//    List<UserInfo> findAll(Integer pageLimit,Integer pageSize);
    @Select("select * from tb_user")
    List<UserInfo> findAll();

    @Insert("insert into tb_user(id,email,username,password,phone_num,status,avatar) values(#{id},#{email},#{username},#{password},#{phoneNum},#{status},#{avatar})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select * from tb_user where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id")
    })
    UserInfo findById(String username);



    void update(UserInfo user);
}
