
    package com.example.controller;

    import com.github.pagehelper.PageInfo;
    import com.example.domain.Announce;
    import com.example.entity.Result;
    import com.example.entity.ResultCode;
    import com.example.service.IAnnounceService;
    import com.example.utils.DateUtil;
    import org.apache.shiro.authz.annotation.RequiresPermissions;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.PutMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.List;

    /**
     * @author example
     * @version V1.0
     * @Package com.example.controller
     * @date 2020/3/23 下午9:56
     * @Copyright ©
     */
    @RestController
    public class AnnounceController {
        @Autowired
        private IAnnounceService announceService;

        @GetMapping("/announces")
        public Result findAll(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "5") Integer limit,
                              @RequestParam(defaultValue = "") String name) {
            List<Announce> list = announceService.findAll(page, limit,name);
            PageInfo pageInfo = new PageInfo(list);
            return new Result(ResultCode.SUCCESS, pageInfo);
        }

        @PostMapping("/announce")
        public Result save(@RequestBody Announce p) {
            announceService.save(p);
            return new Result(ResultCode.SUCCESS);
        }

        @GetMapping("/announce/{id}")
        public Result findById(@PathVariable("id") String id){
            Announce p = announceService.findById(id);
            return new Result(ResultCode.SUCCESS,p);
        }


        @GetMapping("/announce/del")
        public Result delete(@RequestParam String id){
            announceService.delete(id);
            return new Result(ResultCode.SUCCESS);
        }

        @PutMapping("/announce")
        public Result update(@RequestBody Announce p){
            announceService.update(p);
            return new Result(ResultCode.SUCCESS);
        }

    }

    