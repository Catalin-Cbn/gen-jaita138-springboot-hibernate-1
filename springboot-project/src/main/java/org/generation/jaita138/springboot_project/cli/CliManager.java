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
        List<Utente> prodotti = utenteService.findAll();
        System.out.println("Utenti:");
        System.out.println(prodotti);
        System.out.println("-------------------------------------");
    }

    private void insert() {

        Utente u = new Utente();

        System.out.println("nome:");
        String nome = sc.nextLine();
        u.setNome(nome);

        System.out.println("cognome:");
        String cognome = sc.nextLine();
        u.setCognome(cognome);

        System.out.println("username:");
        String username = sc.nextLine();
        u.setUsername(username);

        System.out.println("password:");
        String password = sc.nextLine();
        u.setPassword(password);

        System.out.println("credito:");
        String strCredito = sc.nextLine();
        int credito = Integer.parseInt(strCredito) * 100;
        u.setCredito(credito);

        System.out.println("ruoli");
        List<Ruolo> ruoli = ruoloService.findAll();
        System.out.println(ruoli);
        for (Ruolo ruolo : ruoli) {
            System.out.println(ruolo.getUtenti());
        }
        System.out.println("ruolo id");
        String strRuoloId = sc.nextLine();
        Long ruoloId = Long.parseLong(strRuoloId);
        Ruolo ruolo = ruoloService.findById(ruoloId);
        u.setRuolo(ruolo);

        utenteService.save(u);
    }

    private void edit() {
        System.out.println("edit id");
        String strId = sc.nextLine();
        Long id = Long.parseLong(strId);
        Utente u = utenteService.findById(id);

        if (u == null) {

            System.out.println("Utente non trovato");
            return;
        }
        System.out.println("Cosa vuoi modificare? (1-6)");
        System.out.println("1) Nome: (" + u.getNome() + ")");
        System.out.println("2) Cognome: (" + u.getCognome() + ")");
        System.out.println("3) Username: (" + u.getUsername() + ")");
        System.out.println("4) Password: (" + u.getPassword() + ")");
        System.out.println("5) Credito: (" + u.getCredito() + ")");
        System.out.println("6) Ruolo: (" + u.getRuolo() + ")");

        int scelta = sc.nextInt();
        sc.nextLine();
        
        boolean opzioneInvalida = false;
        String risposta;
        do {
            opzioneInvalida = false;    
            
            switch (scelta) {
                case 1:
                    System.out.println("Modifica il nome: ");
                    String nome = sc.nextLine();
                    u.setNome(nome);
                    break;
                case 2:
                    System.out.println("Modifica il cognome: ");
                    String cognome = sc.nextLine();
                    u.setCognome(cognome);
                    break;
                case 3:
                    System.out.println("Modifica lo username: ");
                    String username = sc.nextLine();
                    u.setPassword(username);
                    break;
                case 4:
                    System.out.println("Modifica la password: ");
                    String password = sc.nextLine();
                    u.setPassword(password);
                    break;
                case 5:
                    System.out.println("Modifica il credito: ");
                    String strCredito = sc.nextLine();
                    int credito = Integer.parseInt(strCredito) * 100;
                    u.setCredito(credito);
                    break;
                case 6:
                    System.out.println("Seleziona il nuovo ruolo: ");
                    List<Ruolo> ruoli = ruoloService.findAll();
                    System.out.println(ruoli);
                    System.out.println("ruolo id");
                    String strRuoloId = sc.nextLine();
                    Long ruoloId = Long.parseLong(strRuoloId);
                    Ruolo ruolo = ruoloService.findById(ruoloId);
                    u.setRuolo(ruolo);
                    break;
                default:
                    System.out.println("Opzione invalida");
                    opzioneInvalida = true;
                    break;
            }

            System.out.println("Vuoi modificare altro? (s/n)");
            risposta = sc.nextLine();
            

        } while (risposta.equalsIgnoreCase("s") || opzioneInvalida);

        utenteService.save(u);
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
