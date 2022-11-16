
package com.kamus2.amarok.Repository;

import com.kamus2.amarok.Entity.Projects;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RProjects extends JpaRepository<Projects, Integer>{
    public Optional<Projects> findByProject(String project);
    public boolean existsByProject (String project);
}
