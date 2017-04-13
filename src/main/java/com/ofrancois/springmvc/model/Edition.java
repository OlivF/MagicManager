package com.ofrancois.springmvc.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * <b>Edition est la classe représentant une édition de la collection que l'on souhaite créer.</b>
 * <p>
 * Une édition est caractérisée par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribué définitivement</li>
 * <li>Un nom </li>
 * </ul>
 * </p>
 * <p>
 * De plus, une édition sera ajoutée à chaque Carte.
 * </p>
 * 
 * @see Card
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Entity
@Table ( name = "edition")
public class Edition {

	/**
	 * L'ID de l'édition. Cet ID n'est pas modifiable
	 * 
	 * @see Edition#setId(long)
	 * @see Edition#getId()
	 */
	private long edition_id;
    
	/**
	 * Le nom de l'édition
	 * 
	 * @see Edition#setName(String)
	 * @see Edition#getName()
	 */
    private String name;
         
    /**
     * Constructeur d'une édition
     * 
     * @param id
     * 				L'identifiant de l'édition
     * @param name
     * 				Le nom de l'édition
     */
    public Edition( long id, String name ) {
    	this.setId( id );
    	this.setName( name );
    }
 
    /**
     * Retoune l'identifiant de l'édition
     * 
     * @return l'identifiant de l'édition
     */
    @Id
  	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "EDITION_ID", unique = true, nullable = false)
    public long getId() {
        return edition_id;
    }
 
    /**
     * Met à jour l'identifiant de l'édition
     * 
     * @param id
     * 				Le nouvel identifiant de l'édition
     */
    public void setId( long id ) {
    	if ( id < 0 ) throw new RuntimeException( "Id must be positive" ); 
        this.edition_id = id;
    }
 
    /**
     * Retourne le nom de l'édition
     * 
     * @return Le nom de l'édition, sous forme d'une chaine de caractère
     */
    @Column(name = "NAME")
    public String getName() {
        return name;
    }
 
    /**
     * Met à jour le nom de l'édition
     * 
     * @param name
     * 				Le nouveau nom de l'édition
     */
    public void setName( String name ) {
    	if ( name == null || name.trim().equals("") ) throw new RuntimeException( "Name cannot be empty" ); 
        this.name = name;
    }
 
    /**
     * function hashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (edition_id ^ (edition_id >>> 32));
        return result;
    }
 
    /**
     * function equals
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( !(obj instanceof Edition) )
            return false;
        Edition other = (Edition) obj;
        if ( edition_id != other.edition_id )
            return false;
        return true;
    }
 
    /**
     * Retourne un string contenant les informations relatives à l'objet
     */
    @Override
    public String toString() {
        return "Edition [id=" + edition_id + ", name=" + name + "]";
    }   
}
