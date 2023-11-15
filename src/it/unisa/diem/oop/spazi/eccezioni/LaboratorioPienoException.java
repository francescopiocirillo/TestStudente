/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.oop.spazi.eccezioni;

/**
 *
 * @author lucagreco
 */
public class LaboratorioPienoException extends SpazioException {

    /**
     * Creates a new instance of <code>LaboratorioPienoException</code> without
     * detail message.
     */
    public LaboratorioPienoException() {
    }

    /**
     * Constructs an instance of <code>LaboratorioPienoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public LaboratorioPienoException(String msg) {
        super(msg);
    }
}
