package org.generation.jaita138.springboot_project.db.service;

import java.util.List;

import org.generation.jaita138.springboot_project.db.entity.Ruolo;
import org.generation.jaita138.springboot_project.db.repo.RuoloRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuoloService {

    @Autowired
    RuoloRepo ruoloRepo;

        public void save(Ruolo ruolo) {

        ruoloRepo.save(ruolo);
    }

    public void delete(Ruolo ruolo) {

        ruoloRepo.delete(ruolo);
    }

    public List<Ruolo> findAll() {

        return ruoloRepo.findAll();
    }

    public Ruolo findById(Long id) {

        return ruoloRepo.findById(id).orElse(null);
    }

}
