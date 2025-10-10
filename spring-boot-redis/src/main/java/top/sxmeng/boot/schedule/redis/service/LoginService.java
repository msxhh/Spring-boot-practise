package top.sxmeng.boot.redis.service;

import top.sxmeng.boot.redis.entity.LoginRequest;
import top.sxmeng.boot.redis.entity.LoginResponse;

public interface LoginService {
    public LoginResponse login(LoginRequest request);
}
