package com.ofrancois.springmvc.service;

import java.util.List;

import com.ofrancois.springmvc.model.Type;

/** 
 * <b>TypeService est l'interface de la classe TypeServiceImpl</b>
 * <p>
 * Cette interface est composée de plusieurs méthodes :
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
public interface TypeService {
    
	/**
	 * Retourne un type en le cherchant par son identifiant$
	 * 
	 * @param id
	 * 				l'identifiant du type à rechercher
	 * @return Type
	 * 
	 * @see Type
	 */
    Type findById(long id);
    
    /**
     * Enregistre un type en le cherchant par son nom
     * 
     * @param name
     * 				Le nom du type a rechercher
     * @return un type
     * 
     * @see type
     */
    Type findByName(String name);
    
    /**
     * Enregistre un type
     * 
     * @param type
     * 				Le type a enregistrer
     * 
     * @see Type
     */
    void saveType(Type type);
    
    /**
     * Mettre à jour un type
     * 
     * @param type
     * 				Le type a mettre à jour
     */
    void updateType(Type type);
    
    /**
     * Supprime un type en le cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant du type à supprimer
     */
    void deleteTypeById(long id);
 
    /**
     * retourne tous les types
     * 
     * @return une liste des types
     */
    List<Type> findAllTypes(); 
    
    /**
     * Supprime tous les types
     */
    void deleteAllTypes();
    
    /**
     * Retourne true si le type existe
     * @param type
     * 				le type a chercher
     * @return boolean
     * 
     */
    public boolean isTypeExist(Type type);
     
}