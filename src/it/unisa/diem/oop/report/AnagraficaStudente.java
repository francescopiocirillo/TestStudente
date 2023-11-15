package it.unisa.diem.oop.report;

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
import java.io.Serializable;
import java.util.*;

public class AnagraficaStudente implements Serializable {
    private String nome;
    private Map<String, Studente> anagrafica;

    public AnagraficaStudente( String nome ) {
        this.nome = nome;
        this.anagrafica = new HashMap<>();
    }

    public void aggiungi(Studente s) {
        anagrafica.putIfAbsent(s.getMatricola(), s);
    }

    public Studente rimuovi(String matricola) {
        return anagrafica.remove(matricola);
    }

    public Studente cerca(String matricola) {
        return anagrafica.get(matricola);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("AnagraficaStudenti nome=" + nome + "\n");
        for( Studente s : anagrafica.values() ) {
            sb.append(s.getNome() + " " + s.getCognome() + " " + s.getMatricola() + "\n");

        }
        return sb.toString();
    }

    public void scriviDOS(/*qua poteva esserci String nomeFile */) throws/*è sottoclassse dell'altra FileNotFoundException,*/ IOException {
        FileOutputStream fos = new FileOutputStream(nome + "_anagrafica.bin");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos);
        //try non usato per gestire eccezioni con catch ma solo per mettere un finally che assicura
        //la chiusura del file, se qualcosa va storto è importante chiuderlo, addirittura potremmo
        //non trovare il contenuto estratto se il file non viene chiuso
        try {
            for(Studente s : anagrafica.values()) {
                dos.writeUTF(s.getNome());
                dos.writeUTF(s.getCognome());
                dos.writeUTF(s.getCodFiscale());
                dos.writeUTF(s.getMatricola());
            }
        }
        finally {
            dos.close();
        }
    }

    public static AnagraficaStudente leggiDIS(String nome) throws FileNotFoundException, IOException {
        AnagraficaStudente a = new AnagraficaStudente(nome);

        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(nome + "_anagrafica.bin")))) {
            
            while(true) {
                String name = dis.readUTF();
                String cognome = dis.readUTF();
                String codFis = dis.readUTF();
                String matricola = dis.readUTF();

                Studente s = new Studente(name, cognome, codFis, matricola);
                a.aggiungi(s);
            }

        } catch (EOFException ex) {
            System.out.println("Caricamento terminato");
        }

        return a;
    }

    public void scriviCSV() throws IOException {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(nome + "_anagrafica.csv"))) {
            bfw.append("NOME;COGNOME;CODICEFISCALE;MATRICOLA\n"); //Writer ci permette append, questa riga serve a far vedere come sono organizzati i dati a chi legge
            
            for( Studente s : anagrafica.values() ) {
                bfw.append(s.getNome() + ";");
                bfw.append(s.getCognome() + ";");
                bfw.append(s.getCodFiscale() + ";");
                bfw.append(s.getMatricola() + "\n");
            }
        
        }
    }

    public static AnagraficaStudente leggiCSV( String nome ) throws IOException {
        AnagraficaStudente a = new AnagraficaStudente(nome);
        try (BufferedReader bfr = new BufferedReader(new FileReader(nome + "_anagrafica.csv"))) {
            if( bfr.readLine() == null )
                return a;
            String line;
            while( (line = bfr.readLine()) != null ) {
                String fields[] = line.split(";");
                Studente s = new Studente(fields[0], fields[1], fields[2], fields[3]);
                a.aggiungi(s);
            }
        }
        return a;
    }

    //ora proviamo una lettura di csv con scanner
    public static AnagraficaStudente leggiCSVs( String nome ) throws FileNotFoundException {
        AnagraficaStudente a = new AnagraficaStudente(nome);
        try (Scanner s = new Scanner(new BufferedReader(new FileReader(nome + "_anagrafica.csv")))) {
            s.useDelimiter("[;\n]");
            if(s.hasNextLine()) // questi metodi vengono dallo scanner
                s.nextLine();
            while(s.hasNext()) {
                String name = s.next();
                String cognome = s.next();
                String codiceFiscale = s.next();
                String matricola = s.next();
                Studente ss = new Studente(name, cognome, codiceFiscale, matricola);
                a.aggiungi(ss);
            }
        }
        return a;
    }
}
