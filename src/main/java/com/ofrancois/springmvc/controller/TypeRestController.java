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
  
@RestController
public class TypeRestController {
  
    @Autowired
    TypeService typeService;  //Service which will do all data retrieval/manipulation work
  
    //-------------------Retrieve All Types --------------------------------------------------------
    @RequestMapping(value = "/type/", method = RequestMethod.GET)
    public ResponseEntity<List<Type>> listAllTypes() {
        List<Type> types = typeService.findAllTypes();
        if(types.isEmpty()){
            return new ResponseEntity<List<Type>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Type>>(types, HttpStatus.OK);
    }
  
    //-------------------Retrieve Single Type--------------------------------------------------------
    @RequestMapping(value = "/type/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Type> getType(@PathVariable("id") long id) {
        System.out.println("Fetching Type with id " + id);
        Type type = typeService.findById(id);
        if (type == null) {
            System.out.println("Type with id " + id + " not found");
            return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Type>(type, HttpStatus.OK);
    }
      
    //-------------------Create a Type--------------------------------------------------------
    @RequestMapping(value = "/type/", method = RequestMethod.POST)
    public ResponseEntity<Void> createType(@RequestBody Type type,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Type " + type.toString());
  
        if (typeService.isTypeExist(type)) {
            System.out.println("A Type with name " + type.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        typeService.saveType(type);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/type/{id}").buildAndExpand(type.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
    //------------------- Update a Type --------------------------------------------------------
    @RequestMapping(value = "/type/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Type> updateType(@PathVariable("id") long id, @RequestBody Type type) {
        System.out.println("Updating Type " + id);
          
        Type currentType = typeService.findById(id);
          
        if (currentType==null) {
            System.out.println("Type with id " + id + " not found");
            return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
        }
  
        currentType.setName(type.getName());
          
        typeService.updateType(currentType);
        return new ResponseEntity<Type>(currentType, HttpStatus.OK);
    }
  
    //------------------- Delete a Type --------------------------------------------------------
    @RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Type> deleteType(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Type with id " + id);
  
        Type type = typeService.findById(id);
        if (type == null) {
            System.out.println("Unable to delete. Type with id " + id + " not found");
            return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
        }
  
        typeService.deleteTypeById(id);
        return new ResponseEntity<Type>(HttpStatus.NO_CONTENT);
    }
     
    //------------------- Delete All Types --------------------------------------------------------
    @RequestMapping(value = "/type/", method = RequestMethod.DELETE)
    public ResponseEntity<Type> deleteAllTypes() {
        System.out.println("Deleting All Types");
  
        typeService.deleteAllTypes();
        return new ResponseEntity<Type>(HttpStatus.NO_CONTENT);
    }
}
