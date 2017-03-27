package com.ofrancois.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
/* Table Card*/
@Entity
@Table ( name = "card")
public class Card {

	private long id;
     
    private String nameFr;
    
    private String nameEn;
    
    //private int type_id;
    //private String type;
    private Type type;
    
    
    private Edition edition;
     
    private String manaCost;
    
    private Rarity rarity;
     
    private float price;
    
    private int nbItem;
    
    private int nbDispo;
    
    private Date date;
     
    public Card(){}
     
    public Card(long id, String nameFr, String nameEn, String type, String edition, String manaCost, String rarity, float price, int nbItem){
        this.id = id;
        this.nameFr = nameFr;
        this.nameEn = nameEn;
        //this.type = type;
        //this.edition = edition;
        this.manaCost = manaCost;
        //this.rarity = rarity;
        this.price = price;
        this.nbItem = nbItem;
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
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    public Type getType() {
        return type;
    }
 
    public void setType(Type type) {
        this.type = type;
    }
 
    /*public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }*/
    
    /*@Column(name = "TYPE_ID")
    public int getTypeId() {
        return type_id;
    }
 
    public void setTypeId(int type_id) {
        this.type_id = type_id;
    }*/
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "edition_id")
    public Edition getEdition() {
        return edition;
    }
 
    public void setEdition(Edition edition) {
        this.edition = edition;
    }
    
    @Column(name = "MANACOST")
    public String getManaCost() {
        return manaCost;
    }
 
    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rarity_id")
    public Rarity getRarity() {
        return rarity;
    }
 
    public void setRarity(Rarity rarity) {
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

    @Column(name = "DISPONIBILITY")
    public int getNbDispo() {
        return nbDispo;
    }
 
    public void setNbDispo(int nbDispo) {
        this.nbDispo = nbDispo;
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
                + ", rarity=" + rarity + ", price=" + price + ", nbItem=" + nbItem +", nbDispo=" + nbDispo +", date=" + date + "]";
    }

    @Column(name = "DATECREATION")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	} 
}