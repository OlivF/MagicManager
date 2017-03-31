package com.ofrancois.springmvc.service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.ofrancois.springmvc.hibernate.HibernateUtil;
import com.ofrancois.springmvc.model.Card;
import com.ofrancois.springmvc.model.Edition;
import com.ofrancois.springmvc.model.Rarity;
import com.ofrancois.springmvc.model.Type;
 
/** 
 * <b>CardServiceImpl est la classe associée à l'interface CardService</b>
 * <p>
 * Cette classe est composée de plusieurs méthodes :
 * <ul>
 * <li>Trouver une carte par son identifiant</li>
 * <li>Trouver une carte par son nom</li>
 * <li>Enregistrer une carte</li>
 * <li>Mettre à jour une carte</li>
 * <li>Supprimer une carte</li>
 * <li>Chercher toutes les cartes</li>
 * <li>Supprimer toutes cartes</li>
 * <li>Vérifier si une carte existe</li>
 * </ul>
 * </p>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Service("cardService")
public class CardServiceImpl implements CardService{
     
	/**
	 * Un compteur pour les identifiants
	 */
    private static final AtomicLong counter = new AtomicLong();
     
    /**
     * La liste static des cartes
     */
    private static List<Card> cards;
     
    static{
        cards= populateDummyCards();
    }
 
    /**
     * retourne toutes les cartes
     * 
     * @return une liste de cartes
     */
    public List<Card> findAllCards() {
    	cards= populateDummyCards();
        return cards;
    }
    
    /**
	 * Retourne une carte en la cherchant par son identifiant$
	 * 
	 * @param id
	 * 				l'identifiant de la carte à rechercher
	 * @return Card
	 * 
	 * @see Card
	 */
    public Card findById(long id) {
        for(Card card : cards){
            if(card.getId() == id){
                return card;
            }
        }
        return null;
    }
    
    /**
     * Retourne une Card en la cherchant par son nom
     * 
     * @param name
     * 				Le nom de la carte a rechercher
     * @return une carte
     * 
     * @see Card
     */
    public Card findByName(String name) {
        for(Card card : cards){
            if(card.getNameFr().equalsIgnoreCase(name)){
                return card;
            }
        }
        return null;
    }
    
    /**
     * Enregistre une carte
     * 
     * @param card
     * 				La carte a enregistrer
     */
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
 
    /**
     * Mettre à jour une carte
     * 
     * @param card
     * 				La carte a mettre à jour
     */
    public void updateCard(Card card) {
        int index = cards.indexOf(card);
        cards.set(index, card);
        System.out.println("On UPDATE");
        System.out.println(card.toString());
        // update card in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Card c = (Card) session.load(Card.class, card.getId());
        Type t = (Type) session.load(Type.class, card.getType().getTypeId());
        Rarity r = (Rarity) session.load(Rarity.class, card.getRarity().getId());
        Edition e = (Edition) session.load(Edition.class, card.getEdition().getId());
        c.setNameFr(card.getNameFr());
        c.setNameEn(card.getNameEn());
        c.setType(t);
        c.setEdition(e);
        c.setManaCost(card.getManaCost());
        c.setRarity(r);
        c.setPrice(card.getPrice());
        c.setNbItem(card.getNbItem()); 
        
        c.setNbDispo(card.getNbDispo());
        
        session.save(c);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    /**
     * Supprime une carte en la cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant de la carte à supprimer
     */
    public void deleteCardById(long id) {
         
    	cards= populateDummyCards();
        for (Iterator<Card> iterator = cards.iterator(); iterator.hasNext(); ) {
            Card card = iterator.next();
            if (card.getId() == id) {
                iterator.remove();
                
                Session session = HibernateUtil.currentSession();
                Transaction tx = session.beginTransaction();
                session.createQuery("delete from Card where id = :id").setLong("id", id).executeUpdate();
                tx.commit();
                HibernateUtil.closeSession();
                
                
            	//List<Card> cards = query.getResultList();
            	HibernateUtil.closeSession();
            }
        }
    }
 
    /**
     * Retourne true si la card existe
     * @param card
     * 				la carte a chercher
     * @return boolean
     * 
     */
    public boolean isCardExist(Card card) {
        return false;
    }
     
    /**
     * Supprime toutes les cartes
     */
    public void deleteAllCards(){
        cards.clear();
    }
 
    /**
     * retourne toutes les cartes de la base
     * 
     * @return une liste de cartes
     */
    private static List<Card> populateDummyCards(){
    	
    	// Get List Card from DB
    	Session session = HibernateUtil.currentSession();
    	Query<Card> query = session.createQuery("from Card");
    	List<Card> cards = query.getResultList();
    	HibernateUtil.closeSession();
    	
        return cards;
    } 
}