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
public class SpazioException extends Exception {

    /**
     * Creates a new instance of <code>SpazioException</code> without detail
     * message.
     */
    public SpazioException() {
    }

    /**
     * Constructs an instance of <code>SpazioException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SpazioException(String msg) {
        super(msg);
    }
}
