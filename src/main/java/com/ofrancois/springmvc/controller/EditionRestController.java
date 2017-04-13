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
import com.ofrancois.springmvc.model.Edition;
import com.ofrancois.springmvc.service.EditionService;

/** 
 * <b>EditionRestController est le controleur des requêtes sur les éditions</b>
 * <p>
 * Les différentes actions possibles sont :
 * <ul>
 * <li>Récupérer les informations de toutes les éditions</li>
 * <li>Récupérer les informations d'une édition</li>
 * <li>Ajouter une édition</li>
 * <li>Mettre à jour les informations d'une édition</li>
 * <li>Supprimer une édition</li>
 * <li>Supprimer toutes les éditions</li>
 * </ul>
 * </p>
 * 
 * @see Edition
 * 
 * @author Olivier F.
 * @version 1.0
 */ 
@RestController
public class EditionRestController {
  
	/**
	 * Le service des éditions
	 */
    @Autowired
    EditionService editionService;  //Service which will do all data retrieval/manipulation work
  
    private static Logger logger = Logger.getLogger(EditionRestController.class);
    
    /**
     * Récupère les informations de toutes les éditions dans la base
     * 
     * @return La liste de toutes les éditions
     * 
     * @see Edition
     */
    @RequestMapping(value = "/edition/", method = RequestMethod.GET)
    public ResponseEntity<List<Edition>> listAllEditions() {
        List<Edition> editions = editionService.findAllEditions();
        if(editions.isEmpty()){
        	logger.warn( "Editions empty..." );
            return new ResponseEntity<List<Edition>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Edition>>(editions, HttpStatus.OK);
    }
  
    /**
     * Récupère les informations d'une édition
     * 
     * @param id
     * 				L'identifiant de l'édition à rechercher
     * 
     * @return Une édition
     * 
     * @see Edition
     */
    @RequestMapping(value = "/edition/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Edition> getEdition(@PathVariable("id") long id) {
        Edition edition = editionService.findById(id);
        if (edition == null) {
        	logger.warn( "Edition with id " + id + " not found" );
            return new ResponseEntity<Edition>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Edition>(edition, HttpStatus.OK);
    }
      
    /**
     * Ajoute une édition dans la base
     * 
     * @param edition
     * 				L'édition a ajouter
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/edition/", method = RequestMethod.POST)
    public ResponseEntity<Void> createEdition(@RequestBody Edition edition,    UriComponentsBuilder ucBuilder) {
        if (editionService.isEditionExist(edition)) {
        	logger.warn( "A Edition with name " + edition.getName() + " already exist" );
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        editionService.saveEdition(edition);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/edition/{id}").buildAndExpand(edition.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    /**
     * Met à jour une édition
     * 
     * @param id
     * 				l'identifiant d'édition à mettre à jour
     * @param edition
     * 				La nouvelle édition
     * 
     * @return une édition
     * 
     * @see Edition
     */
    @RequestMapping(value = "/edition/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Edition> updateEdition(@PathVariable("id") long id, @RequestBody Edition edition) {
        Edition currentEdition = editionService.findById(id);
        if (currentEdition==null) {
        	logger.warn( "Edition with id " + id + " not found" );
            return new ResponseEntity<Edition>(HttpStatus.NOT_FOUND);
        }
        currentEdition.setName(edition.getName());
        editionService.updateEdition(currentEdition);
        return new ResponseEntity<Edition>(currentEdition, HttpStatus.OK);
    }
  
    /**
     * Supprimer une édition
     * 
     * @param id
     * 				l'identifiant d'édition à supprimer
     * @return
     */
    @RequestMapping(value = "/edition/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Edition> deleteEdition(@PathVariable("id") long id) {
        Edition edition = editionService.findById(id);
        if (edition == null) {
        	logger.warn( "Unable to delete. Edition with id " + id + " not found" );
            return new ResponseEntity<Edition>(HttpStatus.NOT_FOUND);
        }
        editionService.deleteEditionById(id);
        return new ResponseEntity<Edition>(HttpStatus.NO_CONTENT);
    }
  
    /**
     * Supprimer toutes les éditions
     * 
     * @return
     */
    @RequestMapping(value = "/edition/", method = RequestMethod.DELETE)
    public ResponseEntity<Edition> deleteAllEditions() {
        editionService.deleteAllEditions();
        return new ResponseEntity<Edition>(HttpStatus.NO_CONTENT);
    }
}
