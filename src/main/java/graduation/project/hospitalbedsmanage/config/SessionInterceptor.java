package graduation.project.hospitalbedsmanage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SessionInterceptor implements WebMvcConfigurer {

    /**
     * 自定义拦截器，添加拦截路径和排除拦截路径
     * addPathPatterns():添加需要拦截的路径
     * excludePathPatterns():添加不需要拦截的路径
     */

    @Autowired
    private UserInterceptor loginInterceptor;

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List list=new ArrayList();
        list.add("/page/**");
        list.add("/login.html");
        list.add("/register.html");
        list.add("/captcha/**");
        list.add("/error");
        list.add("/user/login");
        list.add("/user/register");
        list.add("/user/logout");
        list.add("/lib/**");
        list.add("/images/**");
        list.add("/js/**");
        list.add("/css/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(list);

    }
}
