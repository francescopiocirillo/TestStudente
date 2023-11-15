package it.unisa.diem.oop.report;
import it.unisa.diem.oop.persone.*;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Anagrafica {
    
    private String nome;
    private Set<Persona> persone;

    public Anagrafica( String nome ) {
        this.nome = nome;
        persone = new TreeSet<Persona>();

    }

    public Anagrafica( String nome, Comparator<Persona> c ) {
        this.nome = nome;
        persone = new TreeSet<Persona>(c);
    }

    public void aggiungi( Persona p ) {
        persone.add(p);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Anagrafica{" + "nome=" + nome + ", persone =\n" + persone + '}';
    }
}
