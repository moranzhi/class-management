
    package com.example.controller;

    import com.github.pagehelper.PageInfo;
    import com.example.domain.Teacher;
    import com.example.entity.Result;
    import com.example.entity.ResultCode;
    import com.example.service.ITeacherService;
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
    public class TeacherController {
        @Autowired
        private ITeacherService teacherService;

        @GetMapping("/teachers")
        public Result findAll(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "5") Integer limit,
                              @RequestParam(defaultValue = "") String name) {
            List<Teacher> list = teacherService.findAll(page, limit,name);
            PageInfo pageInfo = new PageInfo(list);
            return new Result(ResultCode.SUCCESS, pageInfo);
        }

        @PostMapping("/teacher")
        public Result save(@RequestBody Teacher p) {
            teacherService.save(p);
            return new Result(ResultCode.SUCCESS);
        }

        @GetMapping("/teacher/{id}")
        public Result findById(@PathVariable("id") String id){
            Teacher p = teacherService.findById(id);
            return new Result(ResultCode.SUCCESS,p);
        }


        @GetMapping("/teacher/del")
        public Result delete(@RequestParam String id){
            teacherService.delete(id);
            return new Result(ResultCode.SUCCESS);
        }

        @PutMapping("/teacher")
        public Result update(@RequestBody Teacher p){
            teacherService.update(p);
            return new Result(ResultCode.SUCCESS);
        }

    }

    