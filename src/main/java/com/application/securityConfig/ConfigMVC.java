package com.application.securityConfig;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class ConfigMVC implements WebMvcConfigurer {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/signup").setViewName("home");
        registry.addViewController("/login").setViewName("home");
        registry.addViewController("/logout").setViewName("home");
        registry.addViewController("/user").setViewName("home");
        registry.addViewController("/users").setViewName("home");
        registry.addViewController("/lesson").setViewName("home");
        registry.addViewController("/lesson/**").setViewName("home");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/resources/**")) {
            registry.addResourceHandler("/resources/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }
    }
}

//...

