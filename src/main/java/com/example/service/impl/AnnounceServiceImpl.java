
    package com.example.service.impl;

    import com.github.pagehelper.PageHelper;
    import com.example.dao.IAnnounceDao;
    import com.example.domain.Announce;
    import com.example.service.IAnnounceService;
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
    @Service("announceService")
    public class AnnounceServiceImpl implements IAnnounceService {
        @Autowired
        private IAnnounceDao announceDao;
        @Override
        public List<Announce> findAll(Integer page,Integer pageSize,String name) {
            PageHelper.startPage(page,pageSize);
            return announceDao.findAll(name);
        }

        @Override
        public void save(Announce p) {
            announceDao.save(p);
        }

        @Override
        public Announce findById(String id) {
            return announceDao.findById(id);
        }

        @Override
        public void update(Announce p) {
            announceDao.update(p);
        }

        @Override
        public void delete(String id) {
            announceDao.delete(id);
        }
    }

    