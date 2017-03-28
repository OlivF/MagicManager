package com.ofrancois.springmvc.service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.ofrancois.springmvc.hibernate.HibernateUtil;
import com.ofrancois.springmvc.model.Edition;

/** 
 * <b>EditionServiceImpl est la classe correspondant à l'interface EditionService</b>
 * <p>
 * Cette classe est composée de plusieurs méthodes :
 * <ul>
 * <li>Trouver une édition par son identifiant</li>
 * <li>Trouver une édition par son nom</li>
 * <li>Enregistrer une édition</li>
 * <li>Mettre à jour une édition</li>
 * <li>Supprimer une édition</li>
 * <li>Chercher toutes les édition</li>
 * <li>Supprimer toutes les éditions</li>
 * <li>Vérifier si une édition existe</li>
 * </ul>
 * </p>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Service("editionService")
public class EditionServiceImpl implements EditionService{
     
	/**
	 * Un compteur pour les identifiants
	 */
    private static final AtomicLong counter = new AtomicLong();
     
    /**
     * La liste statique des éditions
     */
    private static List<Edition> editions;
     
    static{
        editions= populateDummyEditions();
    }
 
    /**
     * retourne toutes les édition
     * 
     * @return une liste des éditions
     */
    public List<Edition> findAllEditions() {
        return editions;
    }
     
    /**
	 * Retourne une édition en la cherchant par son identifiant$
	 * 
	 * @param id
	 * 				l'identifiant d'édition à rechercher
	 * @return Edition
	 * 
	 * @see Edition
	 */
    public Edition findById(long id) {
        for(Edition edition : editions){
            if(edition.getId() == id){
                return edition;
            }
        }
        return null;
    }
    
    /**
     * Retourne une Edition en la cherchant par son nom
     * 
     * @param name
     * 				Le nom de l'édition a rechercher
     * @return une édition
     * 
     * @see Edition
     */
    public Edition findByName(String name) {
        for(Edition edition : editions){
            if(edition.getName().equalsIgnoreCase(name)){
                return edition;
            }
        }
        return null;
    }
    
    /**
     * Enregistre une édition
     * 
     * @param edition
     * 				L'édition a enregistrer
     */
    public void saveEdition(Edition edition) {
    	edition.setId(counter.incrementAndGet());
        editions.add(edition);
        
        // Add edition in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(edition);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    /**
     * Mettre à jour une édition
     * 
     * @param edition
     * 				L'édition a mettre à jour
     */
    public void updateEdition(Edition edition) {
        int index = editions.indexOf(edition);
        editions.set(index, edition);
        
        // update edition in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Edition e = (Edition) session.load(Edition.class, edition.getId());
        e.setName(edition.getName());
        session.save(e);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    /**
     * Supprime une édition en la cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant de l'édition à supprimer
     */
    public void deleteEditionById(long id) {
         
        for (Iterator<Edition> iterator = editions.iterator(); iterator.hasNext(); ) {
            Edition edition = iterator.next();
            if (edition.getId() == id) {
                iterator.remove();
            }
        }
    }
 
    /**
     * Retourne true si l'édition existe
     * @param edition
     * 				l'édition a chercher
     * @return boolean
     * 
     */
    public boolean isEditionExist(Edition edition) {
        return findByName(edition.getName())!=null;
    }
     
    /**
     * Supprime toutes les éditions
     */
    public void deleteAllEditions(){
        editions.clear();
    }
 
    /**
     * Récupère la liste de toutes les éditions dans la base
     * 
     * @return la liste des éditions
     */
    private static List<Edition> populateDummyEditions(){
        // Get List Edition from DB
    	Session session = HibernateUtil.currentSession();
    	Query<Edition> query = session.createQuery("from Edition");
    	List<Edition> editions = query.getResultList();
    	HibernateUtil.closeSession();
        return editions;
    } 
}
