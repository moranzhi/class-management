
    package com.example.service.impl;

    import com.github.pagehelper.PageHelper;
    import com.example.dao.IBookDao;
    import com.example.domain.Book;
    import com.example.service.IBookService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.UUID;

    /**
     * @author example
     * @version V1.0
     * @Package com.example.service.impl
     * @date 2020/3/23 下午9:58
     * @Copyright ©
     */
    @Service("bookService")
    public class BookServiceImpl implements IBookService {
        @Autowired
        private IBookDao bookDao;
        @Override
        public List<Book> findAll(Integer page,Integer pageSize,String name) {
            PageHelper.startPage(page,pageSize);
            return bookDao.findAll(name);
        }

        @Override
        public void save(Book p) {
            bookDao.save(p);
        }

        @Override
        public Book findById(String id) {
            return bookDao.findById(id);
        }

        @Override
        public void update(Book p) {
            bookDao.update(p);
        }

        @Override
        public void delete(String id) {
            bookDao.delete(id);
        }
    }

    