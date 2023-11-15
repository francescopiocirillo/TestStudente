package it.unisa.diem.oop.report;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import it.unisa.diem.oop.persone.Studente;

public class TestMap {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        AnagraficaStudente a = new AnagraficaStudente("OOP23_1");
        RegistroPresenza rp = new RegistroPresenza("OOP23_1", a);
        /*
        a.aggiungi(new Studente("Mario", "Rossi", "MRS001", "061270001"));
        a.aggiungi(new Studente("Mario", "Rossi", "MRS001", "061270002"));
        a.aggiungi(new Studente("Luigi", "Rossi", "MRS002", "061270003"));
        a.aggiungi(new Studente("Luigi", "De Maio", "MRS002", "061270004"));
        */
        rp.rileva(new Studente("Mario", "Rossi", "MRS001", "061270001"), LocalDate.now());
        rp.rileva(new Studente("Davide", "Rossi", "DRS001", "061270002"), LocalDate.now());
        rp.rileva(new Studente("Davide", "Allegri", "DAS001", "061270003"), LocalDate.now());
        rp.rileva(new Studente("Davide", "Rossi", "DRS001", "061270002"), LocalDate.of(2023, 11, 3));
        rp.rileva(new Studente("Davide", "Allegri", "DAS001", "061270003"), LocalDate.of(2023, 11, 3));
        rp.stampaPresenze("061270001");
        System.out.println();
        rp.stampaPresenze("061270002");
        System.out.println();
        rp.stampaPresenze("061270003");
        System.out.println();

        rp.stampaPresenze(LocalDate.now());
        System.out.println();
        rp.stampaPresenze(LocalDate.of(2023, 11, 3));

        System.out.println(rp);

        //a.scriviDOS();

        //System.out.println(AnagraficaStudente.leggiDIS("OOP23"));

        //rp.scriviDOS();

        //RegistroPresenza rp1 = RegistroPresenza.leggiDIS("OOP23_1");
        //System.out.println(rp1);
        //System.out.println(rp1.getAnagrafica());

        /*
        rp.scriviOBJ();
        RegistroPresenza rpp = RegistroPresenza.leggiOBJ("OOP23_1");
        System.out.println(rpp);
        System.out.println(rpp.getAnagrafica());
        */

        //a.scriviCSV();
        //AnagraficaStudente aa = AnagraficaStudente.leggiCSV("OOP23_1");
        //System.out.println(aa);

        //AnagraficaStudente aa = AnagraficaStudente.leggiCSVs("OOP23_1");
        //System.out.println(aa);

        System.out.println("cocoooo");
        //rp.scriviCSV();
        RegistroPresenza rpp = RegistroPresenza.leggiCSV("OOP23_1");
        System.out.println(rpp);
        System.out.println(rpp.getAnagrafica());
    }
}
