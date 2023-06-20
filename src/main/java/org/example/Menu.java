package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    ObjectMapper mapper = new ObjectMapper();
    IrasimoSkaitymasFailai irasymasskaitymas = new IrasimoSkaitymasFailai(mapper);
    List<Zmogus> zmones = irasymasskaitymas.skaitytiFaila();

    void menuMain(){
        String pasirinkimas;
        do{
            System.out.println(("""
                    |1| -> Ivesti nauja asmeni
                    |2| -> Perziureti zmoniu sarasa
                    |0| -> Iseiti
                    """));
            pasirinkimas = scanner.nextLine();

        }while(!pasirinkimas.equals("0"));
    }
    void menuPasirinkimas(String pasirinkimas){
        switch (pasirinkimas){
            case "1" -> pridetiZmogu();
            case "2" -> atspausdintiSarasas();
            case "0" -> {
                irasymasskaitymas.irasytaIFaila(zmones);
                System.out.println("Baigtas darbas");
            }
            default -> System.out.println("Neteisingas pasirinkimas");
        }
    }
    Zmogus pridetiZmogu(){
        System.out.println("Iveskite varda");
        String vardas = scanner.nextLine();
        System.out.println("Iveskite pavarde");
        String pavarde = scanner.nextLine();
        System.out.println("Iveskite asmens koda");
        String asmensKodas = scanner.nextLine();

        return new Zmogus(vardas, pavarde, asmensKodas);

    }
    void pridetiISarasa(){
        Zmogus zmogus = pridetiZmogu();
        for (Zmogus z : zmones ) {
            if (z.getAsmensKodas().equals(zmogus.getAsmensKodas())){
                System.out.printf("Person with ID: %s exist.", zmogus.getAsmensKodas());
                return;
            }
            zmones.add(zmogus);
            System.out.println("Person was added successfully");
        }
    }

    void atspausdintiSarasas (){
        if (zmones.size()==0){
            System.out.println("List is empty");
        }
        zmones.forEach(System.out::println);
    }
}
