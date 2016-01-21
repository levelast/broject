package ru.broject.site.configuration;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.spring.PebbleViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletContext;

/**
 * Created by vyacheslav.svininyh on 21.01.2016.
 */
@Configuration
public class WebTemplateConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ServletContext servletContext;

    @Bean
    public Loader templateLoader() {
        return new ServletLoader(servletContext);
    }

    @Bean
    public PebbleEngine pebbleEngine() {
        PebbleEngine.Builder builder = new PebbleEngine.Builder();
        builder.loader(templateLoader());
        return builder.build();
    }

    @Bean
    public ViewResolver viewResolver() {
        PebbleViewResolver viewResolver = new PebbleViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates");
        viewResolver.setSuffix(".html");
        viewResolver.setPebbleEngine(pebbleEngine());
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("/static/");
    }
}
