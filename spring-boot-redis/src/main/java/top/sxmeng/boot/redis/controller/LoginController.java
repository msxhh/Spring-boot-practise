package top.sxmeng.boot.redis.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sxmeng.boot.redis.entity.LoginRequest;
import top.sxmeng.boot.redis.entity.LoginResponse;
import top.sxmeng.boot.redis.result.Result;
import top.sxmeng.boot.redis.service.LoginService;

/**
 * 登录
 */

@RestController
@RequestMapping("/api")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = loginService.login(request);
        return Result.ok(response);
    }
}
