package com.kamus2.amarok.Service;

import com.kamus2.amarok.Entity.Projects;
import com.kamus2.amarok.Repository.RProjects;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SProjects {
    @Autowired
    RProjects rProjects;
    
    public List<Projects> list(){
        return rProjects.findAll();
    }
    
    public Optional<Projects> getOne(int id){
        return rProjects.findById(id);
    }
    
    public Optional<Projects> getByProject(String project){
        return rProjects.findByProject(project);
    }
    
    public void save(Projects projects){
        rProjects.save(projects);
    }
    
    public void delete(int id){
        rProjects.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rProjects.existsById(id);
    }
    
    public boolean existsByProject(String project){
        return rProjects.existsByProject(project);
    }
}
