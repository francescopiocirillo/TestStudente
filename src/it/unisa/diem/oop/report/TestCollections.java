package it.unisa.diem.oop.report;

import java.util.ArrayList;
import java.util.List;

import it.unisa.diem.oop.persone.Persona;
import it.unisa.diem.oop.persone.Studente;

public class TestCollections {
    public static void main( String[] args ) {
        Anagrafica a = new Anagrafica("OOP23", new CognomeComparator());
        a.aggiungi(new Studente("Mario", "Rossi", "MRS001", "061270001"));
        a.aggiungi(new Studente("Mario", "Rossi", "MRS001", "061270001"));
        a.aggiungi(new Studente("Luigi", "Rossi", "MRS002", "061270001"));
        a.aggiungi(new Studente("Luigi", "De Maio", "MRS002", "061270001"));
        System.out.println(a+"\ncoco\n");

        List<Persona> elenco = new ArrayList<>();
        elenco.add(new Studente("Davide", "Allegri", "ZRS001", "061270002"));
        elenco.add(new Studente("Mario", "Rossi", "MRS001", "061270001"));
        elenco.add(new Studente("Davide", "Rossi", "DRS001", "061270001"));

        elenco.sort(null);
        //questo fa stampare rispetto a Compareto se gli diamo Comparetor = null altrimenti rispetto al Comparetor
        
        System.out.println(elenco);
    }
}

/*comparable è un'interfaccia che obbliga a definire compareTo
 * se invece imponiamo un ordinamento differente lo possiamo fare
 */
