
    package com.example.service;

    import com.example.domain.Teacher;

    import java.util.List;

    /**
     * @author example
     * @version V1.0
     * @Package com.example.service
     * @date 2020/3/23 下午9:58
     * @Copyright ©
     */
    public interface ITeacherService {
        List<Teacher> findAll(Integer page,Integer pageSize,String name);
        void save(Teacher teacher);

        Teacher findById(String id);

        void update(Teacher teacher);

        void delete(String id);
    }

    