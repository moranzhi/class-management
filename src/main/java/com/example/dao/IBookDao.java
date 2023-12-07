
    package com.example.dao;



    import com.example.domain.Book;
    import org.apache.ibatis.annotations.Delete;
    import org.apache.ibatis.annotations.Insert;
    import org.apache.ibatis.annotations.Select;
    import org.apache.ibatis.annotations.Update;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository("bookDao")
    public interface IBookDao {
        @Select("select * from tb_book where bookname like concat(concat('%',#{name}),'%')")
        List<Book> findAll(String name);

        @Insert("insert into tb_book (id,bookname,bookclass,price,num,buydate,remark) values (#{id},#{bookname},#{bookclass},#{price},#{num},#{buydate},#{remark})")
        void save(Book book);

        @Select("select * from tb_book where id=#{id}")
        Book findById(String id);

        @Update("update tb_book set bookname=#{bookname},bookclass=#{bookclass},price=#{price},num=#{num},buydate=#{buydate},remark=#{remark} where id = #{id}")
        void update(Book book);

        @Delete("delete from tb_book where id=#{id}")
        void delete(String id);
    }
    