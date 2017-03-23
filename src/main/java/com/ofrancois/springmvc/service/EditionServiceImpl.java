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
import com.ofrancois.springmvc.model.Card;
import com.ofrancois.springmvc.model.Edition;

@Service("editionService")
public class EditionServiceImpl implements EditionService{
     
    private static final AtomicLong counter = new AtomicLong();
     
    private static List<Edition> editions;
     
    static{
        editions= populateDummyEditions();
    }
 
    public List<Edition> findAllEditions() {
        return editions;
    }
     
    public Edition findById(long id) {
        for(Edition edition : editions){
            if(edition.getId() == id){
                return edition;
            }
        }
        return null;
    }
     
    public Edition findByName(String name) {
        for(Edition edition : editions){
            if(edition.getName().equalsIgnoreCase(name)){
                return edition;
            }
        }
        return null;
    }
     
    public void saveEdition(Edition edition) {
    	edition.setId(counter.incrementAndGet());
        editions.add(edition);
        
        // Add edition in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(edition);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    public void updateEdition(Edition edition) {
        int index = editions.indexOf(edition);
        editions.set(index, edition);
        
        // update edition in DB
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Edition e = (Edition) session.load(Edition.class, edition.getId());
        e.setName(edition.getName());
        session.save(e);
        tx.commit();
        HibernateUtil.closeSession();
    }
 
    public void deleteEditionById(long id) {
         
        for (Iterator<Edition> iterator = editions.iterator(); iterator.hasNext(); ) {
            Edition edition = iterator.next();
            if (edition.getId() == id) {
                iterator.remove();
            }
        }
    }
 
    public boolean isEditionExist(Edition edition) {
        return findByName(edition.getName())!=null;
    }
     
    public void deleteAllEditions(){
        editions.clear();
    }
 
    private static List<Edition> populateDummyEditions(){
        //List<Edition> editions = new ArrayList<Edition>();
        /*editions.add(new Edition(counter.incrementAndGet(),"Modern Master 2017"));
        editions.add(new Edition(counter.incrementAndGet(),"Amonkhet"));
        editions.add(new Edition(counter.incrementAndGet(),"Kaladesh"));
        editions.add(new Edition(counter.incrementAndGet(),"La lune herm�tique"));
        editions.add(new Edition(counter.incrementAndGet(),"T�n�bres sur Innistrad"));
        editions.add(new Edition(counter.incrementAndGet(),"Le Serment des Sentinelles"));
        editions.add(new Edition(counter.incrementAndGet(),"La bataille de Zendikar"));
        editions.add(new Edition(counter.incrementAndGet(),"Les Dragons de Tarkir"));
        editions.add(new Edition(counter.incrementAndGet(),"Destin Reforg�s"));
        editions.add(new Edition(counter.incrementAndGet(),"Les Khans de Tarkir"));
        editions.add(new Edition(counter.incrementAndGet(),"Incursion dans Nyx"));
        editions.add(new Edition(counter.incrementAndGet(),"Cr�ations Divines"));
        editions.add(new Edition(counter.incrementAndGet(),"Temp�te"));
        editions.add(new Edition(counter.incrementAndGet(),"Mirage"));
        editions.add(new Edition(counter.incrementAndGet(),"4eme Edition"));
        editions.add(new Edition(counter.incrementAndGet(),"7eme Edition"));*/
        
        // Get List Edition from DB
    	Session session = HibernateUtil.currentSession();
    	Query<Edition> query = session.createQuery("from Edition");
    	List<Edition> editions = query.getResultList();
    	HibernateUtil.closeSession();
        return editions;
    } 
}
