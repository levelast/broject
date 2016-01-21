package ru.broject.site.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by vyacheslav.svininyh on 21.01.2016.
 */
@Configuration
@ComponentScan(basePackages = "ru.broject.site")
@EnableWebMvc
@PropertySource(value = "classpath:broject.properties")
public class AppConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
