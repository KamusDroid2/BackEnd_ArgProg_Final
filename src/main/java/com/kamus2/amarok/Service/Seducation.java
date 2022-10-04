package com.kamus2.amarok.Service;

import com.kamus2.amarok.Entity.Education;
import com.kamus2.amarok.Repository.REducation;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class Seducation {
    @Autowired
    REducation rEducation;
    
    public List<Education> list(){
        return rEducation.findAll();
    }
    
    public Optional<Education> getOne(int id){
        return rEducation.findById(id);
    }
    
    public Optional<Education> getByNameEd(String nameEd){
        return rEducation.findByNameEd(nameEd);
    }
    
    public void save(Education education){
        rEducation.save(education);
    }
    
    public void delete(int id){
        rEducation.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rEducation.existsById(id);
    }
    
    public boolean existsByNameEd(String nameEd){
        return rEducation.existsByNameEd(nameEd);
    }
}

