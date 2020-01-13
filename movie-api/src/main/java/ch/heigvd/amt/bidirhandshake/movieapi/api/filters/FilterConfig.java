package ch.heigvd.amt.bidirhandshake.movieapi.api.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AuthentificationFilter> authentificationFilter() {
        FilterRegistrationBean<AuthentificationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthentificationFilter());
        registrationBean.addUrlPatterns("/media", "/media/*", "/users/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> AuthorizationFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns("/admin");

        return registrationBean;
    }
}