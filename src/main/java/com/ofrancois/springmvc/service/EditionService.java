package com.ofrancois.springmvc.service;

import java.util.List;
import com.ofrancois.springmvc.model.Edition;

/** 
 * <b>EditionService est l'interface de la classe EditionServiceImpl</b>
 * <p>
 * Cette interface est composée de plusieurs méthodes :
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
public interface EditionService {
    
	/**
	 * Retourne une édition en la cherchant par son identifiant$
	 * 
	 * @param id
	 * 				l'identifiant d'édition à rechercher
	 * @return Edition
	 * 
	 * @see Edition
	 */
    Edition findById(long id);
    
    /**
     * Retourne une Edition en la cherchant par son nom
     * 
     * @param name
     * 				Le nom de l'édition a rechercher
     * @return une édition
     * 
     * @see Edition
     */
    Edition findByName(String name);
     
    /**
     * Enregistre une édition
     * 
     * @param edition
     * 				L'édition a enregistrer
     */
    void saveEdition(Edition edition);
     
    /**
     * Mettre à jour une édition
     * 
     * @param edition
     * 				L'édition a mettre à jour
     */
    void updateEdition(Edition edition);
     
    /**
     * Supprime une édition en la cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant de l'édition à supprimer
     */
    void deleteEditionById(long id);
 
    /**
     * retourne toutes les édition
     * 
     * @return une liste des éditions
     */
    List<Edition> findAllEditions(); 
    
    /**
     * Supprime toutes les éditions
     */
    void deleteAllEditions();
    
    /**
     * Retourne true si l'édition existe
     * @param edition
     * 				l'édition a chercher
     * @return boolean
     * 
     */
    public boolean isEditionExist(Edition edition);
     
}