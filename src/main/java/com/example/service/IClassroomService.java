
    package com.example.service;

    import com.example.domain.Classroom;

    import java.util.List;

    /**
     * @author example
     * @version V1.0
     * @Package com.example.service
     * @date 2020/3/23 下午9:58
     * @Copyright ©
     */
    public interface IClassroomService {
        List<Classroom> findAll(Integer page,Integer pageSize,String name);
        void save(Classroom classroom);

        Classroom findById(String id);

        void update(Classroom classroom);

        void delete(String id);
    }

    