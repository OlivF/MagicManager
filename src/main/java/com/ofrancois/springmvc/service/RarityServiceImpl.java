package com.ofrancois.springmvc.service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.ofrancois.springmvc.hibernate.HibernateUtil;
import com.ofrancois.springmvc.model.Rarity;

/** 
 * <b>RarityServiceImpl est la classe correspondant à l'interface RarityService</b>
 * <p>
 * Cette classe est composée de plusieurs méthodes :
 * <ul>
 * <li>Trouver une rareté par son identifiant</li>
 * <li>Trouver une rareté par son nom</li>
 * <li>Enregistrer une rareté</li>
 * <li>Mettre à jour une rareté</li>
 * <li>Supprimer une rareté</li>
 * <li>Chercher toutes les raretés</li>
 * <li>Supprimer toutes les raretés</li>
 * <li>Vérifier si une rareté existe</li>
 * </ul>
 * </p>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Service("rarityService")
public class RarityServiceImpl implements RarityService{
     
	/**
	 * un compteur pour les identifiants
	 */
    private static final AtomicLong counter = new AtomicLong();
     
    /**
     * La liste statiques des raretés
     */
    private static List<Rarity> raritys;
     
    static{
        raritys= populateDummyRaritys();
    }
 
    /**
     * retourne toutes les raretés
     * 
     * @return une liste des raretés
     */
    public List<Rarity> findAllRaritys() {
        return raritys;
    }
    
    /**
	 * Retourne une rareté en la cherchant par son identifiant$
	 * 
	 * @param id
	 * 				l'identifiant de la rareté à rechercher
	 * @return Rarity
	 * 
	 * @see Rarity
	 */
    public Rarity findById(long id) {
        for(Rarity rarity : raritys){
            if(rarity.getId() == id){
                return rarity;
            }
        }
        return null;
    }
    
    /**
     * Retourne une rareté en la cherchant par son nom
     * 
     * @param name
     * 				Le nom de la rareté a rechercher
     * @return une rareté
     * 
     * @see Rarity
     */
    public Rarity findByName(String name) {
        for(Rarity rarity : raritys){
            if(rarity.getName().equalsIgnoreCase(name)){
                return rarity;
            }
        }
        return null;
    }
    
    /**
     * Enregistre une rareté
     * 
     * @param rarity
     * 				La rareté a enregistrer
     * 
     * @see Rarity
     */
    public void saveRarity(Rarity rarity) {
    	rarity.setId(counter.incrementAndGet());
        raritys.add(rarity);
        
        // Add rarity in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(rarity);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    /**
     * Mettre à jour une rareté
     * 
     * @param rareté
     * 				La rareté a mettre à jour
     */
    public void updateRarity(Rarity rarity) {
        int index = raritys.indexOf(rarity);
        raritys.set(index, rarity);
        
        // update rarity in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Rarity r = (Rarity) session.load(Rarity.class, rarity.getId());
        r.setName(rarity.getName());
        session.save(r);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    /**
     * Supprime une rareté en la cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant de la rareté à supprimer
     */
    public void deleteRarityById(long id) {
         
        for (Iterator<Rarity> iterator = raritys.iterator(); iterator.hasNext(); ) {
            Rarity rarity = iterator.next();
            if (rarity.getId() == id) {
                iterator.remove();
            }
        }
    }
 
    /**
     * Retourne true si la rareté existe
     * @param rarity
     * 				la rareté a chercher
     * @return boolean
     * 
     */
    public boolean isRarityExist(Rarity rarity) {
        return findByName(rarity.getName())!=null;
    }
     
    /**
     * Supprime toutes les raretés
     */
    public void deleteAllRaritys(){
        raritys.clear();
    }
 
    /**
     * Retourne la liste de toutes les raretés
     * 
     * @return La liste des raretés
     */
    private static List<Rarity> populateDummyRaritys(){
        // Get List Rarity from DB
    	Session session = HibernateUtil.currentSession();
    	Query<Rarity> query = session.createQuery("from Rarity");
    	List<Rarity> raritys = query.getResultList();
    	HibernateUtil.closeSession();
        return raritys;
    } 
}
