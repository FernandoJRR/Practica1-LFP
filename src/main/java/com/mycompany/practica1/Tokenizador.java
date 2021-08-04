package com.mycompany.practica1;

import java.util.Stack;

import com.mycompany.practica1.Token.TipoToken;

import java.util.ArrayList;

public class Tokenizador {
    private static Estado estadoActual = Estado.A;
    private static Stack<Character> bufferTokenActual = new Stack<>();
    private static ArrayList<Token> tokens;
    
    public static Token[] tokenizar(char[] statement) {
        tokens = new ArrayList<>();
        for (char caracterActual : statement) {
            switch (estadoActual) {
                case A:
                    estadoA(caracterActual);
                    break;
                case B:
                    estadoB(caracterActual);
                    break;
                case C:
                    estadoC(caracterActual);
                    break;
                case D:
                    estadoD(caracterActual);
                    break;
                case E:
                    estadoE(caracterActual);
                    break;
                case F:
                    estadoF(caracterActual);
                    break;
                case X:
                    estadoX(caracterActual);
                    break;
            }
        }
        return tokens.toArray(new Token[0]);
    }

    static void estadoA(char caracterActual){
        if (Character.isWhitespace(caracterActual)) {
        }else{
            if (Character.isLetter(caracterActual)) {
                estadoActual = Estado.B;
                bufferTokenActual.push(caracterActual);
            }else if (Character.isDigit(caracterActual)){
                estadoActual = Estado.C;
                bufferTokenActual.push(caracterActual);
            }else if (isSimbolo(caracterActual)) {
                estadoActual = Estado.F;       
                bufferTokenActual.push(caracterActual);
                tokens.add(new Token(TipoToken.SIMBOLO, stackToString(bufferTokenActual)));
                bufferTokenActual.clear();
            }else{
                estadoActual = Estado.X;
                bufferTokenActual.push(caracterActual);
            }
        }
    }   

    static void estadoB(char caracterActual){
        if (Character.isWhitespace(caracterActual)) {
            estadoActual = Estado.A;
            tokens.add(new Token(TipoToken.IDENTIFICADOR, stackToString(bufferTokenActual)));
            bufferTokenActual.clear();
        }else{
            if (Character.isLetter(caracterActual)||Character.isDigit(caracterActual)) {
                bufferTokenActual.push(caracterActual);
            }else if (isSimbolo(caracterActual)) {
                estadoActual = Estado.F;
                tokens.add(new Token(TipoToken.IDENTIFICADOR, stackToString(bufferTokenActual)));
                bufferTokenActual.clear();
                bufferTokenActual.push(caracterActual);
                tokens.add(new Token(TipoToken.SIMBOLO, stackToString(bufferTokenActual)));
                bufferTokenActual.clear();
            }else{
                estadoActual = Estado.X;
                bufferTokenActual.push(caracterActual);
            }
        }
    }

    static void estadoC(char caracterActual){
        if (Character.isWhitespace(caracterActual)) {
            estadoActual = Estado.A;
            tokens.add(new Token(TipoToken.ENTERO, stackToString(bufferTokenActual)));
            bufferTokenActual.clear();
        }else{
            if (Character.isDigit(caracterActual)) {
                bufferTokenActual.push(caracterActual);
            }else if (caracterActual == '.') {
                estadoActual = Estado.D;
                bufferTokenActual.push(caracterActual);
            }else if (isSimbolo(caracterActual)) {
                estadoActual = Estado.F;
                tokens.add(new Token(TipoToken.ENTERO, stackToString(bufferTokenActual)));
                bufferTokenActual.clear();
                bufferTokenActual.push(caracterActual);
                tokens.add(new Token(TipoToken.SIMBOLO, stackToString(bufferTokenActual)));
                bufferTokenActual.clear();
            }else{
                estadoActual = Estado.X;
                bufferTokenActual.push(caracterActual);
            }
        }
    }

    static void estadoD(char caracterActual){
        if (Character.isWhitespace(caracterActual)) {
            estadoActual = Estado.A;
            tokens.add(new Token(TipoToken.ERROR, stackToString(bufferTokenActual)));
            bufferTokenActual.clear();
        }else{
            if (Character.isDigit(caracterActual)) {
                estadoActual = Estado.E;
                bufferTokenActual.push(caracterActual);
            }else{
                estadoActual = Estado.X;
                bufferTokenActual.push(caracterActual);
            }
        }
    }

    static void estadoE(char caracterActual){
        if (Character.isWhitespace(caracterActual)) {
            estadoActual = Estado.A;
            tokens.add(new Token(TipoToken.DECIMAL, stackToString(bufferTokenActual)));
            bufferTokenActual.clear();
        }else{
            if (Character.isDigit(caracterActual)) {
                estadoActual = Estado.E;
                bufferTokenActual.push(caracterActual);
            }else if (isSimbolo(caracterActual)) {
                estadoActual = Estado.F;   
                tokens.add(new Token(TipoToken.DECIMAL, stackToString(bufferTokenActual)));
                bufferTokenActual.clear();
                bufferTokenActual.push(caracterActual);
                tokens.add(new Token(TipoToken.SIMBOLO, stackToString(bufferTokenActual)));
                bufferTokenActual.clear();
            }else{
                estadoActual = Estado.X;
                bufferTokenActual.push(caracterActual);
            }
        }
    }

    static void estadoF(char caracterActual){
        if (Character.isWhitespace(caracterActual)) {
            estadoActual = Estado.A;
        }else{
            if (Character.isDigit(caracterActual)) {
                estadoActual = Estado.C;
                bufferTokenActual.push(caracterActual);
            }else if (isSimbolo(caracterActual)) {
                bufferTokenActual.push(caracterActual);
                tokens.add(new Token(TipoToken.SIMBOLO, stackToString(bufferTokenActual)));
                bufferTokenActual.clear();
            }else{
                estadoActual = Estado.X;
                bufferTokenActual.push(caracterActual);
            }
        }
    }

    static void estadoX(char caracterActual){
        if (Character.isWhitespace(caracterActual)) {
            estadoActual = Estado.A;
            tokens.add(new Token(TipoToken.ERROR, stackToString(bufferTokenActual)));
            bufferTokenActual.clear();
        }else{
            bufferTokenActual.push(caracterActual);
        }
    }

    static boolean isSimbolo(char caracterActual){
        if ((caracterActual=='[')||(caracterActual==']')||(caracterActual=='{')||(caracterActual=='}')||(caracterActual==';')||(caracterActual==',')) {
            return true;
        }else{
            return false;
        }
    }
    
    static String stackToString(Stack<Character> stack){
        String expresion = "";
        for (char caracter : stack) {
            expresion = expresion+caracter;
        }
        return expresion;
    }
}
