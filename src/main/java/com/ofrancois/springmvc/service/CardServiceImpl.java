package com.ofrancois.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.ofrancois.springmvc.hibernate.HibernateUtil;
import com.ofrancois.springmvc.model.Card;
 
@Service("cardService")
public class CardServiceImpl implements CardService{
     
    private static final AtomicLong counter = new AtomicLong();
     
    private static List<Card> cards;
     
    static{
        cards= populateDummyCards();
    }
 
    public List<Card> findAllCards() {
        return cards;
    }
     
    public Card findById(long id) {
        for(Card card : cards){
            if(card.getId() == id){
                return card;
            }
        }
        return null;
    }
     
    public Card findByName(String name) {
        for(Card card : cards){
            if(card.getNameFr().equalsIgnoreCase(name)){
                return card;
            }
        }
        return null;
    }
     
    public void saveCard(Card card) {
    	card.setId(counter.incrementAndGet());
        cards.add(card);
        
        // Add card in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(card);
        tx.commit();
        HibernateUtil.closeSession();
        
    }
 
    public void updateCard(Card card) {
        int index = cards.indexOf(card);
        cards.set(index, card);
       
        // update card in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Card c = (Card) session.load(Card.class, card.getId());
        c.setNameFr(card.getNameFr());
        c.setNameEn(card.getNameEn());
        c.setType(card.getType());
        c.setEdition(card.getEdition());
        c.setManaCost(card.getManaCost());
        c.setRarity(card.getRarity());
        c.setPrice(card.getPrice());
        c.setNbItem(card.getNbItem()); 
        session.save(c);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    public void deleteCardById(long id) {
         
        for (Iterator<Card> iterator = cards.iterator(); iterator.hasNext(); ) {
            Card card = iterator.next();
            if (card.getId() == id) {
                iterator.remove();
            }
        }
    }
 
    public boolean isCardExist(Card card) {
        return findByName(card.getNameFr())!=null;
    }
     
    public void deleteAllCards(){
        cards.clear();
    }
 
    private static List<Card> populateDummyCards(){
    	
    	// Get List Card from DB
    	Session session = HibernateUtil.currentSession();
    	Query<Card> query = session.createQuery("from Card");
    	List<Card> cards = query.getResultList();
    	HibernateUtil.closeSession();
    	
        return cards;
    } 
}