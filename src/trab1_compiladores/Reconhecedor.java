/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab1_compiladores;

import java.util.ArrayList;

/**
 *
 * @author PURGUISA
 */
public class Reconhecedor {

    private int qtdeCharCe;
    private int qtdeCharSe;
    private int qtdePalavras;
    private int qtdeId;
    private int qtdeNum;
    private int qtdeOp;
    private int qtdeLinhas;
    private String lexema;

    public Reconhecedor() {
        this.qtdeCharCe = 0;
        this.qtdeCharSe = 0;
        this.qtdeId = 0;
        this.qtdeLinhas = 0;
        this.qtdeNum = 0;
        this.qtdeOp = 0;
        this.qtdePalavras = 0;
    }

    public int contarLinhas(String entrada) {
        String[] parts = entrada.split("\n");
        int tam = parts.length;
        return tam;
    }

    public int contarCaracteresCEspaco(String entrada) {
        int tam = this.contarLinhas(entrada);
        return entrada.length() - tam; //desconsidera último espaço vazio
    }

    public int contarCaracteresSEspaco(String entrada) {
        int tam = this.contarLinhas(entrada);
        return entrada.replace(" ", "").length() - tam;
    }

    public int contarPalavras(String entrada) {
        entrada = entrada.replaceAll("\n", " ");
        String[] palavra = entrada.split(" ");
        int cont = 0;
        for (int i = 0; i < palavra.length; i++) {
            if (palavra[i].equals("") || palavra[i].equals(" ")) {

            } else {
                char charP = palavra[i].charAt(0);
                int valorAscii = charP;
                if (!(valorAscii == 42) && !(valorAscii == 43) && !(valorAscii == 45)
                        && !(valorAscii == 47) && !(valorAscii == 60) && !(valorAscii == 61) && !(valorAscii == 62)
                        && !(valorAscii >= 48 && valorAscii <= 57)) {
                    cont++;
                }
            }
        }
        return cont;
    }

    public int contarOperadores(String entrada) {
        int cont = 0;
        for (int i = 0; i < entrada.length(); i++) {
            int valorAscii = entrada.charAt(i);

            //42 = *
            //43 = +
            //45 = -
            //47 = /
            //60 = <
            //61 = =
            //62 = >
            if (valorAscii == 42 || valorAscii == 43 || valorAscii == 45
                    || valorAscii == 47 || valorAscii == 60 || valorAscii == 61 || valorAscii == 62) {
                cont++;

            }
        }
        return cont;
    }

    public ArrayList<Token> rotinaNumero(String entrada) {
        ArrayList<Token> tokens = new ArrayList<Token>();
        int ultPos = 0;
        boolean achouNum = false;
        for (int i = 0; i < entrada.length(); i++) {
            this.lexema = "";
            if (i == 0 || i > ultPos) {
                Token token = new Token();
                int valorAscii = entrada.charAt(i);
                if (ultPos > 0) {
                    valorAscii = entrada.charAt(ultPos + 1);
                }
                if (valorAscii >= 48 && valorAscii <= 57) { //se for dígito
                    this.lexema += entrada.charAt(i);
                    ultPos = i;
                    boolean prossegue = false;
                    for (int j = i + 1; j < entrada.length(); j++) {
                        int valorAscii2 = entrada.charAt(j);
                        if (valorAscii2 >= 48 && valorAscii2 <= 57 && !prossegue) {
                            this.lexema += entrada.charAt(j);
                            ultPos = j;
                        } else {
                            prossegue = true;
                        }
                    }
                    achouNum = true;
                    token.setClasse("cInt");
                } else {
                    ultPos += 1;
                }
                if (achouNum) {
                    int valorAscii3 = entrada.charAt(ultPos + 1);
                    if (valorAscii3 == 46) { //ponto
                        this.lexema += entrada.charAt(ultPos + 1);
                        boolean prosseguir = false;
                        for (int j = ultPos + 2; j < entrada.length(); j++) {
                            if (entrada.charAt(j) >= 48 && entrada.charAt(j) <= 57 && !prosseguir) {
                                this.lexema += entrada.charAt(j);
                                ultPos = j;
                            } else {
                                prosseguir = true;
                            }
                        }
                        token.setClasse("cReal");
                    }
                    achouNum = false;
                }
                if (this.lexema != "") {
                    token.setValor(this.lexema);
                    tokens.add(token);
                }
            }

        }
        return tokens;
    }

