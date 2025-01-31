package org.generation.jaita138.springboot_project;

import org.generation.jaita138.springboot_project.cli.CliManager;
import org.generation.jaita138.springboot_project.db.service.RuoloService;
import org.generation.jaita138.springboot_project.db.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootProjectApplication implements CommandLineRunner{

	@Autowired
	UtenteService utenteService;

	@Autowired
	RuoloService ruoloService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProjectApplication.class, args);

		
	}

	@Override
	public void run(String... args) throws Exception {
		new CliManager(utenteService, ruoloService);
	}

}
