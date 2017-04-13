package com.ofrancois.springmvc.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * <b>Type est la classe représentant un type de la collection que l'on souhaite créer.</b>
 * <p>
 * Un type est caractérisée par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribué définitivement</li>
 * <li>Un nom </li>
 * </ul>
 * </p>
 * <p>
 * De plus, un type sera ajoutée à chaque Carte.
 * </p>
 * 
 * @see Card
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Entity
@Table ( name = "type")
public class Type {

	/**
	 * L'ID du type. Cet ID n'est pas modifiable
	 * 
	 * @see Type#setId(long)
	 * @see Type#getId()
	 */
	private long type_id;
    
	/**
	 * Le nom du type
	 * 
	 * @see Type#setName(String)
	 * @see Type#getName()
	 */
    private String name;
     
    /**
     * Constructeur d'un type
     * 
     * @param id
     * 				L'identifiant du type
     * @param name
     * 				Le nom du type
     */
    public Type( long id, String name ) {
    	this.setTypeId( id );
    	this.setName( name );
    }
 
    /**
     * Retoune l'identifiant du type
     * 
     * @return l'identifiant du type
     */
    @Id
  	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "type_id", unique = true, nullable = false)
    public long getTypeId() {
        return type_id;
    }
 
    /**
     * Met à jour l'identifiant du type
     * 
     * @param id
     * 				Le nouvel identifiant du type
     */
    public void setTypeId( long id ) {
    	if ( id < 0 ) throw new RuntimeException( "Id must be positive" );
        this.type_id = id;
    }
 
    /**
     * Retourne le nom du type
     * 
     * @return Le nom du type, sous forme d'une chaine de caractère
     */
    @Column(name = "NAME")
    public String getName() {
        return name;
    }
 
    /**
     * Met à jour le nom du type
     * 
     * @param name
     * 				Le nouveau nom du type
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
        result = prime * result + (int) ( type_id ^ ( type_id >>> 32 ) );
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
        if ( !(obj instanceof Type) )
            return false;
        Type other = (Type) obj;
        if ( type_id != other.type_id )
            return false;
        return true;
    }
 
    /**
     * Retourne un string contenant les informations relatives à l'objet
     */
    @Override
    public String toString() {
        return "Type [id=" + type_id + ", name=" + name + "]";
    }   
}
