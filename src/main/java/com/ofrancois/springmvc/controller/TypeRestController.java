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
import com.ofrancois.springmvc.model.Type;
import com.ofrancois.springmvc.service.TypeService;
import org.apache.log4j.Logger;

/** 
 * <b>TypeRestController est le controleur des requêtes sur les types</b>
 * <p>
 * Les différentes actions possibles sont :
 * <ul>
 * <li>Récupérer les informations de tous les types</li>
 * <li>Récupérer les informations d'un type</li>
 * <li>Ajouter un type</li>
 * <li>Mettre à jour les informations d'un type</li>
 * <li>Supprimer un type</li>
 * <li>Supprimer tous les types</li>
 * </ul>
 * </p>
 * 
 * @see Type
 * 
 * @author Olivier F.
 * @version 1.0
 */ 
@RestController
public class TypeRestController {
  
	/**
	 * Le service des types
	 */
    @Autowired
    TypeService typeService;  //Service which will do all data retrieval/manipulation work
  
    private static Logger logger = Logger.getLogger(TypeRestController.class);
     
    /**
     * Récupère les informations de tous les types dans la base
     * 
     * @return La liste de tous les types
     * 
     * @see Type
     */
    @RequestMapping(value = "/type/", method = RequestMethod.GET)
    public ResponseEntity<List<Type>> listAllTypes() {
    	 List<Type> types = typeService.findAllTypes();
        if(types.isEmpty()){
        	logger.warn( "Types empty..." );
            return new ResponseEntity<List<Type>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Type>>(types, HttpStatus.OK);
    }
  
    /**
     * Récupère les informations d'un type
     * 
     * @param id
     * 				L'identifiant du type à rechercher
     * 
     * @return Un type
     * 
     * @see Type
     */
    @RequestMapping(value = "/type/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Type> getType(@PathVariable("id") long id) {
        Type type = typeService.findById(id);
        if (type == null) {
            logger.warn( "Type with id " + id + " not found" );
            return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Type>(type, HttpStatus.OK);
    }
      
    /**
     * Ajoute un type dans la base
     * 
     * @param type
     * 				Le type a ajouter
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/type/", method = RequestMethod.POST)
    public ResponseEntity<Void> createType(@RequestBody Type type,    UriComponentsBuilder ucBuilder) {
        if (typeService.isTypeExist(type)) {
        	logger.warn( "A Type with name " + type.getName() + " already exist" );
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        typeService.saveType(type);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/type/{id}").buildAndExpand(type.getTypeId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    /**
     * Met à jour un type
     * 
     * @param id
     * 				l'identifiant du type à mettre à jour
     * @param type
     * 				Le nouveau type
     * 
     * @return un type
     * 
     * @see Type
     */
    @RequestMapping(value = "/type/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Type> updateType(@PathVariable("id") long id, @RequestBody Type type) {
       Type currentType = typeService.findById(id);
          
        if ( currentType == null ) {
        	logger.warn( "Type with id " + id + " not found" );
            return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
        }
  
        currentType.setName( type.getName() );
        typeService.updateType(currentType);
        return new ResponseEntity<Type>(currentType, HttpStatus.OK);
    }
  
    /**
     * Supprimer un type
     * 
     * @param id
     * 				l'identifiant du type à supprimer
     * @return
     */
    @RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Type> deleteType(@PathVariable("id") long id) {
        Type type = typeService.findById(id);
        if (type == null) {
        	logger.warn( "Unable to delete. Type with id " + id + " not found" );
            return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
        }
  
        typeService.deleteTypeById(id);
        return new ResponseEntity<Type>(HttpStatus.NO_CONTENT);
    }
     
    /**
     * Supprimer tous les types
     * 
     * @return
     */
    @RequestMapping(value = "/type/", method = RequestMethod.DELETE)
    public ResponseEntity<Type> deleteAllTypes() {
        typeService.deleteAllTypes();
        return new ResponseEntity<Type>(HttpStatus.NO_CONTENT);
    }
}
