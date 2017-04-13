package com.ofrancois.springmvc.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Entity (name = "carddeck")
@Table ( name = "carddeck")
public class Carddeck {

	/**
	 * L'ID du carddeck. Cet ID n'est pas modifiable
	 * 
	 * @see Carddeck#setId(long)
	 * @see Carddeck#getId()
	 */
	private long card_deck_id;
    
	/**
	 * L'identifiant du deck
	 * 
	 * @see Carddeck#setDeck(deck)
	 * @see Carddeck#getDeck()
	 */
    private Deck deck;
    
    /**
	 * L'identifiant de la carte
	 * 
	 * @see Carddeck#setCard(Card)
	 * @see Carddeck#getCard()
	 */
    private Card card;
    
    /**
     * La quantity de la carte
     * @see Carddeck#setQuantity(int)
     * @see Carddeck#getQuantity()
     */
    private int quantity;
    
    /**
     * Constructeur CardDeck vide
     */
    public Carddeck(){}
     
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
    public Carddeck(long id, Card card, Deck deck, int quantity){
        this.card_deck_id = id;
        this.card = card;
        this.deck= deck;
        this.quantity = quantity;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deck_id")
    public Deck getDeck() {
        return deck;
    }
 
    /**
     * Met à jour l'identifiant du deck
     * 
     * @param name
     * 				Le nouvel identifiant du deck
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    
    /**
     * Retourne l'identifiant de la carte
     * 
     * @return L'identifiant de la carte
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    public Card getCard() {
        return card;
    }
 
    /**
     * Met à jour de l'identifiant de la carte
     * 
     * @param card_id
     * 				Le nouvel identifiant de la carte
     */
    public void setCard(Card card) {
        this.card = card;
    }
 
    /**
     * Retourne la quantity de la carte
     * 
     * @return La quantité de la carte
     */
    @Column(name = "QUANTITY")
    public int getQuantity() {
        return quantity;
    }
 
    /**
     * Met à jour de la quantity de la carte
     * 
     * @param quantity
     * 				La nouvelle quantity de la carte
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        if (!(obj instanceof Carddeck))
            return false;
        Carddeck other = (Carddeck) obj;
        if (card_deck_id != other.card_deck_id)
            return false;
        return true;
    }
 
    /**
     * Retourne un string contenant les informations relatives à l'objet
     */
    @Override
    public String toString() {
        return "CardDeck [id=" + card_deck_id + ", cardId=" + card.toString() + ", deckId= " + deck.toString() + "]";
    }   
}
