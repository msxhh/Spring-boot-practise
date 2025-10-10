package top.sxmeng.boot.schedule.filter.interceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
@Slf4j
public class YourInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("YourInterceptor 在请求接口之前执行的逻辑：{}", requestURI);
        LocalDateTime beginTime = LocalDateTime.now();
        request.setAttribute("beginTime", beginTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String beginTime = request.getAttribute("beginTime").toString();
        log.info("begin Time:{}", beginTime);
        LocalDateTime endTime = LocalDateTime.now();
        String requestURI = request.getRequestURI();
        log.info("拦截器执行结束：{}, {}", requestURI, endTime);
    }
}
