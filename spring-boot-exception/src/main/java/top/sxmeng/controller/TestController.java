package top.sxmeng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
//    @Resource
//    private ExceptionService exceptionService;
//
//    @GetMapping("/{id}")
//    public Result<String> getUserById(@PathVariable Integer id) {
//        if (id == 0) {
//            exceptionService.unAuthorizedError();
//        } else if (id == 1) {
//            exceptionService.systemError();
//        } else {
//            return Result.ok("查询成功");
//        }
//        return Result.ok("查询成功");
//    }
//
//    @PostMapping("/user")
//    public Result<?> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return Result.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
//        }
//        return Result.ok(user);
//    }
}