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
public class LaboratorioVuotoException extends SpazioException {

    /**
     * Creates a new instance of <code>LaboratorioVuotoException</code> without
     * detail message.
     */
    public LaboratorioVuotoException() {
    }

    /**
     * Constructs an instance of <code>LaboratorioVuotoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public LaboratorioVuotoException(String msg) {
        super(msg);
    }
}
