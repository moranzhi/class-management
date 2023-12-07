
    package com.example.service.impl;

    import com.github.pagehelper.PageHelper;
    import com.example.dao.ITeacherDao;
    import com.example.domain.Teacher;
    import com.example.service.ITeacherService;
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
    @Service("teacherService")
    public class TeacherServiceImpl implements ITeacherService {
        @Autowired
        private ITeacherDao teacherDao;
        @Override
        public List<Teacher> findAll(Integer page,Integer pageSize,String name) {
            PageHelper.startPage(page,pageSize);
            return teacherDao.findAll(name);
        }

        @Override
        public void save(Teacher p) {
            teacherDao.save(p);
        }

        @Override
        public Teacher findById(String id) {
            return teacherDao.findById(id);
        }

        @Override
        public void update(Teacher p) {
            teacherDao.update(p);
        }

        @Override
        public void delete(String id) {
            teacherDao.delete(id);
        }
    }

    