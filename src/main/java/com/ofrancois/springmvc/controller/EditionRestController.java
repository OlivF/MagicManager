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

import com.ofrancois.springmvc.model.Edition;
import com.ofrancois.springmvc.service.EditionService;
  
@RestController
public class EditionRestController {
  
    @Autowired
    EditionService editionService;  //Service which will do all data retrieval/manipulation work
  
     
    //-------------------Retrieve All Editions --------------------------------------------------------
      
    @RequestMapping(value = "/edition/", method = RequestMethod.GET)
    public ResponseEntity<List<Edition>> listAllEditions() {
        List<Edition> editions = editionService.findAllEditions();
        if(editions.isEmpty()){
            return new ResponseEntity<List<Edition>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Edition>>(editions, HttpStatus.OK);
    }
  
  
     
    //-------------------Retrieve Single Edition--------------------------------------------------------
      
    @RequestMapping(value = "/edition/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Edition> getEdition(@PathVariable("id") long id) {
        System.out.println("Fetching Edition with id " + id);
        Edition edition = editionService.findById(id);
        if (edition == null) {
            System.out.println("Edition with id " + id + " not found");
            return new ResponseEntity<Edition>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Edition>(edition, HttpStatus.OK);
    }
  
      
      
    //-------------------Create a Edition--------------------------------------------------------
      
    @RequestMapping(value = "/edition/", method = RequestMethod.POST)
    public ResponseEntity<Void> createEdition(@RequestBody Edition edition,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Edition " + edition.toString());
  
        if (editionService.isEditionExist(edition)) {
            System.out.println("A Edition with name " + edition.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        editionService.saveEdition(edition);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/edition/{id}").buildAndExpand(edition.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
     
      
    //------------------- Update a Edition --------------------------------------------------------
      
    @RequestMapping(value = "/edition/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Edition> updateEdition(@PathVariable("id") long id, @RequestBody Edition edition) {
        System.out.println("Updating Edition " + id);
          
        Edition currentEdition = editionService.findById(id);
          
        if (currentEdition==null) {
            System.out.println("Edition with id " + id + " not found");
            return new ResponseEntity<Edition>(HttpStatus.NOT_FOUND);
        }
  
        currentEdition.setName(edition.getName());
          
        editionService.updateEdition(currentEdition);
        return new ResponseEntity<Edition>(currentEdition, HttpStatus.OK);
    }
  
     
     
    //------------------- Delete a Edition --------------------------------------------------------
      
    @RequestMapping(value = "/edition/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Edition> deleteEdition(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Edition with id " + id);
  
        Edition edition = editionService.findById(id);
        if (edition == null) {
            System.out.println("Unable to delete. Edition with id " + id + " not found");
            return new ResponseEntity<Edition>(HttpStatus.NOT_FOUND);
        }
  
        editionService.deleteEditionById(id);
        return new ResponseEntity<Edition>(HttpStatus.NO_CONTENT);
    }
  
      
     
    //------------------- Delete All Editions --------------------------------------------------------
      
    @RequestMapping(value = "/edition/", method = RequestMethod.DELETE)
    public ResponseEntity<Edition> deleteAllEditions() {
        System.out.println("Deleting All Editions");
  
        editionService.deleteAllEditions();
        return new ResponseEntity<Edition>(HttpStatus.NO_CONTENT);
    }
  
}
