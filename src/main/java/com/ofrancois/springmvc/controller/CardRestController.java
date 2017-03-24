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
import com.ofrancois.springmvc.model.Card;
import com.ofrancois.springmvc.service.CardService;
  
@RestController
public class CardRestController {
  
    @Autowired
    CardService cardService;  //Service which will do all data retrieval/manipulation work
  
    //-------------------Retrieve All Cards --------------------------------------------------------
    @RequestMapping(value = "/card/", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> listAllCards() {
        List<Card> cards = cardService.findAllCards();
        if(cards.isEmpty()){
            return new ResponseEntity<List<Card>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Card>>(cards, HttpStatus.OK);
    }
  
    //-------------------Retrieve Single Card--------------------------------------------------------
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
      
    //-------------------Create a Card--------------------------------------------------------
    @RequestMapping(value = "/card/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCard(@RequestBody Card card,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Card " + card.toString());
  
        if (cardService.isCardExist(card)) {
            System.out.println("A Card with name " + card.getNameFr() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        cardService.saveCard(card);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(card.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
     
      
    //------------------- Update a Card --------------------------------------------------------
      
    @RequestMapping(value = "/card/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Card> updateCard(@PathVariable("id") long id, @RequestBody Card card) {
        System.out.println("Updating Card " + id);
          
        Card currentCard = cardService.findById(id);
          
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
        cardService.updateCard(currentCard);
        return new ResponseEntity<Card>(currentCard, HttpStatus.OK);
    }
  
    //------------------- Delete a Card --------------------------------------------------------
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
  
    //------------------- Delete All Cards --------------------------------------------------------
    @RequestMapping(value = "/card/", method = RequestMethod.DELETE)
    public ResponseEntity<Card> deleteAllCards() {
        System.out.println("Deleting All Cards");
  
        cardService.deleteAllCards();
        return new ResponseEntity<Card>(HttpStatus.NO_CONTENT);
    }
}
