/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author fernanrod
 */
public class main {
    static Estado estadoActual = Estado.A;
    static Stack<Character> bufferTokenActual = new Stack<>();
    public static void main(String[] args){
        /*
        CuadroTexto cuadroTexto = new CuadroTexto();
        cuadroTexto.setVisible(true);
        */
        
        Scanner input = new Scanner(System.in);
        char[] statement = (input.nextLine()+" ").toCharArray();
        input.close();
        Token[] tokens = Tokenizador.tokenizar(statement);
        for (Token token : tokens) {
            System.out.println(token.getTipoToken()+" "+token.getExpresion());
        }
        
    }
}
