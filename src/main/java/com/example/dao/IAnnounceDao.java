    package com.example.dao;



    import com.example.domain.Announce;
    import org.apache.ibatis.annotations.Delete;
    import org.apache.ibatis.annotations.Insert;
    import org.apache.ibatis.annotations.Select;
    import org.apache.ibatis.annotations.Update;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository("announceDao")
    public interface IAnnounceDao {
        @Select("select * from tb_announce where title like concat(concat('%',#{name}),'%')")
        List<Announce> findAll(String name);

        @Insert("insert into tb_announce (id,title,content,senddate,expiredate,publisher,ispub,remark) values (#{id},#{title},#{content},#{senddate},#{expiredate},#{publisher},#{ispub},#{remark})")
        void save(Announce announce);

        @Select("select * from tb_announce where id=#{id}")
        Announce findById(String id);

        @Update("update tb_announce set title=#{title},content=#{content},senddate=#{senddate},expiredate=#{expiredate},publisher=#{publisher},ispub=#{ispub},remark=#{remark} where id = #{id}")
        void update(Announce announce);

        @Delete("delete from tb_announce where id=#{id}")
        void delete(String id);
    }
    