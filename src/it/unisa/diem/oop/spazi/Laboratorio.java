/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.oop.spazi;

import it.unisa.diem.oop.persone.Persona;
import it.unisa.diem.oop.spazi.eccezioni.LaboratorioPienoException;
import it.unisa.diem.oop.spazi.eccezioni.LaboratorioVuotoException;

/**
 *
 * @author lucagreco
 */
public class Laboratorio extends Spazio {
    
    private int testa;
    private int coda;
    private int riemp;
    private Persona persone[];

    public Laboratorio(String nome, int numPosti) {
        
        super(nome, numPosti);
        
        testa = 0;
        coda = 0;
        riemp = 0;
        persone = new Persona[numPosti];
        
        
    }
    
    
    @Override
    public void entra(Persona p) throws LaboratorioPienoException {
        
        if(pieno()) {
        
            throw new LaboratorioPienoException("Laboratorio pieno");
        
        
        }
        
        persone[coda] = p;
        
        riemp++;
        
        coda = (coda + 1) % numPosti;
    
    
    }

    @Override
    public Persona esce() throws LaboratorioVuotoException {
        
        if(vuoto()) {
        
           throw new LaboratorioVuotoException("Il laboratorio è vuoto.");
        
        }
        
        Persona p = persone[testa];
        
        persone[testa] = null;
        
        riemp--;
        
        testa = (testa + 1) % numPosti; 
        
        return p;
        
         }

    @Override
    public boolean vuoto() {
        
        return riemp == 0;
        
        }

    @Override
    public boolean pieno() {
        
        return riemp == numPosti;
    
        
        }

    @Override
    public String toString() {
        
        StringBuffer sb = new StringBuffer(super.toString());
        
        for(int i = 0; i < riemp; i++ ) {
            
            sb.append(persone[(testa + i)%numPosti]);
        
        
        }
        
        
        return sb.toString();
    }
    
    
    
    
    
    
}
