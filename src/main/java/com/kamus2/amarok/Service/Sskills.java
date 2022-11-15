package com.kamus2.amarok.Service;

import com.kamus2.amarok.Entity.skills;
import com.kamus2.amarok.Repository.Rskills;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class Sskills {
    @Autowired
    Rskills rskills;
    
    public List<skills> list() {
        return rskills.findAll();        
    }
    
    public Optional<skills> getOne(int id){
        return rskills.findById(id);
    }
    
    public Optional<skills> getByName(String name){
        return rskills.findByName(name);
    }
    
    public void save(skills skill){
        rskills.save(skill);
    }
    
    public void delete(int id){
        rskills.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rskills.existsById(id);
    }
    
    public boolean existsByName(String name){
        return rskills.existsByName(name);
    }

    public boolean existsByNameSk(CharSequence nameSk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
