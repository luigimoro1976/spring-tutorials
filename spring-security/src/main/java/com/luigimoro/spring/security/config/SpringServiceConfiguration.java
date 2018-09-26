package com.luigimoro.spring.security.config;

import com.luigimoro.spring.security.http.resource.AdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
public class SpringServiceConfiguration implements WebMvcConfigurer {

    @Bean
    AdminService getAdminService() {
        return new AdminService();
    }


}