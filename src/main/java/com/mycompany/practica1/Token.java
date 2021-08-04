package com.mycompany.practica1;

public class Token {
    private TipoToken tipoToken;
    private String expresion;
    
    public Token(TipoToken tipoToken, String expresion){
        this.tipoToken = tipoToken;
        this.expresion = expresion;
    }
    
    public String getExpresion() {
        return expresion;
    }

    public TipoToken getTipoToken() {
        return tipoToken;
    }

    public enum TipoToken{
        ENTERO,
        DECIMAL,
        IDENTIFICADOR,
        SIMBOLO,
        ERROR
    }
}
