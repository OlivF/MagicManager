package com.ofrancois.springmvc.configuration;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ofrancois.springmvc.model.Card;

/** 
 * <b>HelloWorldInitializer est la classe de configuration des servlets.</b>
 * 
 * @see Card
 * 
 * @author Olivier F.
 * @version 1.0
 */
public class HelloWorldInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  
	/**
	 * Function getRootConfigClasses
	 */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { HelloWorldConfiguration.class };
    }
   
    /**
     * Function getServletConfigClasses
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
   
    /**
     * Function getServletMappings
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
     
    /**
     * Function getServletFilters
     */
    @Override
    protected Filter[] getServletFilters() {
        Filter [] singleton = { new CORSFilter() };
        return singleton;
    }
  
}
