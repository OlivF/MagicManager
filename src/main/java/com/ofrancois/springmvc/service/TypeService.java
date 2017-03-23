package com.ofrancois.springmvc.service;

import java.util.List;
import com.ofrancois.springmvc.model.Type;

public interface TypeService {
    
    Type findById(long id);
     
    Type findByName(String name);
     
    void saveType(Type type);
     
    void updateType(Type type);
     
    void deleteTypeById(long id);
 
    List<Type> findAllTypes(); 
     
    void deleteAllTypes();
     
    public boolean isTypeExist(Type type);
     
}