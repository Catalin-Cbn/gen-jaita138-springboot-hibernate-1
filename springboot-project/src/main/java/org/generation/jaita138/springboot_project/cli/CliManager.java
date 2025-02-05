package org.generation.jaita138.springboot_project.cli;

import java.util.List;
import java.util.Scanner;

import org.generation.jaita138.springboot_project.db.entity.Ruolo;
import org.generation.jaita138.springboot_project.db.entity.Utente;
import org.generation.jaita138.springboot_project.db.service.RuoloService;
import org.generation.jaita138.springboot_project.db.service.UtenteService;

public class CliManager {

    private Scanner sc;

    private UtenteService utenteService;
    private RuoloService ruoloService;

    public CliManager(UtenteService utenteService, RuoloService ruoloService) {

        
        this.utenteService = utenteService;
        this.ruoloService = ruoloService;

        sc = new Scanner(System.in);

        printOptions();
    }

    private void printOptions() {

        System.out.println("Operazioni:");
        System.out.println("1. Leggi tutta la tabella");
        System.out.println("2. Inserisci nuovo record");
        System.out.println("3. Modifica record");
        System.out.println("4. Elimina record");
        System.out.println("5. Cerca utenti per iniziale nome");
        System.out.println("6. Cerca per soglia credito");
        System.out.println("7. Cerca utenti con nome o cognome mancanti");
        System.out.println("8. Cerca utenti con credito positivo sotto i 10 Euro compresi");
        System.out.println("9. Esci");
        System.out.println("");

        String strValue = sc.nextLine();
        int value = Integer.parseInt(strValue);

        switch (value) {

            case 1:
                readAll();
                break;
            case 2:
                insert();
                break;
            case 3:
                edit();
                break;
            case 4:
                delete();
                break;
            case 5:
                cercaPerIniziale();
                break;
            case 6:
                cercaPerCredito();
                break;
            case 7:
                cercaPerNUll();
                break;
            case 8:
                cercaPerCreditoDa0a10();
                break;
            case 9:
                return;

            default:
                System.out.println("Operazione non valida");
                break;
        }

        printOptions();
    }

    private void readAll() {
        List<Utente> utenti = utenteService.findAll();
        System.out.println("Utenti:");
        System.out.println(utenti);
        System.out.println("-------------------------------------");
    }

    private void insert() {

        Utente utente = new Utente();

        save(utente);

    }

    private void edit() {
        System.out.println("edit id");
        String strId = sc.nextLine();
        Long id = Long.parseLong(strId);
        Utente utente = utenteService.findById(id);

        if (utente == null) {

            System.out.println("Utente non trovato");
            return;
        }
        
        save(utente);
    }

    private void delete() {

        System.out.println("delete id:");
        String strId = sc.nextLine();
        Long id = Long.parseLong(strId);
        Utente u = utenteService.findById(id);

        if (u != null) {
            utenteService.delete(u);
            System.out.println("Utente " + strId + " eliminato");
        } else
            System.out.println("Utente non trovato");
    }

    private void stampaSeparatore() {
        System.out.println("-----------------------------------------------------");
    }

    private void save(Utente utente) {

        boolean isInsert = (utente.getId() == null);

        System.out.println("Nome" + (isInsert
                ? ""
                : " (" + utente.getNome() + ")"));
        String nome = sc.nextLine();
        utente.setNome(nome);
        
        System.out.println("Cognome" + (isInsert
                ? ""
                : " (" + utente.getCognome() + ")"));
        String cognome = sc.nextLine();
        utente.setCognome(cognome);

        System.out.println("Username" + (isInsert
                ? ""
                : " (" + utente.getUsername() + ")"));
        String username = sc.nextLine();
        utente.setUsername(username);

        System.out.println("Password" + (isInsert
                ? ""
                : " (" + utente.getPassword() + ")"));
        String password = sc.nextLine();
        utente.setPassword(password);

        System.out.println("Credito" + (isInsert
                ? ""
                : " (" + utente.getCredito() + ")"));
        String strCredito = sc.nextLine();
        int credito = Integer.parseInt(strCredito) * 100;
        utente.setCredito(credito);

        //1aN
        List<Ruolo> ruoli = ruoloService.findAll();
        if (ruoli.size() > 0) {

            System.out.println("Ruoli"); 
            ruoli.stream()
            .map(r -> r.getId() + ". " + r.getNome() + " - " + r.getDescrizione()).
            forEach(System.out::println);
            stampaSeparatore();

            String ruoloIDString = (utente.getRuolo() == null) ? "Senza ruolo"
            : "" + utente.getRuolo().getId();
            System.out.println("ruolo id" + (isInsert
            ? ""
            : " (" + ruoloIDString + ")"));
            String stringRuoloId = sc.nextLine();
            Long ruoloId = Long.parseLong(stringRuoloId);
            Ruolo ruolo = ruoloService.findById(ruoloId);

            utente.setRuolo(ruolo);
        } else
            System.out.println("Nessun ruolo disponibile");

    }

    private void cercaPerIniziale() {
        System.out.println("Quale è l'iniziale?");
        String iniziale = sc.nextLine();
        List<Utente> u = utenteService.inizialeUtenti(iniziale);

        System.out.println(u);
    }

    private void cercaPerCredito() {
        System.out.println("Qual'è la soglia di credito?");
        String soglia = sc.nextLine();
        int sogliaEffettiva = Integer.parseInt(soglia) * 100;
        List<Utente> u = utenteService.sogliaCredito(sogliaEffettiva);

        System.out.println(u);
    }

    private void cercaPerNUll() {
        System.out.println("Ecco la lista degli utenti con nome o cognome mancanti: ");

        List<Utente> u = utenteService.nomeCognomeNull();

        System.out.println(u);
    }

    private void cercaPerCreditoDa0a10() {
        System.out.println("Ecco la lista degli utenti con un credito positivo sotto i 10 euro:");

        List<Utente> u = utenteService.creditoTra0E10();

        System.out.println(u);
    }
}
