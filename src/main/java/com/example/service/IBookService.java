
    package com.example.service;

    import com.example.domain.Book;

    import java.util.List;

    /**
     * @author example
     * @version V1.0
     * @Package com.example.service
     * @date 2020/3/23 下午9:58
     * @Copyright ©
     */
    public interface IBookService {
        List<Book> findAll(Integer page,Integer pageSize,String name);
        void save(Book book);

        Book findById(String id);

        void update(Book book);

        void delete(String id);
    }

    