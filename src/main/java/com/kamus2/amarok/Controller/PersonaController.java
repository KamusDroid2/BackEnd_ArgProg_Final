package com.kamus2.amarok.Controller;

import com.kamus2.amarok.Dto.dtoPersona;
import com.kamus2.amarok.Entity.Persona;
import com.kamus2.amarok.Security.Controller.Mensaje;
import com.kamus2.amarok.Service.ImpPersonaService;
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
@CrossOrigin(origins = "https://frontamarok2.web.app")
@RequestMapping("/personas")
public class PersonaController {
    @Autowired 
    ImpPersonaService ipersonaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = ipersonaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id")int id){
        if(!ipersonaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = ipersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!ipersonaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        ipersonaService.delete(id);
        return new ResponseEntity(new Mensaje("Persona Eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona){
        if(StringUtils.isBlank(dtopersona.getName())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(ipersonaService.existsByName(dtopersona.getName())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = new Persona(
                dtopersona.getName(), dtopersona.getDescription()
            );
        ipersonaService.save(persona);
        return new ResponseEntity(new Mensaje("Usuario creado"), HttpStatus.OK);
                
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona){
        if(!ipersonaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(ipersonaService.existsByName(dtopersona.getName()) && ipersonaService.getByName(dtopersona.getName()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtopersona.getName())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = ipersonaService.getOne(id).get();
        
        persona.setName(dtopersona.getName());
        persona.setSurname(dtopersona.getSurname());
        persona.setDescription(dtopersona.getDescription());
        persona.setImg(dtopersona.getImg());
        
        ipersonaService.save(persona);
        
        return new ResponseEntity(new Mensaje("Usuario actualizado"), HttpStatus.OK);
    }
    
    /*@GetMapping("/personas/bring")
    public List<Persona> getPersona(){
        return ipersonaService.getPersona();
    }
    @PreAuthorize("hasRole('ADMIN')")    
    @PostMapping("/personas/create")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "Person was created correctly";
    }

    @PreAuthorize("hasRole('ADMIN')")    
    @DeleteMapping("/personas/delete/{id}")
    public String deletePersona(@PathVariable Long id){
        ipersonaService.deletePersona(id);
        return "Person was delete correctly eaeapepé";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/personas/edit/{id}")
    public Persona editPersona(@PathVariable Long id,
                                @RequestParam("name") String newName,
                                @RequestParam("surname") String newSurname,
                                @RequestParam("img") String newImg){
        Persona persona = ipersonaService.findPersona(id);
        
        persona.setName(newName);
        persona.setSurname(newSurname);
        persona.setImg(newImg);
        
        ipersonaService.savePersona(persona);
        return persona;
    }
    @GetMapping("/personas/bring/perfil")
    public Persona findPersona() {
        return ipersonaService.findPersona((long)1);
    }*/
}