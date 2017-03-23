package com.ofrancois.springmvc.service;

import java.util.List;
import com.ofrancois.springmvc.model.Edition;

public interface EditionService {
    
    Edition findById(long id);
     
    Edition findByName(String name);
     
    void saveEdition(Edition edition);
     
    void updateEdition(Edition edition);
     
    void deleteEditionById(long id);
 
    List<Edition> findAllEditions(); 
     
    void deleteAllEditions();
     
    public boolean isEditionExist(Edition edition);
     
}