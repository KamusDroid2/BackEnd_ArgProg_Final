package com.kamus2.amarok.Controller;

import com.kamus2.amarok.Entity.Persona;
import com.kamus2.amarok.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://frontamarok.web.app/")
public class PersonaController {
    @Autowired IPersonaService ipersonaService;
    
    @GetMapping("/personas/bring")
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
    }
}
