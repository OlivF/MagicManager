package com.ofrancois.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofrancois.springmvc.hibernate.HibernateUtil;
import com.ofrancois.springmvc.model.Card;
import com.ofrancois.springmvc.model.Carddeck;

/** 
 * <b>CardDeckServiceImpl est la classe correspondant à l'interface CardDeckService</b>
 * <p>
 * Cette classe est composée de plusieurs méthodes :
 * <ul>
 * <li>Trouver un carddeck par son identifiant</li>
 * <li>Enregistrer un carddeck</li>
 * <li>Mettre à jour un carddeck</li>
 * <li>Supprimer un carddeck</li>
 * <li>Chercher tous les carddecks</li>
 * <li>Supprimer tous les carddecks</li>
 * <li>Vérifier si un carddeck existe</li>
 * </ul>
 * </p>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Service("cardDeckService")
public class CarddeckServiceImpl implements CarddeckService{
     
	
	@Autowired
	DeckService deckService;
	CardService cardService;
	
	/**
	 * un compteur pour les identifiants
	 */
    private static final AtomicLong counter = new AtomicLong();
     
    /**
     * La liste statiques des carddecks
     */
    private static List<Carddeck> carddecks;
     
    static{
        carddecks= populateDummyCardDecks();
    }
 
    /**
     * retourne tous les carddecks
     * 
     * @return une liste des carddecks
     */
    public List<Carddeck> findAllCardDecks() {
        return carddecks;
    }
    
    /**
	 * Retourne un carddeck en le cherchant par son identifiant
	 * 
	 * @param id
	 * 				l'identifiant du carddeck à rechercher
	 * @return CardDeck
	 * 
	 * @see Carddeck
	 */
    public Carddeck findById(long id) {
        for(Carddeck carddeck : carddecks){
            if(carddeck.getId() == id){
                return carddeck;
            }
        }
        return null;
    }
    
    /**
     * Enregistre un carddeck
     * 
     * @param carddeck
     * 				Le carddeck a enregistrer
     * 
     * @see Carddeck
     */
    public void saveCardDeck(Carddeck carddeck) {
    	carddeck.setId(counter.incrementAndGet());
    	
    	carddecks.add(carddeck);
        
        // Add deck in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(carddeck);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    /**
     * Mettre à jour un carddeck
     * 
     * @param carddeck
     * 				Le carddeck a mettre à jour
     */
    public void updateCardDeck(Carddeck carddeck) {
        int index = carddecks.indexOf(carddeck);
        carddecks.set(index, carddeck);
        
        // update deck in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Carddeck r = (Carddeck) session.load(Carddeck.class, carddeck.getId());
        session.save(r);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    /**
     * Supprime un carddeck en le cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant du carddeck à supprimer
     */
    public void deleteCardDeckById(long id) {
         
        for (Iterator<Carddeck> iterator = carddecks.iterator(); iterator.hasNext(); ) {
            Carddeck carddeck = iterator.next();
            if (carddeck.getId() == id) {
                iterator.remove();
            }
        }
    }
 
    /**
     * Retourne true si le carddeck existe
     * @param deck
     * 				le carddeck a chercher
     * @return boolean
     * 
     */
    public boolean isCardDeckExist(Carddeck carddeck) {
        return false;
    }
     
    /**
     * Supprime toutes les carddecks
     */
    public void deleteAllCardDecks(){
        carddecks.clear();
    }
 
    /**
	 * Retourne les carddeck qui sont dans un deck
	 * 
	 * @param id
	 * 				l'identifiant du deck à rechercher
	 * @return CardDeck
	 * 
	 * @see Carddeck
	 */
    public List<Carddeck> findCardByDeckId(long id) {
        
    	 List<Carddeck> cardByDeck = new ArrayList<Carddeck>();
    	 for(Carddeck carddeck : carddecks){
             if(carddeck.getDeck().getId() == id){
            	 cardByDeck.add(carddeck);
             }	 
         }
    	return cardByDeck;
    }
    
    /**
	 * Retourne les deckcard qui sont associés à une carte
	 * 
	 * @param id
	 * 				l'identifiant de la carte à rechercher
	 * @return CardDeck
	 * 
	 * @see Carddeck
	 */
    public List<Carddeck> findDeckByCardId(long id) {
        
    	 List<Carddeck> cardByDeck = new ArrayList<Carddeck>();
    	 for(Carddeck carddeck : carddecks){
             if(carddeck.getCard().getId() == id){
            	 cardByDeck.add(carddeck);
             }	 
         }
    	return cardByDeck;
    }
    
    /**
     * Retourne la liste de tous les carddecks
     * 
     * @return La liste des carddecks
     */
    private static List<Carddeck> populateDummyCardDecks(){
        // Get List Deck from DB
    	Session session = HibernateUtil.currentSession();
    	Query<Carddeck> query = session.createQuery("from carddeck");
    	List<Carddeck> carddecks = query.getResultList();
    	HibernateUtil.closeSession();
    	
    	return carddecks;
    } 
}
