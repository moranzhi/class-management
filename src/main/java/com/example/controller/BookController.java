
    package com.example.controller;

    import com.github.pagehelper.PageInfo;
    import com.example.domain.Book;
    import com.example.entity.Result;
    import com.example.entity.ResultCode;
    import com.example.service.IBookService;
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
    public class BookController {
        @Autowired
        private IBookService bookService;

        @GetMapping("/books")
        public Result findAll(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "5") Integer limit,
                              @RequestParam(defaultValue = "") String name) {
            List<Book> list = bookService.findAll(page, limit,name);
            PageInfo pageInfo = new PageInfo(list);
            return new Result(ResultCode.SUCCESS, pageInfo);
        }

        @PostMapping("/book")
        public Result save(@RequestBody Book p) {
            bookService.save(p);
            return new Result(ResultCode.SUCCESS);
        }

        @GetMapping("/book/{id}")
        public Result findById(@PathVariable("id") String id){
            Book p = bookService.findById(id);
            return new Result(ResultCode.SUCCESS,p);
        }


        @GetMapping("/book/del")
        public Result delete(@RequestParam String id){
            bookService.delete(id);
            return new Result(ResultCode.SUCCESS);
        }

        @PutMapping("/book")
        public Result update(@RequestBody Book p){
            bookService.update(p);
            return new Result(ResultCode.SUCCESS);
        }

    }

    