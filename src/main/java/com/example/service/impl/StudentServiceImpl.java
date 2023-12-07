
    package com.example.service.impl;

    import com.github.pagehelper.PageHelper;
    import com.example.dao.IStudentDao;
    import com.example.domain.Student;
    import com.example.service.IStudentService;
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
    @Service("studentService")
    public class StudentServiceImpl implements IStudentService {
        @Autowired
        private IStudentDao studentDao;
        @Override
        public List<Student> findAll(Integer page,Integer pageSize,String name) {
            PageHelper.startPage(page,pageSize);
            return studentDao.findAll(name);
        }

        @Override
        public void save(Student p) {
            studentDao.save(p);
        }

        @Override
        public Student findById(String id) {
            return studentDao.findById(id);
        }

        @Override
        public void update(Student p) {
            studentDao.update(p);
        }

        @Override
        public void delete(String id) {
            studentDao.delete(id);
        }
    }

    