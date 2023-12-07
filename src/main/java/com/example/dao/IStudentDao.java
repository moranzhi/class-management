
    package com.example.dao;



    import com.example.domain.Student;
    import org.apache.ibatis.annotations.Delete;
    import org.apache.ibatis.annotations.Insert;
    import org.apache.ibatis.annotations.Select;
    import org.apache.ibatis.annotations.Update;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository("studentDao")
    public interface IStudentDao {
        @Select("select * from tb_student where name like concat(concat('%',#{name}),'%')")
        List<Student> findAll(String name);

        @Insert("insert into tb_student (id,name,sex,classid,phone,address,remark,birthday) values (#{id},#{name},#{sex},#{classid},#{phone},#{address},#{remark},#{birthday})")
        void save(Student student);

        @Select("select * from tb_student where id=#{id}")
        Student findById(String id);

        @Update("update tb_student set name=#{name},sex=#{sex},classid=#{classid},phone=#{phone},address=#{address},remark=#{remark},birthday=#{birthday} where id = #{id}")
        void update(Student student);

        @Delete("delete from tb_student where id=#{id}")
        void delete(String id);
    }
    