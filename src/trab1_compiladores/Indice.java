/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab1_compiladores;

import java.util.ArrayList;

/**
 *
 * @author isadora.faria
 */
public class Indice extends Token{
    private ArrayList<Matriz> linhaOcor;
    private int frequencia;
    private char primeiraLetra;

    /**
     * @return the frequencia
     */
    public int getFrequencia() {
        return frequencia;
    }

    /**
     * @param frequencia the frequencia to set
     */
    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    /**
     * @return the primeiraLetra
     */
    public char getPrimeiraLetra() {
        return Character.toUpperCase(primeiraLetra);
    }

    /**
     * @param primeiraLetra the primeiraLetra to set
     */
    public void setPrimeiraLetra(char primeiraLetra) {
        this.primeiraLetra = primeiraLetra;
    }

    /**
     * @return the linhaOcor
     */
    public ArrayList<Matriz> getLinhaOcor() {
        return linhaOcor;
    }

    /**
     * @param linhaOcor the linhaOcor to set
     */
    public void setLinhaOcor(ArrayList<Matriz> linhaOcor) {
        this.linhaOcor = linhaOcor;
    }
    
    
}
