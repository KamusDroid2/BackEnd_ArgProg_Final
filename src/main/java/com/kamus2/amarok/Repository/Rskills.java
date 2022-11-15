package com.kamus2.amarok.Repository;

import com.kamus2.amarok.Entity.skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Rskills extends JpaRepository<skills, Integer>{
    Optional<skills> findByName(String name);
    public boolean existsByName(String name);
}
