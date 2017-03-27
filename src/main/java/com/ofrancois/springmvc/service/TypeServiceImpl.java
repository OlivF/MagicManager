package com.ofrancois.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.ofrancois.springmvc.hibernate.HibernateUtil;
import com.ofrancois.springmvc.model.Edition;
import com.ofrancois.springmvc.model.Rarity;
import com.ofrancois.springmvc.model.Type;

@Service("typeService")
public class TypeServiceImpl implements TypeService{
     
    private static final AtomicLong counter = new AtomicLong();
     
    private static List<Type> types;
     
    static{
        types= populateDummyTypes();
    }
 
    public List<Type> findAllTypes() {
        return types;
    }
     
    public Type findById(long id) {
        for(Type type : types){
            if(type.getTypeId() == id){
                return type;
            }
        }
        return null;
    }
     
    public Type findByName(String name) {
        for(Type type : types){
            if(type.getName().equalsIgnoreCase(name)){
                return type;
            }
        }
        return null;
    }
     
    public void saveType(Type type) {
    	type.setTypeId(counter.incrementAndGet());
        types.add(type);
        
        // Add type in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(type);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    public void updateType(Type type) {
        int index = types.indexOf(type);
        types.set(index, type);
        
        // update type in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Type t = (Type) session.load(Type.class, type.getTypeId());
        t.setName(type.getName());
        session.save(t);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    public void deleteTypeById(long id) {
         
        for (Iterator<Type> iterator = types.iterator(); iterator.hasNext(); ) {
            Type type = iterator.next();
            if (type.getTypeId() == id) {
                iterator.remove();
            }
        }
    }
 
    public boolean isTypeExist(Type type) {
        return findByName(type.getName())!=null;
    }
     
    public void deleteAllTypes(){
        types.clear();
    }
 
    private static List<Type> populateDummyTypes(){
        /*List<Type> types = new ArrayList<Type>();
        types.add(new Type(counter.incrementAndGet(),"Planeswalker"));
        types.add(new Type(counter.incrementAndGet(),"Ephemere"));
        types.add(new Type(counter.incrementAndGet(),"Creature Legendaire"));
        types.add(new Type(counter.incrementAndGet(),"Terrain"));
        types.add(new Type(counter.incrementAndGet(),"Enchantement"));
        types.add(new Type(counter.incrementAndGet(),"Artefact"));
        types.add(new Type(counter.incrementAndGet(),"Rituel"));*/
    	
    	// Get List Type from DB
    	Session session = HibernateUtil.currentSession();
    	Query<Type> query = session.createQuery("from Type");
    	List<Type> types = query.getResultList();
    	HibernateUtil.closeSession();
        return types;
    } 
}
