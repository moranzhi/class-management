
    package com.example.dao;



    import com.example.domain.Teacher;
    import org.apache.ibatis.annotations.Delete;
    import org.apache.ibatis.annotations.Insert;
    import org.apache.ibatis.annotations.Select;
    import org.apache.ibatis.annotations.Update;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository("teacherDao")
    public interface ITeacherDao {
        @Select("select * from tb_teacher where name like concat(concat('%',#{name}),'%')")
        List<Teacher> findAll(String name);

        @Insert("insert into tb_teacher (id,name,sex,nick,money,remark) values (#{id},#{name},#{sex},#{nick},#{money},#{remark})")
        void save(Teacher teacher);

        @Select("select * from tb_teacher where id=#{id}")
        Teacher findById(String id);

        @Update("update tb_teacher set name=#{name},sex=#{sex},nick=#{nick},money=#{money},remark=#{remark} where id = #{id}")
        void update(Teacher teacher);

        @Delete("delete from tb_teacher where id=#{id}")
        void delete(String id);
    }
    