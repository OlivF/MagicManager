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

import com.ofrancois.springmvc.model.Rarity;
import com.ofrancois.springmvc.service.RarityService;
  
@RestController
public class RarityRestController {
  
    @Autowired
    RarityService rarityService;  //Service which will do all data retrieval/manipulation work
     
    //-------------------Retrieve All Raritys --------------------------------------------------------
    @RequestMapping(value = "/rarity/", method = RequestMethod.GET)
    public ResponseEntity<List<Rarity>> listAllRaritys() {
        List<Rarity> raritys = rarityService.findAllRaritys();
        if(raritys.isEmpty()){
            return new ResponseEntity<List<Rarity>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Rarity>>(raritys, HttpStatus.OK);
    }
     
    //-------------------Retrieve Single Rarity--------------------------------------------------------
    @RequestMapping(value = "/rarity/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rarity> getRarity(@PathVariable("id") long id) {
        System.out.println("Fetching Rarity with id " + id);
        Rarity rarity = rarityService.findById(id);
        if (rarity == null) {
            System.out.println("Rarity with id " + id + " not found");
            return new ResponseEntity<Rarity>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Rarity>(rarity, HttpStatus.OK);
    }
      
    //-------------------Create a Rarity--------------------------------------------------------
    @RequestMapping(value = "/rarity/", method = RequestMethod.POST)
    public ResponseEntity<Void> createRarity(@RequestBody Rarity rarity,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Rarity " + rarity.toString());
  
        if (rarityService.isRarityExist(rarity)) {
            System.out.println("A Rarity with name " + rarity.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        rarityService.saveRarity(rarity);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rarity/{id}").buildAndExpand(rarity.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    //------------------- Update a Rarity --------------------------------------------------------
    @RequestMapping(value = "/rarity/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Rarity> updateRarity(@PathVariable("id") long id, @RequestBody Rarity rarity) {
        System.out.println("Updating Rarity " + id);
          
        Rarity currentRarity = rarityService.findById(id);
          
        if (currentRarity==null) {
            System.out.println("Rarity with id " + id + " not found");
            return new ResponseEntity<Rarity>(HttpStatus.NOT_FOUND);
        }
  
        currentRarity.setName(rarity.getName());
          
        rarityService.updateRarity(currentRarity);
        return new ResponseEntity<Rarity>(currentRarity, HttpStatus.OK);
    }
  
    //------------------- Delete a Rarity --------------------------------------------------------
    @RequestMapping(value = "/rarity/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Rarity> deleteRarity(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Rarity with id " + id);
  
        Rarity rarity = rarityService.findById(id);
        if (rarity == null) {
            System.out.println("Unable to delete. Rarity with id " + id + " not found");
            return new ResponseEntity<Rarity>(HttpStatus.NOT_FOUND);
        }
  
        rarityService.deleteRarityById(id);
        return new ResponseEntity<Rarity>(HttpStatus.NO_CONTENT);
    }
  
    //------------------- Delete All Raritys --------------------------------------------------------
    @RequestMapping(value = "/rarity/", method = RequestMethod.DELETE)
    public ResponseEntity<Rarity> deleteAllRaritys() {
        System.out.println("Deleting All Raritys");
  
        rarityService.deleteAllRaritys();
        return new ResponseEntity<Rarity>(HttpStatus.NO_CONTENT);
    }
}
