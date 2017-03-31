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
import com.ofrancois.springmvc.model.Sideboard;

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
@Service("sideboardService")
public class SideboardServiceImpl implements SideboardService{
     
	
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
    private static List<Sideboard> sideboards;
     
    static{
        sideboards= populateDummySideboard();
    }
 
    /**
     * retourne tous les carddecks
     * 
     * @return une liste des carddecks
     */
    public List<Sideboard> findAllSideboards() {
    	sideboards = populateDummySideboard();
        return sideboards;
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
    public Sideboard findById(long id) {
        for(Sideboard sideboard : sideboards){
            if(sideboard.getId() == id){
                return sideboard;
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
    public void saveSideboard(Sideboard sideboard) {
    	sideboard.setId(counter.incrementAndGet());
    	
    	sideboards.add(sideboard);
        
        // Add deck in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(sideboard);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    /**
     * Mettre à jour un carddeck
     * 
     * @param carddeck
     * 				Le carddeck a mettre à jour
     */
    public void updateSideboard(Sideboard sideboard) {
        int index = sideboards.indexOf(sideboard);
        sideboards.set(index, sideboard);
        
        // update deck in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Sideboard r = (Sideboard) session.load(Sideboard.class, sideboard.getId());
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
    public void deleteSideboardById(long id) {
         
        for (Iterator<Sideboard> iterator = sideboards.iterator(); iterator.hasNext(); ) {
            Sideboard sideboard = iterator.next();
            if (sideboard.getId() == id) {
                iterator.remove();
                
                Session session = HibernateUtil.currentSession();
                Transaction tx = session.beginTransaction();
                session.createQuery("delete from sideboard where id = :id").setLong("id", id).executeUpdate();
                tx.commit();
                HibernateUtil.closeSession();
                
                
                
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
    public boolean isSideboardExist(Sideboard sideboard) {
        return false;
    }
     
    /**
     * Supprime toutes les carddecks
     */
    public void deleteAllSideboard(){
        sideboards.clear();
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
    public List<Sideboard> findCardByDeckId(long id) {
    	 sideboards = populateDummySideboard();
    	 List<Sideboard> sideboardList = new ArrayList<Sideboard>();
    	 for(Sideboard sideboard : sideboards){
             if(sideboard.getDeck().getId() == id){
            	 sideboardList.add(sideboard);
             }	 
         }
    	return sideboardList;
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
    public List<Sideboard> findDeckByCardId(long id) {
    	 sideboards = populateDummySideboard();
    	 List<Sideboard> sideboardList = new ArrayList<Sideboard>();
    	 for(Sideboard sideboard : sideboards){
             if(sideboard.getCard().getId() == id){
            	 sideboardList.add(sideboard);
             }	 
         }
    	return sideboardList;
    }
    
    /**
     * Retourne la liste de tous les carddecks
     * 
     * @return La liste des carddecks
     */
    private static List<Sideboard> populateDummySideboard(){
        // Get List Deck from DB
    	Session session = HibernateUtil.currentSession();
    	Query<Sideboard> query = session.createQuery("from sideboard");
    	List<Sideboard> sideboards = query.getResultList();
    	HibernateUtil.closeSession();
    	
    	return sideboards;
    } 
}
