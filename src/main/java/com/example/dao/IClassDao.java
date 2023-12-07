package com.example.dao;



import com.example.domain.Class;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("classDao")
public interface IClassDao {
    @Select("select * from tb_class where classname like concat(concat('%',#{name}),'%')")
    List<Class> findAll(String name);

    @Insert("insert into tb_class (id,classname,mambernum,teacher,opentime,classstatus) values (#{id},#{classname},#{mambernum},#{teacher},#{opentime},#{classstatus})")
    void save(Class c);


    @Select("select * from tb_class where id=#{id}")
    Class findById(String id);

    @Update("update tb_class set classname=#{classname},mambernum=#{mambernum},teacher=#{teacher},opentime=#{opentime},classstatus=#{classstatus} where id = #{id}")
    void update(Class c);

    @Delete("delete from tb_class where id=#{id}")
    void delete(String id);
}