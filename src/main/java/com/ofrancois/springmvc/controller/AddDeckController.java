package com.ofrancois.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * <b>AddDeckController est la classe gérant le mapping vers l'url /addDeck</b>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Controller
@RequestMapping("/addDeck")
public class AddDeckController {
 
	/**
	 * Fait le mapping entre la requête /addDeck et deckBuilder.jsp
	 * 
	 * @return String
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
    	return "deckBuilder";
    }
 
}
