package cn.edu.zucc.elves.config;

import cn.edu.zucc.elves.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器 拦截url
 *
 */
@Configuration
public class InterceptorConfigurer extends WebMvcConfigurerAdapter {

	 public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
//	     registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/*");
		   super.addInterceptors(registry);
	    }
	
}