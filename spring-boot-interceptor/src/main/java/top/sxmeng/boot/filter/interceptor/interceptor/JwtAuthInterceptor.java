package top.sxmeng.boot.schedule.filter.interceptor.interceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.sxmeng.boot.schedule.filter.interceptor.util.JwtUtil;

@Component
@Slf4j
public class JwtAuthInterceptor implements HandlerInterceptor {
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
//            response.getWriter().write("token is empty");
            return false;
        }
//        boolean flag = jwtUtil.validateToken(token);
//
//        if (!flag) {
//            response.setStatus(401);
////            response.getWriter().write("token is invalid");
//            return false;
//        }
//        request.setAttribute("userId", jwtUtil.getUserIdFrinToken(token));
        return true;
    }
}
