package com.kamus2.amarok.Controller;

import com.kamus2.amarok.Dto.dtoSkills;
import com.kamus2.amarok.Entity.skills;
import com.kamus2.amarok.Security.Controller.Mensaje;
import com.kamus2.amarok.Service.Sskills;
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
@RequestMapping("/skills")
public class CSkills {
    @Autowired
    Sskills sskills;
    
    @GetMapping("/lista")
    public ResponseEntity<List<skills>> list(){
        List<skills> list = sskills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<skills> getById(@PathVariable("id")int id){
        if(!sskills.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        skills sKills = sskills.getOne(id).get();
        return new ResponseEntity(sKills, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sskills.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        sskills.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkills dtoskills){
        if(StringUtils.isBlank(dtoskills.getName())){
            return new ResponseEntity(new Mensaje("El Skill es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(sskills.existsByName(dtoskills.getName())){
            return new ResponseEntity(new Mensaje("Ese Skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        skills sKills = new skills(
                dtoskills.getName(), dtoskills.getPorcen()
            );
        sskills.save(sKills);
        return new ResponseEntity(new Mensaje("Skill creado"), HttpStatus.OK);
                
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkills dtoskills){
        if(!sskills.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(sskills.existsByName(dtoskills.getName()) && sskills.getByName(dtoskills.getName()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoskills.getName())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        skills sKills = sskills.getOne(id).get();
        
        sKills.setName(dtoskills.getName());
        sKills.setPorcen(dtoskills.getPorcen());
        
        sskills.save(sKills);
        
        return new ResponseEntity(new Mensaje("Skill actualizado"), HttpStatus.OK);
    }
    
}
