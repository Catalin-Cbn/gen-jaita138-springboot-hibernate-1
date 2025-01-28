package org.generation.jaita138.springboot_project.db.repo;

import java.util.List;

import org.generation.jaita138.springboot_project.db.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepo extends JpaRepository<Utente, Long> {
    
    List<Utente> findByNomeStartingWith(String carattere);

    List<Utente> findByCreditoGreaterThan(int credito);

    List<Utente> findByNomeIsNullOrCognomeIsNull();

    List<Utente> findByCreditoBetween(int sogliaMinore, int sogliaMaggiore);
}
