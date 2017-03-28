package com.ofrancois.springmvc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.ofrancois.springmvc.model.Deck;
import com.ofrancois.springmvc.service.DeckService;

/** 
 * <b>DeckRestController est le controleur des requêtes sur les decks</b>
 * <p>
 * Les différentes actions possibles sont :
 * <ul>
 * <li>Récupérer les informations de tous les decks</li>
 * <li>Récupérer les informations d'un deck</li>
 * <li>Ajouter un deck</li>
 * <li>Mettre à jour les informations d'un deck</li>
 * <li>Supprimer un deck</li>
 * <li>Supprimer tous les decks</li>
 * </ul>
 * </p>
 * 
 * @see Deck
 * 
 * @author Olivier F.
 * @version 1.0
 */ 
@RestController
public class DeckRestController {
  
	/**
	 * Le service des cartes
	 */
    @Autowired
    DeckService deckService;  //Service which will do all data retrieval/manipulation work
  
    /**
     * Récupère les informations de tous les decks dans la base
     * 
     * @return La liste de toutes les decks
     * 
     * @see Deck
     */
    @RequestMapping(value = "/deck/", method = RequestMethod.GET)
    public ResponseEntity<List<Deck>> listAllDecks() {
        List<Deck> decks = deckService.findAllDecks();
        if(decks.isEmpty()){
            return new ResponseEntity<List<Deck>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Deck>>(decks, HttpStatus.OK);
    }
  
    /**
     * Récupère les informations d'un deck
     * 
     * @param id
     * 				L'identifiant du deck à rechercher
     * 
     * @return Un deck
     * 
     * @see Deck
     */
    @RequestMapping(value = "/deck/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Deck> getDeck(@PathVariable("id") long id) {
        System.out.println("Fetching Deck with id " + id);
        Deck deck = deckService.findById(id);
        if (deck == null) {
            System.out.println("Deck with id " + id + " not found");
            return new ResponseEntity<Deck>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Deck>(deck, HttpStatus.OK);
    }
      
    /**
     * Ajoute un deck dans la base
     * 
     * @param deck
     * 				Le deck a ajouter
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/deck/", method = RequestMethod.POST)
    public ResponseEntity<Void> createDeck(@RequestBody Deck deck,    UriComponentsBuilder ucBuilder) {
    	
    	// System.out.println("Creating Deck " + deck.toString());
  
        if (deckService.isDeckExist(deck)) {
            System.out.println("A Deck with name " + deck.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        
        deckService.saveDeck(deck);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/deck/{id}").buildAndExpand(deck.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    /**
     * Met à jour un deck
     * 
     * @param id
     * 				l'identifiant du deck à mettre à jour
     * @param deck
     * 				Le nouveau deck
     * 
     * @return un deck
     * 
     * @see Deck
     */
    @RequestMapping(value = "/deck/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Deck> updateDeck(@PathVariable("id") long id, @RequestBody Deck deck) {
        System.out.println("Updating Deck " + id);
          
        Deck currentDeck = deckService.findById(id);
        System.out.println(deck.toString());
        if (currentDeck==null) {
            System.out.println("Deck with id " + id + " not found");
            return new ResponseEntity<Deck>(HttpStatus.NOT_FOUND);
        }
  
        currentDeck.setName(deck.getName());
        currentDeck.setColor(deck.getColor());
        
        deckService.updateDeck(currentDeck);
        return new ResponseEntity<Deck>(currentDeck, HttpStatus.OK);
    }
  
    /**
     * Supprimer un deck
     * 
     * @param id
     * 				l'identifiant du deck  à supprimer
     * @return
     */
    @RequestMapping(value = "/deck/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Deck> deleteDeck(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Deck with id " + id);
  
        Deck deck = deckService.findById(id);
        if (deck == null) {
            System.out.println("Unable to delete. Deck with id " + id + " not found");
            return new ResponseEntity<Deck>(HttpStatus.NOT_FOUND);
        }
  
        deckService.deleteDeckById(id);
        return new ResponseEntity<Deck>(HttpStatus.NO_CONTENT);
    }
  
    /**
     * Supprimer tous les decks
     * 
     * @return
     */
    @RequestMapping(value = "/deck/", method = RequestMethod.DELETE)
    public ResponseEntity<Deck> deleteAllDecks() {
        System.out.println("Deleting All Decks");
  
        deckService.deleteAllDecks();
        return new ResponseEntity<Deck>(HttpStatus.NO_CONTENT);
    }
}
