
    package com.example.service.impl;

    import com.github.pagehelper.PageHelper;
    import com.example.dao.IClassroomDao;
    import com.example.domain.Classroom;
    import com.example.service.IClassroomService;
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
    @Service("classroomService")
    public class ClassroomServiceImpl implements IClassroomService {
        @Autowired
        private IClassroomDao classroomDao;
        @Override
        public List<Classroom> findAll(Integer page,Integer pageSize,String name) {
            PageHelper.startPage(page,pageSize);
            return classroomDao.findAll(name);
        }

        @Override
        public void save(Classroom p) {
            classroomDao.save(p);
        }

        @Override
        public Classroom findById(String id) {
            return classroomDao.findById(id);
        }

        @Override
        public void update(Classroom p) {
            classroomDao.update(p);
        }

        @Override
        public void delete(String id) {
            classroomDao.delete(id);
        }
    }

    