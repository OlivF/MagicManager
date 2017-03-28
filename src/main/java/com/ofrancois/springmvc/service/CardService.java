package com.ofrancois.springmvc.service;

import java.util.List;

import com.ofrancois.springmvc.model.Card;
 
/** 
 * <b>CardService est l'interface de la classe CardServiceImpl</b>
 * <p>
 * Cette interface est composée de plusieurs méthodes :
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
public interface CardService {
     
	/**
	 * Retourne une carte en la cherchant par son identifiant$
	 * 
	 * @param id
	 * 				l'identifiant de la carte à rechercher
	 * @return Card
	 * 
	 * @see Card
	 */
    Card findById(long id);
     
    /**
     * Retourne une Card en la cherchant par son nom
     * 
     * @param name
     * 				Le nom de la carte a rechercher
     * @return une carte
     * 
     * @see Card
     */
    Card findByName(String name);
     
    /**
     * Enregistre une carte
     * 
     * @param card
     * 				La carte a enregistrer
     */
    void saveCard(Card card);
    
    /**
     * Mettre à jour une carte
     * 
     * @param card
     * 				La carte a mettre à jour
     */
    void updateCard(Card card);
     
    /**
     * Supprime une carte en la cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant de la carte à supprimer
     */
    void deleteCardById(long id);
 
    /**
     * retourne toutes les cartes
     * 
     * @return une liste de cartes
     */
    List<Card> findAllCards(); 
     
    /**
     * Supprime toutes les cartes
     */
    void deleteAllCards();
     
    /**
     * Retourne true si la card existe
     * @param card
     * 				la carte a chercher
     * @return boolean
     * 
     */
    public boolean isCardExist(Card card);
     
}
