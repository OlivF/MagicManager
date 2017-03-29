package com.ofrancois.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * <b>IndexController est la classe gérant le mapping vers l'url /</b>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class IndexController {

	/**
	 * Fait le mapping entre la requête / et cardManager.jsp
	 * 
	 * @return String
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
    	return "home";
    }
 
}
