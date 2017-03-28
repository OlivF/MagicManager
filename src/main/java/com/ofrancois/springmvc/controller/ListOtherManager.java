package com.ofrancois.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * <b>ListOtherManager est la classe gérant le mapping vers l'url /listOther</b>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Controller
@RequestMapping("/listOther")
public class ListOtherManager {
 
	/**
	 * Fait le mapping entre la requête /listOther et listOtherManager.jsp
	 * 
	 * @return String
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
    	return "listOtherManager";
    }
 
}
