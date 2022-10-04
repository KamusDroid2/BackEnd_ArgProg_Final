/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kamus2.amarok.Security.Repository;

import com.kamus2.amarok.Security.Entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author developer
 */
@Repository
public interface iUserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByNameUser(String nameUser);
    
    boolean existsByNameUser(String nameUser);
    boolean existsByEmail(String email);
    
}
