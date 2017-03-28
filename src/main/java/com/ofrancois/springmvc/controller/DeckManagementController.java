package com.ofrancois.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * <b>DeckManagementController est la classe gérant le mapping vers l'url /decks</b>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Controller
@RequestMapping("/decks")
public class DeckManagementController {
 
	/**
	 * Fait le mapping entre la requête /decks et deckManager.jsp
	 * 
	 * @return String
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
    	return "deckManager";
    }
 
}
