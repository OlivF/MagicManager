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
import com.ofrancois.springmvc.model.Sideboard;
import com.ofrancois.springmvc.model.Sideboard;
import com.ofrancois.springmvc.service.SideboardService;
import com.ofrancois.springmvc.service.SideboardService;

/** 
 * <b>SideboardRestController est le controleur des requêtes sur les cardsideboards</b>
 * <p>
 * Les différentes actions possibles sont :
 * <ul>
 * <li>Récupérer les informations de tous les cardsideboards</li>
 * <li>Récupérer les informations d'un cardsideboard</li>
 * <li>Ajouter un cardsideboard</li>
 * <li>Mettre à jour les informations d'un cardsideboard</li>
 * <li>Supprimer un cardsideboard</li>
 * <li>Supprimer tous les cardsideboards</li>
 * </ul>
 * </p>
 * 
 * @see Sideboard
 * 
 * @author Olivier F.
 * @version 1.0
 */ 
@RestController
public class SideboardRestController {
  
	/**
	 * Le service des cardsideboards
	 */
    @Autowired
    SideboardService sideboardService;  //Service which will do all data retrieval/manipulation work
  
    /**
     * Récupère les informations de tous les sideboards dans la base
     * 
     * @return La liste de toutes les sideboards
     * 
     * @see Sideboard
     */
    @RequestMapping(value = "/sideboard/", method = RequestMethod.GET)
    public ResponseEntity<List<Sideboard>> listAllSideboards() {
        List<Sideboard> sideboards = sideboardService.findAllSideboards();
        if(sideboards.isEmpty()){
            return new ResponseEntity<List<Sideboard>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Sideboard>>(sideboards, HttpStatus.OK);
    }
  
    /**
     * Récupère les informations d'un sideboard
     * 
     * @param id
     * 				L'identifiant du sideboard à rechercher
     * 
     * @return Un sideboard
     * 
     * @see Sideboard
     */
    @RequestMapping(value = "/sideboard/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sideboard> getSideboard(@PathVariable("id") long id) {
        System.out.println("Fetching Sideboard with id " + id);
        Sideboard sideboard = sideboardService.findById(id);
        if (sideboard == null) {
            System.out.println("Sideboard with id " + id + " not found");
            return new ResponseEntity<Sideboard>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Sideboard>(sideboard, HttpStatus.OK);
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
    @RequestMapping(value = "/sideboard/deck/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sideboard>> getCardByDeck(@PathVariable("id") long id) {
    	 List<Sideboard> sideboards = sideboardService.findCardByDeckId(id);
         if(sideboards.isEmpty()){
             return new ResponseEntity<List<Sideboard>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
         }
         return new ResponseEntity<List<Sideboard>>(sideboards, HttpStatus.OK);
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
    @RequestMapping(value = "/sideboard/card/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sideboard>> getDeckByCard(@PathVariable("id") long id) {
    	 List<Sideboard> sideboards = sideboardService.findDeckByCardId(id);
         if(sideboards.isEmpty()){
             return new ResponseEntity<List<Sideboard>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
         }
         return new ResponseEntity<List<Sideboard>>(sideboards, HttpStatus.OK);
    }
      
    /**
     * Ajoute un sideboard dans la base
     * 
     * @param sideboard
     * 				Le sideboard a ajouter
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/sideboard/", method = RequestMethod.POST)
    public ResponseEntity<Void> createSideboard(@RequestBody Sideboard sideboard,    UriComponentsBuilder ucBuilder) {
    	
    	System.out.println("Creating Sideboard " + sideboard.toString());
  
        /*if (cardeckService.isCardDeckExist(sideboard)) {
            System.out.println("A Sideboard with name " + sideboard.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
        
        sideboardService.saveSideboard(sideboard);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/sideboard/{id}").buildAndExpand(sideboard.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    /**
     * Met à jour un sideboard
     * 
     * @param id
     * 				l'identifiant du sideboard à mettre à jour
     * @param sideboard
     * 				Le nouveau sideboard
     * 
     * @return un sideboard
     * 
     * @see Sideboard
     */
    @RequestMapping(value = "/sideboard/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sideboard> updateSideboard(@PathVariable("id") long id, @RequestBody Sideboard sideboard) {
        System.out.println("Updating Sideboard " + id);
          
        Sideboard currentSideboard = sideboardService.findById(id);
        System.out.println(sideboard.toString());
        if (currentSideboard==null) {
            System.out.println("Sideboard with id " + id + " not found");
            return new ResponseEntity<Sideboard>(HttpStatus.NOT_FOUND);
        }
  
       // currentSideboard.setName(sideboard.getName());
       // currentSideboard.setColor(sideboard.getColor());
        
        sideboardService.updateSideboard(currentSideboard);
        return new ResponseEntity<Sideboard>(currentSideboard, HttpStatus.OK);
    }
  
    /**
     * Supprimer un sideboard
     * 
     * @param id
     * 				l'identifiant du sideboard  à supprimer
     * @return
     */
    @RequestMapping(value = "/sideboard/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Sideboard> deleteSideboard(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Sideboard with id " + id);
  
        Sideboard sideboard = sideboardService.findById(id);
        if (sideboard == null) {
            System.out.println("Unable to delete. Sideboard with id " + id + " not found");
            return new ResponseEntity<Sideboard>(HttpStatus.NOT_FOUND);
        }
  
        sideboardService.deleteSideboardById(id);
        return new ResponseEntity<Sideboard>(HttpStatus.NO_CONTENT);
    }
  
    /**
     * Supprimer tous les sideboards
     * 
     * @return
     */
    @RequestMapping(value = "/sideboard/", method = RequestMethod.DELETE)
    public ResponseEntity<Sideboard> deleteAllSideboards() {
        System.out.println("Deleting All Sideboards");
  
        sideboardService.deleteAllSideboard();
        return new ResponseEntity<Sideboard>(HttpStatus.NO_CONTENT);
    }
}
