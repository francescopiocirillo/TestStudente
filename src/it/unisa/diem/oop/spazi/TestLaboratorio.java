/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.oop.spazi;

import it.unisa.diem.oop.persone.Docente;
import it.unisa.diem.oop.persone.Persona;
import it.unisa.diem.oop.persone.Studente;
import it.unisa.diem.oop.spazi.eccezioni.LaboratorioPienoException;

/**
 *
 * @author lucagreco
 */
public class TestLaboratorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Laboratorio l = new Laboratorio("T25", 3);

        Persona p = new Studente("Mario", "Rossi", "MRS001", "061270001");

        Persona d = new Docente("Giacomo", "Bianchi", "GBC0002", "TSW");

        try {
            l.entra(p);

            l.entra(d);

            l.entra(new Studente("Stefano", "Rossi", "SRS001", "061270002"));

            l.entra(new Studente("Ernesto", "Rossi", "ERS001", "061270003"));

            l.entra(new Studente("Guido", "Rossi", "GRS001", "061270004"));

        } catch (LaboratorioPienoException ex) {
          //  Logger.getLogger(TestLaboratorio.class.getName()).log(Level.SEVERE, null, ex);
          
          System.out.println(ex);
        }

   
        try {
           Docente e = (Docente) l.esce();
            
            System.out.println(e);
            
        } catch (Exception ex) {
           // Logger.getLogger(TestLaboratorio.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println(ex);
        }
        
        finally {
        
         
        
        
        }
          System.out.println("finally"); 
        
        

        

    }
    
}
