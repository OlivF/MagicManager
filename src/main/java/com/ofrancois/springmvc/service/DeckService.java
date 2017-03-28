package com.ofrancois.springmvc.service;

import java.util.List;

import com.ofrancois.springmvc.model.Deck;

/** 
 * <b>DeckService est l'interface de la classe DeckServiceImpl</b>
 * <p>
 * Cette interface est composée de plusieurs méthodes :
 * <ul>
 * <li>Trouver un deck par son identifiant</li>
 * <li>Trouver un deck par son nom</li>
 * <li>Enregistrer un deck</li>
 * <li>Mettre à jour un deck</li>
 * <li>Supprimer un deck</li>
 * <li>Chercher tous les decks</li>
 * <li>Supprimer tous les decks</li>
 * <li>Vérifier si un deck existe</li>
 * </ul>
 * </p>
 * 
 * @author Olivier F.
 * @version 1.0
 */
public interface DeckService {
    
	/**
	 * Retourne un deck en la cherchant par son identifiant$
	 * 
	 * @param id
	 * 				l'identifiant du deck à rechercher
	 * @return Deck
	 * 
	 * @see Deck
	 */
    Deck findById(long id);
     
    /**
     * Retourne un deck en la cherchant par son nom
     * 
     * @param name
     * 				Le nom du deck a rechercher
     * @return un deck
     * 
     * @see Deck
     */
    Deck findByName(String name);
    
    /**
     * Enregistre un deck
     * 
     * @param deck
     * 				Le deck a enregistrer
     * 
     * @see Deck
     */
    void saveDeck(Deck deck);
    
    /**
     * Mettre à jour un deck
     * 
     * @param deck
     * 				Le deck a mettre à jour
     */
    void updateDeck(Deck deck);
    
    /**
     * Supprime un deck en la cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant du deck à supprimer
     */
    void deleteDeckById(long id);
 
    /**
     * retourne tous les decks
     * 
     * @return une liste des decks
     */
    List<Deck> findAllDecks(); 
    
    /**
     * Supprime tous les decks
     */
    void deleteAllDecks();
    
    /**
     * Retourne true si le deck existe
     * @param deck
     * 				le deck a chercher
     * @return boolean
     * 
     */
    public boolean isDeckExist(Deck deck);
     
}