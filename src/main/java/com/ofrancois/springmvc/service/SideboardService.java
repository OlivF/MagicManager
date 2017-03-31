package com.ofrancois.springmvc.service;

import java.util.List;

import com.ofrancois.springmvc.model.Carddeck;
import com.ofrancois.springmvc.model.Deck;
import com.ofrancois.springmvc.model.Sideboard;

/** 
 * <b>CardDeckService est l'interface de la classe CardDeckServiceImpl</b>
 * <p>
 * Cette interface est composée de plusieurs méthodes :
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
public interface SideboardService {
    
	/**
	 * Retourne un carddeck en la cherchant par son identifiant
	 * 
	 * @param id
	 * 				l'identifiant du carddeck à rechercher
	 * @return CardDeck
	 * 
	 * @see Carddeck
	 */
    Sideboard findById(long id);
    
    /**
     * Enregistre un carddeck
     * 
     * @param carddeck
     * 				Le carddeck a enregistrer
     * 
     * @see Carddeck
     */
    void saveSideboard(Sideboard sideboard);
    
    /**
     * Mettre à jour un carddeck
     * 
     * @param carddeck
     * 				Le carddeck a mettre à jour
     */
    void updateSideboard(Sideboard sideboard);
    
    /**
     * Supprime un carddeck en la cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant du carddeck à supprimer
     */
    void deleteSideboardById(long id);
 
    /**
     * retourne tous les carddecks
     * 
     * @return une liste des carddecks
     */
    List<Sideboard> findAllSideboards(); 
    
    /**
     * retourne tous les carddecks d'un deck
     * 
     * @return une liste des carddecks
     */
    List<Sideboard> findCardByDeckId(long id);
    
    /**
     * retourne tous les carddecks d'une carte
     * 
     * @return une liste des carddecks
     */
    List<Sideboard> findDeckByCardId(long id);
    
    /**
     * Supprime tous les carddecks
     */
    void deleteAllSideboard();
    
    /**
     * Retourne true si le carddeck existe
     * @param carddeck
     * 				le carddeck a chercher
     * @return boolean
     * 
     */
    public boolean isSideboardExist(Sideboard sideboard);
     
}