package com.ofrancois.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ofrancois.springmvc.model.Deck;
import com.ofrancois.springmvc.service.DeckService;

/** 
 * <b>DeckManagementController est la classe gérant le mapping vers l'url /decks</b>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Controller
@RequestMapping("/deckList")
public class DeckListController {
 
	/** Fait le mapping entre la requête /addDeck et deckBuilder.jsp
	 * 
	 * @return String
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
    	return "deckList";
    }
 
}
