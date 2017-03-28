package com.ofrancois.springmvc.service;

import java.util.List;

import com.ofrancois.springmvc.model.CardDeck;
import com.ofrancois.springmvc.model.Deck;

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
public interface CardDeckService {
    
	/**
	 * Retourne un carddeck en la cherchant par son identifiant
	 * 
	 * @param id
	 * 				l'identifiant du carddeck à rechercher
	 * @return CardDeck
	 * 
	 * @see CardDeck
	 */
    CardDeck findById(long id);
    
    /**
     * Enregistre un carddeck
     * 
     * @param carddeck
     * 				Le carddeck a enregistrer
     * 
     * @see CardDeck
     */
    void saveCardDeck(CardDeck carddeck);
    
    /**
     * Mettre à jour un carddeck
     * 
     * @param carddeck
     * 				Le carddeck a mettre à jour
     */
    void updateCardDeck(CardDeck carddeck);
    
    /**
     * Supprime un carddeck en la cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant du carddeck à supprimer
     */
    void deleteCardDeckById(long id);
 
    /**
     * retourne tous les carddecks
     * 
     * @return une liste des carddecks
     */
    List<CardDeck> findAllCardDecks(); 
    
    /**
     * Supprime tous les carddecks
     */
    void deleteAllCardDecks();
    
    /**
     * Retourne true si le carddeck existe
     * @param carddeck
     * 				le carddeck a chercher
     * @return boolean
     * 
     */
    public boolean isCardDeckExist(CardDeck carddeck);
     
}