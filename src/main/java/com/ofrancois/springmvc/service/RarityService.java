package com.ofrancois.springmvc.service;

import java.util.List;
import com.ofrancois.springmvc.model.Rarity;

public interface RarityService {
    
    Rarity findById(long id);
     
    Rarity findByName(String name);
     
    void saveRarity(Rarity rarity);
     
    void updateRarity(Rarity rarity);
     
    void deleteRarityById(long id);
 
    List<Rarity> findAllRaritys(); 
     
    void deleteAllRaritys();
     
    public boolean isRarityExist(Rarity rarity);
     
}