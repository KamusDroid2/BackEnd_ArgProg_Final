package com.kamus2.amarok.Controller;

import com.kamus2.amarok.Dto.dtoProjects;
import com.kamus2.amarok.Entity.Projects;
import com.kamus2.amarok.Security.Controller.Mensaje;
import com.kamus2.amarok.Service.SProjects;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "https://frontamarok.web.app")
@RequestMapping("/projects")
public class CProjects {
    @Autowired
    SProjects sProjects;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Projects>> list(){
        List<Projects> list = sProjects.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Projects> getById(@PathVariable("id")int id){
        if(!sProjects.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Projects projects = sProjects.getOne(id).get();
        return new ResponseEntity(projects, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sProjects.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        sProjects.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProjects dtoprojects){
        if(StringUtils.isBlank(dtoprojects.getProject())){
            return new ResponseEntity(new Mensaje("El título es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(sProjects.existsByProject(dtoprojects.getProject())){
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Projects projects = new Projects(
                dtoprojects.getProject(), dtoprojects.getDescription()
            );
        sProjects.save(projects);
        return new ResponseEntity(new Mensaje("Proyecto creado"), HttpStatus.OK);
                
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProjects dtoprojects){
        if(!sProjects.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(sProjects.existsByProject(dtoprojects.getProject()) && sProjects.getByProject(dtoprojects.getProject()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoprojects.getProject())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Projects projects = sProjects.getOne(id).get();
        
        projects.setProject(dtoprojects.getProject());
        projects.setDescription(dtoprojects.getDescription());
        projects.setImg(dtoprojects.getImg());
        
        sProjects.save(projects);
        
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }
}
