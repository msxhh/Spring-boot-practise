package top.sxmeng.boot.redis.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.sxmeng.boot.redis.cache.RedisCache;
import top.sxmeng.boot.redis.cache.RedisKeys;
import top.sxmeng.boot.redis.entity.LoginRequest;
import top.sxmeng.boot.redis.entity.LoginResponse;
import top.sxmeng.boot.redis.enums.ErrorCode;
import top.sxmeng.boot.redis.exception.ServerException;
import top.sxmeng.boot.redis.service.LoginService;
import top.sxmeng.boot.redis.utils.CommonUtils;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final RedisCache redisCache;

    @Override
    public LoginResponse login(LoginRequest request) {
        String phone = request.getPhone();
        String inputCode = request.getCode();

        // 验证码校验
        if (!CommonUtils.checkPhone(phone)) {
            throw new ServerException(ErrorCode.PHONE_ERROR);
        }

        // 验证码是否为空校验
        if (inputCode == null || inputCode.trim().isEmpty()) {
            throw new ServerException("验证码不能为空");
        }

        // 获取Redis中的验证码
        String redisKey = RedisKeys.getSmsKey(phone);
        Object codeObj = redisCache.get(redisKey);
        String redisCode = codeObj != null ? codeObj.toString() : null;

        // 验证码过期或不存在
        if (redisCode == null) {
            throw new ServerException("验证码已过期或不存在");
        }

        // 验证码不匹配
        if (!redisCode.equals(inputCode)) {
            throw new ServerException("验证码错误");
        }

        // 验证码匹配成功，删除Redis中的验证码
        redisCache.delete(redisKey);

        // 生成token返回登录信息
        String token = CommonUtils.generateToken();

        log.info("用户：{}登录成功", phone);
        return new LoginResponse(token, phone);
    }

    public String generateToken(String phone) {
        return UUID.randomUUID().toString().replace("-","") + phone.hashCode();
    }
}
