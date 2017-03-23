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

@Service("rarityService")
public class RarityServiceImpl implements RarityService{
     
    private static final AtomicLong counter = new AtomicLong();
     
    private static List<Rarity> raritys;
     
    static{
        raritys= populateDummyRaritys();
    }
 
    public List<Rarity> findAllRaritys() {
        return raritys;
    }
     
    public Rarity findById(long id) {
        for(Rarity rarity : raritys){
            if(rarity.getId() == id){
                return rarity;
            }
        }
        return null;
    }
     
    public Rarity findByName(String name) {
        for(Rarity rarity : raritys){
            if(rarity.getName().equalsIgnoreCase(name)){
                return rarity;
            }
        }
        return null;
    }
     
    public void saveRarity(Rarity rarity) {
    	rarity.setId(counter.incrementAndGet());
        raritys.add(rarity);
        
        // Add rarity in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(rarity);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    public void updateRarity(Rarity rarity) {
        int index = raritys.indexOf(rarity);
        raritys.set(index, rarity);
        
        // update rarity in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Rarity r = (Rarity) session.load(Rarity.class, rarity.getId());
        r.setName(rarity.getName());
        session.save(r);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    public void deleteRarityById(long id) {
         
        for (Iterator<Rarity> iterator = raritys.iterator(); iterator.hasNext(); ) {
            Rarity rarity = iterator.next();
            if (rarity.getId() == id) {
                iterator.remove();
            }
        }
    }
 
    public boolean isRarityExist(Rarity rarity) {
        return findByName(rarity.getName())!=null;
    }
     
    public void deleteAllRaritys(){
        raritys.clear();
    }
 
    private static List<Rarity> populateDummyRaritys(){
        /*List<Rarity> raritys = new ArrayList<Rarity>();
        raritys.add(new Rarity(counter.incrementAndGet(),"Commune"));
        raritys.add(new Rarity(counter.incrementAndGet(),"Unco"));
        raritys.add(new Rarity(counter.incrementAndGet(),"Rare"));
        raritys.add(new Rarity(counter.incrementAndGet(),"Mythique"));
        raritys.add(new Rarity(counter.incrementAndGet(),"L�gende"));*/
    	
    	// Get List Rarity from DB
    	Session session = HibernateUtil.currentSession();
    	Query<Rarity> query = session.createQuery("from Rarity");
    	List<Rarity> raritys = query.getResultList();
    	HibernateUtil.closeSession();
        return raritys;
    } 
}
