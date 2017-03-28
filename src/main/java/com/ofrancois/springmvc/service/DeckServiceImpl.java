package com.ofrancois.springmvc.service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.ofrancois.springmvc.hibernate.HibernateUtil;
import com.ofrancois.springmvc.model.Deck;
import com.ofrancois.springmvc.model.Deck;

/** 
 * <b>DeckServiceImpl est la classe correspondant à l'interface DeckService</b>
 * <p>
 * Cette classe est composée de plusieurs méthodes :
 * <ul>
 * <li>Trouver un deck par son identifiant</li>
 * <li>Trouver un deck par son nom</li>
 * <li>Enregistrer un deck</li>
 * <li>Mettre à jour un deck</li>
 * <li>Supprimer un deck</li>
 * <li>Chercher tous les decks</li>
 * <li>Supprimer tous les decks</li>
 * <li>Vérifier si un deck existe</li>
 * </ul>
 * </p>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Service("deckService")
public class DeckServiceImpl implements DeckService{
     
	/**
	 * un compteur pour les identifiants
	 */
    private static final AtomicLong counter = new AtomicLong();
     
    /**
     * La liste statiques des decks
     */
    private static List<Deck> decks;
     
    static{
        decks= populateDummyDecks();
    }
 
    /**
     * retourne tous les decks
     * 
     * @return une liste des decks
     */
    public List<Deck> findAllDecks() {
        return decks;
    }
    
    /**
	 * Retourne un deck en le cherchant par son identifiant
	 * 
	 * @param id
	 * 				l'identifiant du deck à rechercher
	 * @return Deck
	 * 
	 * @see Deck
	 */
    public Deck findById(long id) {
        for(Deck deck : decks){
            if(deck.getId() == id){
                return deck;
            }
        }
        return null;
    }
    
    /**
     * Retourne un deck en la cherchant par son nom
     * 
     * @param name
     * 				Le nom du deck a rechercher
     * @return un deck
     * 
     * @see Deck
     */
    public Deck findByName(String name) {
        for(Deck deck : decks){
            if(deck.getName().equalsIgnoreCase(name)){
                return deck;
            }
        }
        return null;
    }
    
    /**
     * Enregistre un deck
     * 
     * @param deck
     * 				La deck a enregistrer
     * 
     * @see Deck
     */
    public void saveDeck(Deck deck) {
    	deck.setId(counter.incrementAndGet());
        decks.add(deck);
        
        // Add deck in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(deck);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    /**
     * Mettre à jour un deck
     * 
     * @param deck
     * 				Le deck a mettre à jour
     */
    public void updateDeck(Deck deck) {
        int index = decks.indexOf(deck);
        decks.set(index, deck);
        
        // update deck in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Deck r = (Deck) session.load(Deck.class, deck.getId());
        r.setName(deck.getName());
        session.save(r);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    /**
     * Supprime un deck en le cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant du deck à supprimer
     */
    public void deleteDeckById(long id) {
         
        for (Iterator<Deck> iterator = decks.iterator(); iterator.hasNext(); ) {
            Deck deck = iterator.next();
            if (deck.getId() == id) {
                iterator.remove();
            }
        }
    }
 
    /**
     * Retourne true si le deck existe
     * @param deck
     * 				le deck a chercher
     * @return boolean
     * 
     */
    public boolean isDeckExist(Deck deck) {
        return findByName(deck.getName())!=null;
    }
     
    /**
     * Supprime toutes les decks
     */
    public void deleteAllDecks(){
        decks.clear();
    }
 
    /**
     * Retourne la liste de tous les decks
     * 
     * @return La liste des decks
     */
    private static List<Deck> populateDummyDecks(){
        // Get List Deck from DB
    	Session session = HibernateUtil.currentSession();
    	Query<Deck> query = session.createQuery("from Deck");
    	List<Deck> decks = query.getResultList();
    	HibernateUtil.closeSession();
        return decks;
    } 
}
