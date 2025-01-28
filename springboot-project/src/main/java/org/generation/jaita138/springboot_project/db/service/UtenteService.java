package org.generation.jaita138.springboot_project.db.service;

import java.util.List;

import org.generation.jaita138.springboot_project.db.entity.Utente;
import org.generation.jaita138.springboot_project.db.repo.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    @Autowired
    UtenteRepo utenteRepo;

    public void save(Utente utente) {

        utenteRepo.save(utente);
    }

    public void delete(Utente utente) {

        utenteRepo.delete(utente);
    }

    public List<Utente> findAll() {

        return utenteRepo.findAll();
    }

    public Utente findById(Long id) {

        return utenteRepo.findById(id).orElse(null);
    }

    public List<Utente> inizialeUtenti (String carattere) {

        return utenteRepo.findByNomeStartingWith(carattere);
    }

    public List<Utente> sogliaCredito (int credito) {

        return utenteRepo.findByCreditoGreaterThan(credito);
    }

    public List<Utente> nomeCognomeNull () {

        return utenteRepo.findByNomeIsNullOrCognomeIsNull();
    }

    public List<Utente> creditoTra0E10 () {

        return utenteRepo.findByCreditoBetween(0, 1000);
    }
}
