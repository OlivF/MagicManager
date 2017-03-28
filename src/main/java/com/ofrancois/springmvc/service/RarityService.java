package com.ofrancois.springmvc.service;

import java.util.List;

import com.ofrancois.springmvc.model.Rarity;

/** 
 * <b>RarityService est l'interface de la classe RarityServiceImpl</b>
 * <p>
 * Cette interface est composée de plusieurs méthodes :
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
public interface RarityService {
    
	/**
	 * Retourne une rareté en la cherchant par son identifiant$
	 * 
	 * @param id
	 * 				l'identifiant de la rareté à rechercher
	 * @return Rarity
	 * 
	 * @see Rarity
	 */
    Rarity findById(long id);
     
    /**
     * Retourne une rareté en la cherchant par son nom
     * 
     * @param name
     * 				Le nom de la rareté a rechercher
     * @return une rareté
     * 
     * @see Rarity
     */
    Rarity findByName(String name);
    
    /**
     * Enregistre une rareté
     * 
     * @param rarity
     * 				La rareté a enregistrer
     * 
     * @see Rarity
     */
    void saveRarity(Rarity rarity);
    
    /**
     * Mettre à jour une rareté
     * 
     * @param rareté
     * 				La rareté a mettre à jour
     */
    void updateRarity(Rarity rarity);
    
    /**
     * Supprime une rareté en la cherchant par son identifiant
     * 
     * @param id
     * 				l'identifiant de la rareté à supprimer
     */
    void deleteRarityById(long id);
 
    /**
     * retourne toutes les raretés
     * 
     * @return une liste des raretés
     */
    List<Rarity> findAllRaritys(); 
    
    /**
     * Supprime toutes les raretés
     */
    void deleteAllRaritys();
    
    /**
     * Retourne true si la rareté existe
     * @param rarity
     * 				la rareté a chercher
     * @return boolean
     * 
     */
    public boolean isRarityExist(Rarity rarity);
     
}