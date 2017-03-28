package com.ofrancois.springmvc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/** 
 * <b>HelloWorldConfiguration est la classe de configuration des vues et des ressources </b>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ofrancois.springmvc")
public class HelloWorldConfiguration extends WebMvcConfigurerAdapter{
    
	/**
	 * Configure le répertoire où seront stockées les jsp
	 */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }
 
    /**
     * Configure le répertoire où seront stockées les ressources static (css/js)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
 
}