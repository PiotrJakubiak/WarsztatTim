package pl.edu.wat.tim.webstore.configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import pl.edu.wat.tim.webstore.interceptor.AuditingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Piotr on 10.05.2017.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    AuditingInterceptor AuditingInterceptor () {
        return new AuditingInterceptor();
    }

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(AuditingInterceptor());

    }
}
