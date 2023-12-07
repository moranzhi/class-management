
    package com.example.service;

    import com.example.domain.Student;

    import java.util.List;

    /**
     * @author example
     * @version V1.0
     * @Package com.example.service
     * @date 2020/3/23 下午9:58
     * @Copyright ©
     */
    public interface IStudentService {
        List<Student> findAll(Integer page,Integer pageSize,String name);
        void save(Student student);

        Student findById(String id);

        void update(Student student);

        void delete(String id);
    }

    