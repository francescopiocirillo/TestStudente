/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.oop.spazi;

import it.unisa.diem.oop.persone.Persona;
import it.unisa.diem.oop.spazi.eccezioni.SpazioException;

/**
 *
 * @author lucagreco
 */
public abstract class Spazio {
    
    private String nome;
    public final int numPosti;

    public Spazio(String nome, int numPosti) {
        this.nome = nome;
        
        if(numPosti < 1) throw new IllegalArgumentException("numPosti è un valore positivo.");
        
        
        this.numPosti = numPosti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    public abstract void entra(Persona p) throws SpazioException;
    
    public abstract Persona esce() throws SpazioException;
    
    public abstract boolean vuoto();
    
    public abstract boolean pieno();
    

    @Override
    public String toString() {
        return "Spazio" + "\nNome:" + nome + "\nCapienza=" + numPosti + '\n';
    }
    
    
    
    
    
}