    public ArrayList<Token> rotinaIdentificador(String entrada) {
        ArrayList<Token> tokens = new ArrayList<Token>();
        int ultPos = 0;
        for (int i = 0; i < entrada.length(); i++) {
            this.lexema = "";
            if (i == 0 || i > ultPos) {
                Token token = new Token();
                int valorAscii = entrada.charAt(i);
                if (ultPos > 0) {
                    valorAscii = entrada.charAt(ultPos + 1);
                }
                if ((valorAscii >= 65 && valorAscii <= 90) || (valorAscii >= 97 && valorAscii <= 122)) { //se for letra
                    this.lexema += entrada.charAt(i);
                    ultPos = i;
                    boolean prossegue = false;
                    boolean existe = false;
                    for (int j = i + 1; j < entrada.length(); j++) {
                        int valorAscii2 = entrada.charAt(j);
                        if (((valorAscii2 >= 65 && valorAscii2 <= 90) || (valorAscii2 >= 97 && valorAscii2 <= 122) || (valorAscii2 >= 48 && valorAscii2 <= 57)) && !prossegue) {
                            this.lexema += entrada.charAt(j);
                            ultPos = j;
                            existe = true;
                        } else {
                            prossegue = true;
                        }
                    }
                    if (!existe) {
                        this.lexema = "";
                    }
                    token.setClasse("cId");
                } else {
                    ultPos += 1;
                }
                if (this.lexema != "") {
                    token.setValor(this.lexema);
                    tokens.add(token);
                    //System.out.println("Valor: "+token.getValor()+"\nClasse: "+token.getClasse());
                }
            }

        }
        return tokens;
    }

    public ArrayList<Indice> montaIndice(ArrayList<Token> identificadores, String entrada) {
        String[] palavra = entrada.split("\n");

        ArrayList<Indice> Indices = new ArrayList<Indice>();

        for (int i = 0; i < palavra.length; i++) {
            String[] arrPal = palavra[i].split(" ");
            for (int j = 0; j < arrPal.length; j++) {
                String txt = arrPal[j].replaceAll("[^A-Z^a-z^\\s]", "");
                if (!txt.equals("")) {
                    if (Indices.size() > 0) {
                        boolean existe = false;
                        for (Indice ind : Indices) {
                            if (ind.getValor().equalsIgnoreCase(txt)) {
                                existe = true;
                                ind.setFrequencia(ind.getFrequencia() + 1);
                                ArrayList<Matriz> linhaOcor = ind.getLinhaOcor();
                                Matriz ocor = linhaOcor.get(linhaOcor.size() - 1);
                                if (ocor.getLinha() == i) {
                                    ocor.setOcorrencia(ocor.getOcorrencia() + 1);
                                    linhaOcor.set(linhaOcor.size() - 1, ocor);
                                    ind.setLinhaOcor(linhaOcor);
                                } else {
                                    Matriz ocorL = new Matriz();
                                    ocorL.setLinha(i);
                                    ocorL.setOcorrencia(1);
                                    linhaOcor.add(ocorL);
                                    ind.setLinhaOcor(linhaOcor);
                                }
                            }
                        }
                        if (!existe) {
                            Indice indice = new Indice();
                            ArrayList<Matriz> linhaOcor = new ArrayList<Matriz>();
                            Matriz ocorL = new Matriz();
                            ocorL.setLinha(i);
                            ocorL.setOcorrencia(1);
                            linhaOcor.add(ocorL);
                            indice.setValor(txt);
                            indice.setFrequencia(1);
                            indice.setPrimeiraLetra(txt.charAt(0));
                            indice.setLinhaOcor(linhaOcor);
                            Indices.add(indice);
                        }
                    } else {
                        Indice indice = new Indice();
                        ArrayList<Matriz> linhaOcor = new ArrayList<Matriz>();
                        Matriz ocorL = new Matriz();
                        ocorL.setLinha(i);
                        ocorL.setOcorrencia(1);
                        linhaOcor.add(ocorL);
                        indice.setValor(txt);
                        indice.setFrequencia(1);
                        indice.setPrimeiraLetra(txt.charAt(0));
                        indice.setLinhaOcor(linhaOcor);
                        Indices.add(indice);
                    }

                }
            }
        }
        return Indices;
    }

    public String limpaEntrada(String entrada) {
        entrada = entrada.replaceAll("(", "");
        entrada = entrada.replaceAll(")", "");
        entrada = entrada.replaceAll(":", "");
        entrada = entrada.replaceAll("=", "");
        entrada = entrada.replaceAll("+", "");
        entrada = entrada.replaceAll("*", "");
        entrada = entrada.replaceAll("-", "");
        entrada = entrada.replaceAll("/", "");
        entrada = entrada.replaceAll("<", "");
        entrada = entrada.replaceAll(">", "");
        //entrada = entrada.replaceAll("[", "");
        //entrada = entrada.replaceAll("]", "");
        //entrada = entrada.replaceAll("{", "");
        //entrada = entrada.replaceAll("}", "");
        System.out.println(entrada);
        return entrada;
    }
}
