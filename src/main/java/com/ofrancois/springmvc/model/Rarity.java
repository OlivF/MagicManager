package com.ofrancois.springmvc.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * <b>Rarity est la classe représentant une rareté de la collection que l'on souhaite créer.</b>
 * <p>
 * Une rareté est caractérisée par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribué définitivement</li>
 * <li>Un nom </li>
 * </ul>
 * </p>
 * <p>
 * De plus, une rareté sera ajoutée à chaque Carte.
 * </p>
 * 
 * @see Card
 * 
 * @author Olivier F.
 * @version 1.0
 */
@Entity
@Table ( name = "rarity")
public class Rarity {

	/**
	 * L'ID de la rareté. Cet ID n'est pas modifiable
	 * 
	 * @see Rarity#setId(long)
	 * @see Rarity#getId()
	 */
	private long rarity_id;
    
	/**
	 * Le nom de la rareté
	 * 
	 * @see Rarity#setName(String)
	 * @see Rarity#getName()
	 */
    private String name;
     
    /**
     * Constructeur Rarity vide
     */
    public Rarity(){}
    
    /**
     * Constructeur d'une rareté
     * 
     * @param id
     * 				L'identifiant de la rareté
     * @param name
     * 				Le nom de la rareté
     */
    public Rarity(long id, String name){
        this.rarity_id = id;
        this.name = name;
    }
    
    /**
     * Retoune l'identifiant de la rareté
     * 
     * @return l'identifiant de la rareté
     */
    @Id
  	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "rarity_id", unique = true, nullable = false)
    public long getId() {
        return rarity_id;
    }
 
    /**
     * Met à jour l'identifiant de la rareté
     * 
     * @param id
     * 				Le nouvel identifiant de la rareté
     */
    public void setId(long id) {
        this.rarity_id = id;
    }
 
    /**
     * Retourne le nom de la rareté
     * 
     * @return Le nom de la rareté, sous forme d'une chaine de caractère
     */
    @Column(name = "NAME")
    public String getName() {
        return name;
    }
 
    /**
     * Met à jour le nom de la rareté
     * 
     * @param name
     * 				Le nouveau nom de la rareté
     */
    public void setName(String name) {
        this.name = name;
    }
 
    /**
     * function hashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (rarity_id ^ (rarity_id >>> 32));
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
        if (!(obj instanceof Rarity))
            return false;
        Rarity other = (Rarity) obj;
        if (rarity_id != other.rarity_id)
            return false;
        return true;
    }
 
    /**
     * Retourne un string contenant les informations relatives à l'objet
     */
    @Override
    public String toString() {
        return "Rarity [id=" + rarity_id + ", name=" + name + "]";
    }   
}
