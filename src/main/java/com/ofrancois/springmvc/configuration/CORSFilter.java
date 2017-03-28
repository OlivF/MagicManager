package com.ofrancois.springmvc.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * CORSFilter introduce a standard mechanism for enabling cross-domain requests from web browsers to 
 * servers that wish to handle them
 * @author Olivier F.
 * @version 1.0
 */
public class CORSFilter implements Filter {
 
	/**
	 * Applique le filtre
	 */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filtering on...........................................................");
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-requested-with, Content-Type");
        chain.doFilter(req, res);
    }
 
    /**
     * function init
     */
    public void init(FilterConfig filterConfig) {}
 
    /**
     * function destroy
     */
    public void destroy() {}
 
}
