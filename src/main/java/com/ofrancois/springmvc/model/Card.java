package com.ofrancois.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
/* Table Card*/
@Entity
@Table ( name = "card")
public class Card {

	private long id;
     
    private String nameFr;
    
    private String nameEn;
    
    private String type;
    
    private String edition;
     
    private String manaCost;
    
    private String rarity;
     
    private float price;
    
    private int nbItem;
    
    private int nbDispo;
     
    public Card(){}
     
    public Card(long id, String nameFr, String nameEn, String type, String edition, String manaCost, String rarity, float price, int nbItem){
        this.id = id;
        this.nameFr = nameFr;
        this.nameEn = nameEn;
        this.type = type;
        this.edition = edition;
        this.manaCost = manaCost;
        this.rarity = rarity;
        this.price = price;
        this.nbItem = nbItem;
        this.nbDispo = nbItem;
    }
 
    @Id
	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    @Column(name = "NAMEFR")
    public String getNameFr() {
        return nameFr;
    }
 
    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }
    
    @Column(name = "NAMEEN")
    public String getNameEn() {
        return nameEn;
    }
 
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
 
    @Column(name = "TYPE")
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name = "EDITION")
    public String getEdition() {
        return edition;
    }
 
    public void setEdition(String edition) {
        this.edition = edition;
    }
    
    @Column(name = "MANACOST")
    public String getManaCost() {
        return manaCost;
    }
 
    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }
    
    @Column(name = "RARITY")
    public String getRarity() {
        return rarity;
    }
 
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
    
    @Column(name = "PRICE")
    public float getPrice() {
        return price;
    }
 
    public void setPrice(float price) {
        this.price = price;
    }
   
    @Column(name = "NBITEM")
    public int getNbItem() {
        return nbItem;
    }
 
    public void setNbItem(int nbItem) {
        this.nbItem = nbItem;
    }
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Card))
            return false;
        Card other = (Card) obj;
        if (id != other.id)
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "Card [id=" + id + ", nameFr=" + nameFr + ", nameEn=" + nameEn
                + ", type=" + type + ", edition=" + edition + ", manaCost=" + manaCost 
                + ", rarity=" + rarity + ", price=" + price + ", nbItem=" + nbItem +", nbDispo=" + nbDispo +"]";
    } 
}