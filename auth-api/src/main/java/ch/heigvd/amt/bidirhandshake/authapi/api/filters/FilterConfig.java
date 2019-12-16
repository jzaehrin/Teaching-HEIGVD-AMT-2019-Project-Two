package ch.heigvd.amt.bidirhandshake.authapi.api.filters;

import com.google.common.collect.Maps;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AuthentificationFilter> authentificationFilter() {
        FilterRegistrationBean<AuthentificationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthentificationFilter());
        registrationBean.addUrlPatterns("/users/*", "/admin/*");

        return registrationBean;
    }
}