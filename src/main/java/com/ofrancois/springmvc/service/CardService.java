package com.ofrancois.springmvc.service;

import java.util.List;

import com.ofrancois.springmvc.model.Card;
 
 
 
public interface CardService {
     
    Card findById(long id);
     
    Card findByName(String name);
     
    void saveCard(Card card);
     
    void updateCard(Card card);
     
    void deleteCardById(long id);
 
    List<Card> findAllCards(); 
     
    void deleteAllCards();
     
    public boolean isCardExist(Card card);
     
}
