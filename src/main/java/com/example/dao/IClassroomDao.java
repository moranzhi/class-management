
    package com.example.dao;



    import com.example.domain.Classroom;
    import org.apache.ibatis.annotations.Delete;
    import org.apache.ibatis.annotations.Insert;
    import org.apache.ibatis.annotations.Select;
    import org.apache.ibatis.annotations.Update;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository("classroomDao")
    public interface IClassroomDao {
        @Select("select * from tb_classroom where classid like concat(concat('%',#{name}),'%')")
        List<Classroom> findAll(String name);

        @Insert("insert into tb_classroom (id,classid,address,status,maxmember,remark) values (#{id},#{classid},#{address},#{status},#{maxmember},#{remark})")
        void save(Classroom classroom);

        @Select("select * from tb_classroom where id=#{id}")
        Classroom findById(String id);

        @Update("update tb_classroom set classid=#{classid},address=#{address},status=#{status},maxmember=#{maxmember},remark=#{remark} where id = #{id}")
        void update(Classroom classroom);

        @Delete("delete from tb_classroom where id=#{id}")
        void delete(String id);
    }
    