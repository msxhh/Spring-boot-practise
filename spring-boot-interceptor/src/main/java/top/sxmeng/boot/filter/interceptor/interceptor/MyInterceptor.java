package top.sxmeng.boot.filter.interceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("MyInterceptor 在请求接口之前执行的逻辑：{}", requestURI);
        LocalDateTime beginTime = LocalDateTime.now();
        request.setAttribute("beginTime", beginTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        String beginTime = request.getAttribute("beginTime").toString();
        LocalDateTime beginTime = (LocalDateTime) request.getAttribute("beginTime");
        log.info("begin Time:{}", beginTime);
        LocalDateTime endTime = LocalDateTime.now();

        // 计算时间差
        long duration = Duration.between(beginTime, endTime).toMillis();
        String requestURI = request.getRequestURI();
        log.info("接口耗时:{}ms",  duration);
        log.info("拦截器执行结束：{}, {}", requestURI, endTime);
    }
}
