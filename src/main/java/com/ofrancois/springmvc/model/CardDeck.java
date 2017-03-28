package com.ofrancois.springmvc.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * <b>CardDeck est la classe représentant la relation entre un deck et une carte de la collection.</b>
 * <p>
 * Cette relation est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribué définitivement</li>
 * <li>L'identifiant du deck</li>
 * <li>L'identifiant de la carte</li>
 * </ul>
 * </p>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Entity
@Table ( name = "CardDeck")
public class CardDeck {

	/**
	 * L'ID du carddeck. Cet ID n'est pas modifiable
	 * 
	 * @see CardDeck#setId(long)
	 * @see CardDeck#getId()
	 */
	private long card_deck_id;
    
	/**
	 * L'identifiant du deck
	 * 
	 * @see CardDeck#setDeckId(long)
	 * @see CardDeck#getDeckId()
	 */
    private long deck_id;
    
    /**
	 * L'identifiant de la carte
	 * 
	 * @see CardDeck#setCardId(long)
	 * @see CardDeck#getCardId()
	 */
    private long card_id;
    
    /**
     * Constructeur CardDeck vide
     */
    public CardDeck(){}
     
    /**
     * Constructeur d'un carddeck
     *  
     * @param id
     * 				L'identifiant du deck
     * @param name
     * 				Le nom du deck
     * @param color
     * 				La couleur du deck
     */
    public CardDeck(long id, long card_id, long deck_id){
        this.card_deck_id = id;
        this.card_id = card_id;
        this.deck_id= deck_id;
    }
 
    /**
     * Retourne l'identifiant du card_deck
     * 
     * @return l'identifiant du card_deck
     */
    @Id
  	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "CARD_DECK_ID", unique = true, nullable = false)
    public long getId() {
        return card_deck_id;
    }
 
    /**
     * Met à jour l'identifiant du carddeck
     * 
     * @param id
     * 				Le nouvel identifiant du carddeck
     */
    public void setId(long id) {
        this.card_deck_id = id;
    }
 
    /**
     * Retourne l'identifiant du deck
     * 
     * @return L'identifiant du deck
     */
    @Column(name = "DECK_ID")
    public long getDeckId() {
        return deck_id;
    }
 
    /**
     * Met à jour l'identifiant du deck
     * 
     * @param name
     * 				Le nouvel identifiant du deck
     */
    public void setDeckId(long deck_id) {
        this.deck_id = deck_id;
    }
    
    /**
     * Retourne l'identifiant de la carte
     * 
     * @return L'identifiant de la carte
     */
    @Column(name = "CARD_ID")
    public long getCardId() {
        return card_id;
    }
 
    /**
     * Met à jour de l'identifiant de la carte
     * 
     * @param card_id
     * 				Le nouvel identifiant de la carte
     */
    public void setCardId(long card_id) {
        this.card_id = card_id;
    }
 
    /**
     * function hashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (card_deck_id ^ (card_deck_id >>> 32));
        return result;
    }
 
    /**
     * function equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof CardDeck))
            return false;
        CardDeck other = (CardDeck) obj;
        if (card_deck_id != other.card_deck_id)
            return false;
        return true;
    }
 
    /**
     * Retourne un string contenant les informations relatives à l'objet
     */
    @Override
    public String toString() {
        return "CardDeck [id=" + card_deck_id + ", cardId=" + card_id + ", deckId= " + deck_id + "]";
    }   
}
