/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kamus2.amarok.Security.Service;

import com.kamus2.amarok.Security.Entity.User;
import com.kamus2.amarok.Security.Repository.iUserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author developer
 */
@Service
@Transactional
public class UserService {
    @Autowired
    iUserRepository iuserRepository;
    
    public Optional<User> getByNameUser(String nameUser){
        return iuserRepository.findByNameUser(nameUser);
    }
    
    public boolean existsByNameUser(String nameUser){
        return iuserRepository.existsByNameUser(nameUser);
    }
    
    public boolean existsByEmail(String email){
        return iuserRepository.existsByEmail(email);
    }
    public void save(User user){
        iuserRepository.save(user);
    }
}
