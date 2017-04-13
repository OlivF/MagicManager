package com.ofrancois.springmvc.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * <b>Deck est la classe représentant un deck de la collection que l'on souhaite créer.</b>
 * <p>
 * Un deck est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribué définitivement</li>
 * <li>Un nom </li>
 * <li>Une couleur</li>
 * </ul>
 * </p>
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Entity
@Table ( name = "Deck")
public class Deck {

	/**
	 * L'ID du deck. Cet ID n'est pas modifiable
	 * 
	 * @see Deck#setId(long)
	 * @see Deck#getId()
	 */
	private long deck_id;
    
	/**
	 * Le nom du deck
	 * 
	 * @see Deck#setName(String)
	 * @see Deck#getName()
	 */
    private String name;
    
    /**
	 * La couleur du deck
	 * 
	 * @see Deck#setColor(String)
	 * @see Deck#getColor()
	 */
    private String color;
    
    /**
     * Constructeur d'un deck
     *  
     * @param id
     * 				L'identifiant du deck
     * @param name
     * 				Le nom du deck
     * @param color
     * 				La couleur du deck
     */
    public Deck( long id, String name, String color ) {
    	this.setId( id );
    	this.setName( name );
    	this.setColor( color );
    }
 
    /**
     * Retourne l'identifiant du deck
     * 
     * @return l'identifiant du deck
     */
    @Id
  	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "DECK_ID", unique = true, nullable = false)
    public long getId() {
        return deck_id;
    }
 
    /**
     * Met à jour l'identifiant du deck
     * 
     * @param id
     * 				Le nouvel identifiant du deck
     */
    public void setId( long id ) {
    	if ( id < 0 ) throw new RuntimeException( "Id must be positive" ); 
        this.deck_id = id;
    }
 
    /**
     * Retourne le nom du deck
     * 
     * @return Le nom du deck, sous forme d'une chaine de caractère
     */
    @Column(name = "NAME")
    public String getName() {
        return name;
    }
 
    /**
     * Met à jour le nom du deck
     * 
     * @param name
     * 				Le nouveau nom du deck
     */
    public void setName( String name ) {
    	if ( name == null || name.trim().equals("") ) throw new RuntimeException( "Name cannot be empty" ); 
        this.name = name;
    }
    
    /**
     * Retourne la couleur du deck
     * 
     * @return La couleur du deck, sous forme d'une chaine de caractère
     */
    @Column(name = "COLOR")
    public String getColor() {
        return color;
    }
 
    /**
     * Met à jour la couleur du deck
     * 
     * @param color
     * 				La nouvelle couleur du deck
     */
    public void setColor( String color ) {
    	if ( color == null || color.trim().equals("") ) throw new RuntimeException( "Color cannot be empty" ); 
        this.color = color;
    }
 
    /**
     * function hashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (deck_id ^ (deck_id >>> 32));
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
        if (!(obj instanceof Deck))
            return false;
        Deck other = (Deck) obj;
        if (deck_id != other.deck_id)
            return false;
        return true;
    }
 
    /**
     * Retourne un string contenant les informations relatives à l'objet
     */
    @Override
    public String toString() {
        return "Deck [id=" + deck_id + ", name=" + name + ", color= " + color + "]";
    }   
}
