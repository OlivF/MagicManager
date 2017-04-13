package com.ofrancois.springmvc.controller;

import java.util.List;

import org.apache.log4j.Logger;
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
import com.ofrancois.springmvc.model.Rarity;
import com.ofrancois.springmvc.service.RarityService;

/** 
 * <b>RarityRestController est le controleur des requêtes sur les rareté</b>
 * <p>
 * Les différentes actions possibles sont :
 * <ul>
 * <li>Récupérer les informations de toutes les raretés</li>
 * <li>Récupérer les informations d'une rareté</li>
 * <li>Ajouter une rareté</li>
 * <li>Mettre à jour les informations d'une rareté</li>
 * <li>Supprimer une rareté</li>
 * <li>Supprimer toutes les raretés</li>
 * </ul>
 * </p>
 * 
 * @see Rarity
 * 
 * @author Olivier F.
 * @version 1.0
 */ 
@RestController
public class RarityRestController {
  
	/**
	 * Le service des raretés
	 */
    @Autowired
    RarityService rarityService;  //Service which will do all data retrieval/manipulation work
    
    private static Logger logger = Logger.getLogger(RarityRestController.class);
    
    /**
     * Récupère les informations de toutes les raretés dans la base
     * 
     * @return La liste de toutes les raretés
     * 
     * @see Rarity
     */
    @RequestMapping(value = "/rarity/", method = RequestMethod.GET)
    public ResponseEntity<List<Rarity>> listAllRaritys() {
        List<Rarity> raritys = rarityService.findAllRaritys();
        if(raritys.isEmpty()){
        	logger.warn( "Raritys empty..." );
            return new ResponseEntity<List<Rarity>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Rarity>>(raritys, HttpStatus.OK);
    }
     
    /**
     * Récupère les informations d'une rareté
     * 
     * @param id
     * 				L'identifiant de la rareté à rechercher
     * 
     * @return Une rareté
     * 
     * @see Rarity
     */
    @RequestMapping(value = "/rarity/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rarity> getRarity(@PathVariable("id") long id) {
        Rarity rarity = rarityService.findById(id);
        if (rarity == null) {
        	logger.warn( "Rarity with id " + id + " not found" );
            return new ResponseEntity<Rarity>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Rarity>(rarity, HttpStatus.OK);
    }
      
    /**
     * Ajoute une rareté dans la base
     * 
     * @param rarity
     * 				La rereté a ajouter
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/rarity/", method = RequestMethod.POST)
    public ResponseEntity<Void> createRarity(@RequestBody Rarity rarity,    UriComponentsBuilder ucBuilder) {
        if (rarityService.isRarityExist(rarity)) {
        	logger.warn( "A Rarity with name " + rarity.getName() + " already exist" );
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        rarityService.saveRarity(rarity);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rarity/{id}").buildAndExpand(rarity.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    /**
     * Met à jour une rareté
     * 
     * @param id
     * 				l'identifiant de la rareté à mettre à jour
     * @param rarity
     * 				La nouvelle rareté
     * 
     * @return une rareté
     * 
     * @see Rarity
     */
    @RequestMapping(value = "/rarity/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Rarity> updateRarity(@PathVariable("id") long id, @RequestBody Rarity rarity) {
        Rarity currentRarity = rarityService.findById(id);
        if (currentRarity==null) {
        	logger.warn( "Rarity with id " + id + " not found" );
            return new ResponseEntity<Rarity>(HttpStatus.NOT_FOUND);
        }
        currentRarity.setName(rarity.getName());
        rarityService.updateRarity(currentRarity);
        return new ResponseEntity<Rarity>(currentRarity, HttpStatus.OK);
    }
  
    /**
     * Supprimer une rareté
     * 
     * @param id
     * 				l'identifiant de la rareté à supprimer
     * @return
     */
    @RequestMapping(value = "/rarity/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Rarity> deleteRarity(@PathVariable("id") long id) {
        Rarity rarity = rarityService.findById(id);
        if (rarity == null) {
        	logger.warn( "Unable to delete. Rarity with id " + id + " not found" );
            return new ResponseEntity<Rarity>(HttpStatus.NOT_FOUND);
        }
        rarityService.deleteRarityById(id);
        return new ResponseEntity<Rarity>(HttpStatus.NO_CONTENT);
    }
  
    /**
     * Supprimer toutes les raretés
     * 
     * @return
     */
    @RequestMapping(value = "/rarity/", method = RequestMethod.DELETE)
    public ResponseEntity<Rarity> deleteAllRaritys() {
        rarityService.deleteAllRaritys();
        return new ResponseEntity<Rarity>(HttpStatus.NO_CONTENT);
    }
}
