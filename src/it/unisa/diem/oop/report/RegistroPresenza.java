package it.unisa.diem.oop.report;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisa.diem.oop.persone.Studente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;

public class RegistroPresenza implements Serializable {
    
    private String nome;
    private Map<String, Set<LocalDate>> presenze;
    private AnagraficaStudente anagrafica;

    public RegistroPresenza(String nome, AnagraficaStudente anagrafica) {
        this.nome = nome;
        this.anagrafica = anagrafica;
        this.presenze = new HashMap<>();
    }

    public AnagraficaStudente getAnagrafica() {
        return anagrafica;
    }

    public void rileva(Studente s, LocalDate d) {
        if(presenze.containsKey(s.getMatricola())) {
            Set<LocalDate> date = presenze.get(s.getMatricola());
            date.add(d);
        }
        else {
            Set<LocalDate> date = new HashSet<>();
            date.add(d);
            presenze.put(s.getMatricola(), date);
        }
        anagrafica.aggiungi(s);
    }

    public void stampaPresenze(String matricola) {
        System.out.println(anagrafica.cerca(matricola));
        System.out.println(presenze.get(matricola));
    }

    public void stampaPresenze(LocalDate d) {
        System.out.println("Report studenti che hanno rilevato in data: " + d + "\n");

        for( Map.Entry<String, Set<LocalDate>> me : presenze.entrySet() ) {
            if(me.getValue().contains(d)) {
                System.out.println(anagrafica.cerca(me.getKey()));
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer("Regstro presenze " + nome + "\n");

        for(Map.Entry<String, Set<LocalDate>> me : presenze.entrySet()) {
            Studente s = anagrafica.cerca(me.getKey());
            buf.append(s.getNome() + " " + s.getCognome() + " " + s.getMatricola() + " " + me.getValue() + "\n");
        }

        return buf.toString();
    }

    public void scriviDOS() throws IOException {
        anagrafica.scriviDOS();
        try(DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(nome + "_registro.bin")))) {
            for( Map.Entry<String, Set<LocalDate>> me : presenze.entrySet() ) {
                dos.writeUTF(me.getKey());
                Set<LocalDate> date = me.getValue();
                dos.writeInt(date.size());
                for( LocalDate d : date ) {
                    dos.writeUTF(d.toString());
                }
            }
        }
    }

    public void scriviOBJ() throws FileNotFoundException, IOException {

        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nome + "_registro_anagrafica.bin"))) ) {
            oos.writeObject(this); // dov'è implementato? Personalizzazione della serializzazione pagina
        }
    }

    //in un altro metodo usando parse si dovrebbe fare la lettura
    public static RegistroPresenza leggiDIS(String nome) throws FileNotFoundException, IOException {
        AnagraficaStudente a = AnagraficaStudente.leggiDIS(nome);
        RegistroPresenza rp = new RegistroPresenza(nome, a);
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(nome +"_registro.bin")))) {
            
            while(true) {
                String id = dis.readUTF();
                
                int size = dis.readInt();
                
                Studente s = a.cerca(id);
                
                for(int i=0; i<size; i++) {
                    rp.rileva(s, LocalDate.parse(dis.readUTF()));
                }
            }
            

        } catch (EOFException ex) {
            System.out.println("Caricamento registro completato");
        }
        return rp;
    }

    public static RegistroPresenza leggiOBJ(String nome) throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nome + "_registro_anagrafica.bin")))) {
            RegistroPresenza rp = (RegistroPresenza)ois.readObject();
            return rp;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger( RegistroPresenza.class.getName() ).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void scriviCSV() throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(nome + "_registro.csv")))) {
            pw.println("NOME;COGNOME;CODICEFISCALE;MATRICOLA;DATE;;");
            
            for( Map.Entry<String, Set<LocalDate>> me : presenze.entrySet() ) {
                Studente s = anagrafica.cerca(me.getKey());
                pw.print(s.getNome() + ";" + s.getCognome() + ";" + s.getCodFiscale() + ";" + s.getMatricola());
                for( LocalDate d : me.getValue() ) {
                    pw.print(";" + d.toString());
                }
                pw.println();
            }
        }
    }

    public static RegistroPresenza leggiCSV(String nome) throws FileNotFoundException, IOException {
        AnagraficaStudente a = new AnagraficaStudente(nome);
        RegistroPresenza rp = new RegistroPresenza(nome, a);
        try(BufferedReader bfr = new BufferedReader(new FileReader(nome + "_registro.csv"))) {
            if( bfr.readLine() == null )
                return rp;
            String line;
            while( (line = bfr.readLine()) != null ) {
                String fields[] = line.split(";");
                Studente s = new Studente(fields[0], fields[1], fields[2], fields[3]);
                for(int i = 4; i < fields.length; i++) {
                    rp.rileva(s, LocalDate.parse(fields[i]));
                }
            }
        }
        return rp;
    }
}
