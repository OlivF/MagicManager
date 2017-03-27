package com.ofrancois.springmvc.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table ( name = "rarity")
public class Rarity {

	private long rarity_id;
    
    private String name;
     
    public Rarity(){}
    
    public Rarity(long id, String name){
        this.rarity_id = id;
        this.name = name;
    }
    
    @Id
  	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "rarity_id", unique = true, nullable = false)
    public long getId() {
        return rarity_id;
    }
 
    public void setId(long id) {
        this.rarity_id = id;
    }
 
    @Column(name = "NAME")
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (rarity_id ^ (rarity_id >>> 32));
        return result;
    }
 
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
 
    @Override
    public String toString() {
        return "Rarity [id=" + rarity_id + ", name=" + name + "]";
    }   
}
