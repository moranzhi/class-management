
    package com.example.controller;

    import com.github.pagehelper.PageInfo;
    import com.example.domain.Student;
    import com.example.entity.Result;
    import com.example.entity.ResultCode;
    import com.example.service.IStudentService;
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
    public class StudentController {
        @Autowired
        private IStudentService studentService;

        @GetMapping("/students")
        public Result findAll(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "5") Integer limit,
                              @RequestParam(defaultValue = "") String name) {
            List<Student> list = studentService.findAll(page, limit, name);
            PageInfo pageInfo = new PageInfo(list);
            return new Result(ResultCode.SUCCESS, pageInfo);
        }

        @PostMapping("/student")
        public Result save(@RequestBody Student p) {
            studentService.save(p);
            return new Result(ResultCode.SUCCESS);
        }

        @GetMapping("/student/{id}")
        public Result findById(@PathVariable("id") String id){
            Student p = studentService.findById(id);
            return new Result(ResultCode.SUCCESS,p);
        }


        @GetMapping("/student/del")
        public Result delete(@RequestParam String id){
            studentService.delete(id);
            return new Result(ResultCode.SUCCESS);
        }

        @PutMapping("/student")
        public Result update(@RequestBody Student p){
            studentService.update(p);
            return new Result(ResultCode.SUCCESS);
        }

    }

    