/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.oop.persone;

import java.io.Serializable;

/**
 *
 * @author lucagreco
 */
//potevamo mettere Studente Serializable e non persona se persona aveva un costruttore senza parametri
public class Persona implements Comparable<Persona>, Serializable {
    
    private String nome;
    private String cognome;
    private String codFiscale;

    public Persona(String nome, String cognome, String codFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.codFiscale = codFiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodFiscale() {
        return codFiscale;
    }

    public void setCodFiscale(String codFiscale) {
        this.codFiscale = codFiscale;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nCognome: " + cognome + "\nCodice Fiscale:" + codFiscale + '\n';
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + ( this.codFiscale == null ? 0 : this.codFiscale.hashCode() );
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        return this.codFiscale.equals(other.codFiscale);
    }

    @Override
    public int compareTo(Persona o) {
        //è buono fare si che la condizione di uguale per compareTo sia uguale a quella di Equals
        return this.codFiscale.compareTo(o.codFiscale);
    }

}
