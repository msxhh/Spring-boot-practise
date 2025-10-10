package top.sxmeng.boot.schedule.filter.interceptor.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.sxmeng.boot.schedule.filter.interceptor.interceptor.MyInterceptor;
import top.sxmeng.boot.schedule.filter.interceptor.interceptor.YourInterceptor;

@Configuration
@Slf4j
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

//    @Resource
    private final MyInterceptor myInterceptor;
    private final YourInterceptor yourInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/test");
        registry.addInterceptor(yourInterceptor).addPathPatterns("/test");
    }
}
