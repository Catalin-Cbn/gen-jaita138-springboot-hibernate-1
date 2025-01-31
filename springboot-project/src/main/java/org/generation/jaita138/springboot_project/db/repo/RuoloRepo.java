package org.generation.jaita138.springboot_project.db.repo;

import org.generation.jaita138.springboot_project.db.entity.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuoloRepo extends JpaRepository<Ruolo, Long> {
    
}
