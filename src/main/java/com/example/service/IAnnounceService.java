
    package com.example.service;

    import com.example.domain.Announce;

    import java.util.List;

    /**
     * @author example
     * @version V1.0
     * @Package com.example.service
     * @date 2020/3/23 下午9:58
     * @Copyright ©
     */
    public interface IAnnounceService {
        List<Announce> findAll(Integer page,Integer pageSize,String name);
        void save(Announce announce);

        Announce findById(String id);

        void update(Announce announce);

        void delete(String id);
    }

    