package top.sxmeng.boot.filter.interceptor.util;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class JwtUtilsTest {

    @Resource
    private JwtUtil jwtUtils;

    @Test
    void generateToken() {
        String token = jwtUtils.generateToken(String.valueOf(1L), "admin");
        log.info("token={}", token);
//       boolean b = jwtUtils.validation(token);

//        log.info("{}", jwtUtils.validation(token));
    }
}