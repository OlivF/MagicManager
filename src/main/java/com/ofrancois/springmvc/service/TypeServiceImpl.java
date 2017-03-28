package com.ofrancois.springmvc.service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.ofrancois.springmvc.hibernate.HibernateUtil;
import com.ofrancois.springmvc.model.Type;

/** 
 * <b>TyprServiceImpl est la classe correspond à l'interface TypeService</b>
 * <p>
 * Cette classe est composée de plusieurs méthodes :
 * <ul>
 * <li>Trouver un type par son identifiant</li>
 * <li>Trouver un type par son nom</li>
 * <li>Enregistrer un type</li>
 * <li>Mettre à jour un type</li>
 * <li>Supprimer un type</li>
 * <li>Chercher tous les types</li>
 * <li>Supprimer tous les types</li>
 * <li>Vérifier si un type existe</li>
 * </ul>
 * </p>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService{
    
	/**
     * Un compteur pour les identifiants 
     */
    private static final AtomicLong counter = new AtomicLong();
    
    /**
     * La liste static de tous les types
     */
    private static List<Type> types;
     
    static{
        types= populateDummyTypes();
    }
 
    /**
     * retourne tous les types
     * 
     * @return une liste des types
     */
    public List<Type> findAllTypes() {
        return types;
    }
    
    /**
	 * Retourne un type en le cherchant par son identifiant$
	 * 
	 * @param id
	 * 				l'identifiant du type à rechercher
	 * @return Type
	 * 
	 * @see Type
	 */
    public Type findById(long id) {
        for(Type type : types){
            if(type.getTypeId() == id){
                return type;
            }
        }
        return null;
    }
    
    /**
     * Enregistre un type en le cherchant par son nom
     * 
     * @param name
     * 				Le nom du type a rechercher
     * @return un type
     * 
     * @see type
     */
    public Type findByName(String name) {
        for(Type type : types){
            if(type.getName().equalsIgnoreCase(name)){
                return type;
            }
        }
        return null;
    }
    
    /**
     * Enregistre un type
     * 
     * @param type
     * 				Le type a enregistrer
     * 
     * @see Type
     */
    public void saveType(Type type) {
    	type.setTypeId(counter.incrementAndGet());
        types.add(type);
        
        // Add type in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(type);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    /**
     * Mettre à jour un type
     * 
     * @param type
     * 				Le type a mettre à jour
     */
    public void updateType(Type type) {
        int index = types.indexOf(type);
        types.set(index, type);
        
        // update type in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Type t = (Type) session.load(Type.class, type.getTypeId());
        t.setName(type.getName());
        session.save(t);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    /**
     * Supprime un type en le cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant du type à supprimer
     */
    public void deleteTypeById(long id) {
         
        for (Iterator<Type> iterator = types.iterator(); iterator.hasNext(); ) {
            Type type = iterator.next();
            if (type.getTypeId() == id) {
                iterator.remove();
            }
        }
    }
 
    /**
     * Retourne true si le type existe
     * @param type
     * 				le type a chercher
     * @return boolean
     * 
     */
    public boolean isTypeExist(Type type) {
        return findByName(type.getName())!=null;
    }
    
    /**
     * Supprime tous les types
     */
    public void deleteAllTypes(){
        types.clear();
    }
 
    /**
     * Retourne la liste des types de la base de données
     * @return
     */
    private static List<Type> populateDummyTypes(){
        // Get List Type from DB
    	Session session = HibernateUtil.currentSession();
    	Query<Type> query = session.createQuery("from Type");
    	List<Type> types = query.getResultList();
    	HibernateUtil.closeSession();
        return types;
    } 
}
