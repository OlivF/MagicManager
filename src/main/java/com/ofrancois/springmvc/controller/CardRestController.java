package com.ofrancois.springmvc.controller;

import java.sql.Date;
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
import com.ofrancois.springmvc.model.Card;
import com.ofrancois.springmvc.service.CardService;

/** 
 * <b>CardRestController est le controleur des requêtes sur les cartes</b>
 * <p>
 * Les différentes actions possibles sont :
 * <ul>
 * <li>Récupérer les informations de toutes les cartes</li>
 * <li>Récupérer les informations d'une carte</li>
 * <li>Ajouter une carte</li>
 * <li>Mettre à jour les informations d'une carte</li>
 * <li>Supprimer une carte</li>
 * <li>Supprimer toutes les cartes</li>
 * </ul>
 * </p>
 * 
 * @see Card
 * 
 * @author Olivier F.
 * @version 1.0
 */ 
@RestController
public class CardRestController {
  
	/**
	 * Le service des cartes
	 */
    @Autowired
    CardService cardService;  //Service which will do all data retrieval/manipulation work
  
    /**
     * Récupère les informations de toutes les cartes dans la base
     * 
     * @return La liste de toutes les cartes
     * 
     * @see Card
     */
    @RequestMapping(value = "/card/", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> listAllCards() {
        List<Card> cards = cardService.findAllCards();
        if(cards.isEmpty()){
            return new ResponseEntity<List<Card>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Card>>(cards, HttpStatus.OK);
    }
  
    /**
     * Récupère les informations d'une carte
     * 
     * @param id
     * 				L'identifiant de la carte à rechercher
     * 
     * @return Une carte
     * 
     * @see Card
     */
    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> getCard(@PathVariable("id") long id) {
        System.out.println("Fetching Card with id " + id);
        Card card = cardService.findById(id);
        if (card == null) {
            System.out.println("Card with id " + id + " not found");
            return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }
      
    /**
     * Ajoute une carte dans la base
     * 
     * @param card
     * 				La carte a ajouter
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/card/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCard(@RequestBody Card card,    UriComponentsBuilder ucBuilder) {
    	
    	card.setNbDispo(card.getNbItem());
        card.setDate(new Date(System.currentTimeMillis()));
    	
        // System.out.println("Creating Card " + card.toString());
  
        if (cardService.isCardExist(card)) {
            System.out.println("A Card with name " + card.getNameFr() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        
        cardService.saveCard(card);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(card.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    /**
     * Met à jour une carte
     * 
     * @param id
     * 				l'identifiant de la carte à mettre à jour
     * @param card
     * 				La nouvelle carte
     * 
     * @return une carte
     * 
     * @see Card
     */
    @RequestMapping(value = "/card/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Card> updateCard(@PathVariable("id") long id, @RequestBody Card card) {
        System.out.println("Updating Card " + id);
          
        Card currentCard = cardService.findById(id);
        System.out.println(card.toString());
        if (currentCard==null) {
            System.out.println("Card with id " + id + " not found");
            return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
        }
  
        currentCard.setNameFr(card.getNameFr());
        currentCard.setNameEn(card.getNameEn());
        currentCard.setType(card.getType());
        currentCard.setEdition(card.getEdition());
        currentCard.setManaCost(card.getManaCost());
        currentCard.setRarity(card.getRarity());
        currentCard.setPrice(card.getPrice());
        currentCard.setNbItem(card.getNbItem());
        currentCard.setNbDispo(card.getNbDispo());
        currentCard.setDate(new Date(System.currentTimeMillis()));
        cardService.updateCard(currentCard);
        return new ResponseEntity<Card>(currentCard, HttpStatus.OK);
    }
  
    /**
     * Supprimer une carte
     * 
     * @param id
     * 				l'identifiant de la carte à supprimer
     * @return
     */
    @RequestMapping(value = "/card/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Card> deleteCard(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Card with id " + id);
  
        Card card = cardService.findById(id);
        if (card == null) {
            System.out.println("Unable to delete. Card with id " + id + " not found");
            return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
        }
  
        cardService.deleteCardById(id);
        return new ResponseEntity<Card>(HttpStatus.NO_CONTENT);
    }
  
    /**
     * Supprimer toutes les cartes
     * 
     * @return
     */
    @RequestMapping(value = "/card/", method = RequestMethod.DELETE)
    public ResponseEntity<Card> deleteAllCards() {
        System.out.println("Deleting All Cards");
  
        cardService.deleteAllCards();
        return new ResponseEntity<Card>(HttpStatus.NO_CONTENT);
    }
}
