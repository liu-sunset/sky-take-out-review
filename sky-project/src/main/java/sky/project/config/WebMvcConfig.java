package sky.project.config;

import intercepter.JWTTokenAdminIntercepter;
import intercepter.JWTTokenUserIntercepter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JWTTokenAdminIntercepter jwtTokenAdminIntercepter;
    @Autowired
    private JWTTokenUserIntercepter jwtTokenUserIntercepter;

    @Override
    //添加拦截器
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        log.info("开始注册管理员拦截器");
        interceptorRegistry.addInterceptor(jwtTokenAdminIntercepter)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");

        interceptorRegistry.addInterceptor(jwtTokenUserIntercepter)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/user/login")
                .excludePathPatterns("/user/shop/status");
    }
}
