package com.ofrancois.springmvc.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table ( name = "edition")
public class Edition {

	private long edition_id;
    
    private String name;
     
    public Edition(){}
     
    public Edition(long id, String name){
        this.edition_id = id;
        this.name = name;
    }
 
    @Id
  	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "EDITION_ID", unique = true, nullable = false)
    public long getId() {
        return edition_id;
    }
 
    public void setId(long id) {
        this.edition_id = id;
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
        result = prime * result + (int) (edition_id ^ (edition_id >>> 32));
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Edition))
            return false;
        Edition other = (Edition) obj;
        if (edition_id != other.edition_id)
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "Edition [id=" + edition_id + ", name=" + name + "]";
    }   
}
