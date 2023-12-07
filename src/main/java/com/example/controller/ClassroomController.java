
    package com.example.controller;

    import com.github.pagehelper.PageInfo;
    import com.example.domain.Classroom;
    import com.example.entity.Result;
    import com.example.entity.ResultCode;
    import com.example.service.IClassroomService;
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
    public class ClassroomController {
        @Autowired
        private IClassroomService classroomService;

        @GetMapping("/classrooms")
        public Result findAll(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "5") Integer limit,
                              @RequestParam(defaultValue = "") String name) {
            List<Classroom> list = classroomService.findAll(page, limit,name);
            PageInfo pageInfo = new PageInfo(list);
            return new Result(ResultCode.SUCCESS, pageInfo);
        }

        @PostMapping("/classroom")
        public Result save(@RequestBody Classroom p) {
            classroomService.save(p);
            return new Result(ResultCode.SUCCESS);
        }

        @GetMapping("/classroom/{id}")
        public Result findById(@PathVariable("id") String id){
            Classroom p = classroomService.findById(id);
            return new Result(ResultCode.SUCCESS,p);
        }


        @GetMapping("/classroom/del")
        public Result delete(@RequestParam String id){
            classroomService.delete(id);
            return new Result(ResultCode.SUCCESS);
        }

        @PutMapping("/classroom")
        public Result update(@RequestBody Classroom p){
            classroomService.update(p);
            return new Result(ResultCode.SUCCESS);
        }

    }

    