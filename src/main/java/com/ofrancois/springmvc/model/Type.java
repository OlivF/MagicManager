package com.ofrancois.springmvc.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table ( name = "type")
public class Type {

	private long type_id;
    
    private String name;
     
    public Type(){}
    
    public Type(long id, String name){
        this.type_id = id;
        this.name = name;
    }
 
    @Id
  	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "type_id", unique = true, nullable = false)
    public long getTypeId() {
        return type_id;
    }
 
    public void setTypeId(long id) {
        this.type_id = id;
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
        result = prime * result + (int) (type_id ^ (type_id >>> 32));
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Type))
            return false;
        Type other = (Type) obj;
        if (type_id != other.type_id)
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "Type [id=" + type_id + ", name=" + name + "]";
    }   
}
