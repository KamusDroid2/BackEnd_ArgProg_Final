package com.kamus2.amarok.Interface;

import com.kamus2.amarok.Entity.Persona;
import java.util.List;

public interface IPersonaService {
    
    public List<Persona> getPersona();
    
    public void savePersona(Persona persona);
    
    public void deletePersona(Long id);
    
    public Persona findPersona(Long id);

   
    
}
