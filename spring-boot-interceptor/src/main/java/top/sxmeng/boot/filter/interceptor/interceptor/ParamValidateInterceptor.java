package top.sxmeng.boot.schedule.filter.interceptor.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import top.sxmeng.boot.schedule.filter.interceptor.dto.LoginRequest;
import top.sxmeng.boot.schedule.filter.interceptor.result.Result;

import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ParamValidateInterceptor implements HandlerInterceptor {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 获取请求参数（仅处理POST JSON）
        if ("POST".equalsIgnoreCase(request.getMethod()) && "application/json".equals(request.getContentType())) {
            // 使用 Spring 的 ContentCachingRequestWrapper
            ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);

            // 先读取一次请求体来缓存内容
            byte[] bodyBytes = wrappedRequest.getContentAsByteArray();
            if (bodyBytes.length > 0) {
                String body = new String(bodyBytes, StandardCharsets.UTF_8);

                if (StringUtils.hasText(body)) {
                    // 2. 转换为实体类并进行校验
                    LoginRequest loginRequest = objectMapper.readValue(body, LoginRequest.class);
                    Set<ConstraintViolation<LoginRequest>> violations = VALIDATOR.validate(loginRequest);

                    if (!violations.isEmpty()) {
                        // 3. 校验失败处理
                        String errorMsg = violations.stream()
                                .map(ConstraintViolation::getMessage)
                                .collect(Collectors.joining("; "));
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write(objectMapper.writeValueAsString(Result.error(400, errorMsg)));
                        return false;
                    }
                }
            }

            // 4. 将包装后的请求继续传递
            request = wrappedRequest;
        }
        return true;
    }
}