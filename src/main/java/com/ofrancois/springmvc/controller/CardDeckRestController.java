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
import com.ofrancois.springmvc.model.Carddeck;

import com.ofrancois.springmvc.service.CarddeckService;

/** 
 * <b>CarddeckRestController est le controleur des requêtes sur les cardcarddecks</b>
 * <p>
 * Les différentes actions possibles sont :
 * <ul>
 * <li>Récupérer les informations de tous les cardcarddecks</li>
 * <li>Récupérer les informations d'un cardcarddeck</li>
 * <li>Ajouter un cardcarddeck</li>
 * <li>Mettre à jour les informations d'un cardcarddeck</li>
 * <li>Supprimer un cardcarddeck</li>
 * <li>Supprimer tous les cardcarddecks</li>
 * </ul>
 * </p>
 * 
 * @see Carddeck
 * 
 * @author Olivier F.
 * @version 1.0
 */ 
@RestController
public class CardDeckRestController {
  
	/**
	 * Le service des cardcarddecks
	 */
    @Autowired
    CarddeckService carddeckService;  //Service which will do all data retrieval/manipulation work
  
    /**
     * Récupère les informations de tous les carddecks dans la base
     * 
     * @return La liste de toutes les carddecks
     * 
     * @see Carddeck
     */
    @RequestMapping(value = "/carddeck/", method = RequestMethod.GET)
    public ResponseEntity<List<Carddeck>> listAllCarddecks() {
        List<Carddeck> carddecks = carddeckService.findAllCardDecks();
        if(carddecks.isEmpty()){
            return new ResponseEntity<List<Carddeck>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Carddeck>>(carddecks, HttpStatus.OK);
    }
  
    /**
     * Récupère les informations d'un carddeck
     * 
     * @param id
     * 				L'identifiant du carddeck à rechercher
     * 
     * @return Un carddeck
     * 
     * @see Carddeck
     */
    @RequestMapping(value = "/carddeck/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Carddeck> getCarddeck(@PathVariable("id") long id) {
        System.out.println("Fetching Carddeck with id " + id);
        Carddeck carddeck = carddeckService.findById(id);
        if (carddeck == null) {
            System.out.println("Carddeck with id " + id + " not found");
            return new ResponseEntity<Carddeck>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Carddeck>(carddeck, HttpStatus.OK);
    }
    
    /**
     * Récupère les informations sur les cartes d'un deck
     * 
     * @param id
     * 				L'identifiant du deck à rechercher
     * 
     * @return Une liste de carte
     * 
     * @see Card
     */
    @RequestMapping(value = "/carddeck/deck/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Carddeck>> getCardByDeck(@PathVariable("id") long id) {
    	 List<Carddeck> carddecks = carddeckService.findCardByDeckId(id);
         if(carddecks.isEmpty()){
             return new ResponseEntity<List<Carddeck>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
         }
         return new ResponseEntity<List<Carddeck>>(carddecks, HttpStatus.OK);
    }
    
    /**
     * Récupère les informations sur les decks d'une carte
     * 
     * @param id
     * 				L'identifiant de la carte à rechercher
     * 
     * @return Une liste de carte
     * 
     * @see Card
     */
    @RequestMapping(value = "/carddeck/card/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Carddeck>> getDeckByCard(@PathVariable("id") long id) {
    	 List<Carddeck> carddecks = carddeckService.findDeckByCardId(id);
         if(carddecks.isEmpty()){
             return new ResponseEntity<List<Carddeck>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
         }
         return new ResponseEntity<List<Carddeck>>(carddecks, HttpStatus.OK);
    }
      
    /**
     * Ajoute un carddeck dans la base
     * 
     * @param carddeck
     * 				Le carddeck a ajouter
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/carddeck/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCarddeck(@RequestBody Carddeck carddeck,    UriComponentsBuilder ucBuilder) {
    	
    	System.out.println("Creating Carddeck " + carddeck.toString());
  
        /*if (cardeckService.isCardDeckExist(carddeck)) {
            System.out.println("A Carddeck with name " + carddeck.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
        
        carddeckService.saveCardDeck(carddeck);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/carddeck/{id}").buildAndExpand(carddeck.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    /**
     * Met à jour un carddeck
     * 
     * @param id
     * 				l'identifiant du carddeck à mettre à jour
     * @param carddeck
     * 				Le nouveau carddeck
     * 
     * @return un carddeck
     * 
     * @see Carddeck
     */
    @RequestMapping(value = "/carddeck/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Carddeck> updateCarddeck(@PathVariable("id") long id, @RequestBody Carddeck carddeck) {
        System.out.println("Updating Carddeck " + id);
          
        Carddeck currentCarddeck = carddeckService.findById(id);
        System.out.println(carddeck.toString());
        if (currentCarddeck==null) {
            System.out.println("Carddeck with id " + id + " not found");
            return new ResponseEntity<Carddeck>(HttpStatus.NOT_FOUND);
        }
  
       // currentCarddeck.setName(carddeck.getName());
       // currentCarddeck.setColor(carddeck.getColor());
        
        carddeckService.updateCardDeck(currentCarddeck);
        return new ResponseEntity<Carddeck>(currentCarddeck, HttpStatus.OK);
    }
  
    /**
     * Supprimer un carddeck
     * 
     * @param id
     * 				l'identifiant du carddeck  à supprimer
     * @return
     */
    @RequestMapping(value = "/carddeck/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Carddeck> deleteCarddeck(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Carddeck with id " + id);
  
        Carddeck carddeck = carddeckService.findById(id);
        if (carddeck == null) {
            System.out.println("Unable to delete. Carddeck with id " + id + " not found");
            return new ResponseEntity<Carddeck>(HttpStatus.NOT_FOUND);
        }
  
        carddeckService.deleteCardDeckById(id);
        return new ResponseEntity<Carddeck>(HttpStatus.NO_CONTENT);
    }
  
    /**
     * Supprimer tous les carddecks
     * 
     * @return
     */
    @RequestMapping(value = "/carddeck/", method = RequestMethod.DELETE)
    public ResponseEntity<Carddeck> deleteAllCarddecks() {
        System.out.println("Deleting All Carddecks");
  
        carddeckService.deleteAllCardDecks();
        return new ResponseEntity<Carddeck>(HttpStatus.NO_CONTENT);
    }
}
