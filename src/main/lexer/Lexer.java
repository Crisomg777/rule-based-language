package main.lexer;

import java.util.List;
import java.util.ArrayList;

public class Lexer {
    private String input;
    private int pos;
    private int line;

    public Lexer(String input){
        this.input = input;
        pos = 0;
        line = 1;
    }

    public List<Token> tokenize() {
    List<Token> tokens = new ArrayList<>();

    while (peek() != '\0') {
        skipWhitespace();
        if (peek() == '\0') break;

        if (Character.isLowerCase(peek())) {
            tokens.add(readId());
        } else if (peek() == 'A') {
            tokens.add(readAND());
        } else if (Character.isDigit(peek())) {
            tokens.add(readValue());
        } else if (peek() == '>' || peek() == '<' || peek() == '=' || peek() == ':') {
            tokens.add(readSymbol());
        } else {
            throw new RuntimeException("Error léxico en línea " + line + 
            ": carácter inesperado '" + peek());
        }
    }

    tokens.add(new Token(TokenType.EOF, "", line));
    return tokens;
}
    

    public char peek() {           // Muestrsa caracter en posicion sin avanzar
        if (pos >= input.length()) {
            return '\0';
        } else {
            return input.charAt(pos); 
        }
    }

    public char advance() {       // Muestra caracter en posicion y avanza uno y si hay salto de linea aumenta line
        char c = input.charAt(pos);
        if (c == '\n'){
            line++;
        }
        pos++;
        return c; 
    }

    public void skipWhitespace(){  // Utiliza peek para ver el caracter actual y si es algo como espacio tab o salto de linea lo ignora y anvaza
        while (peek() == ' ' || peek() == '\n' || peek() == '\t' || peek() == '\r') {
            advance();
        }
    }

    public Token readId() {
        StringBuilder acumulation = new StringBuilder();  // usar StringBuilder
        int startLine = line;

        while (Character.isLowerCase(peek()) || Character.isDigit(peek()) || peek() == '_') {
            acumulation.append(advance());  // acumula Y avanza en una línea
        }

        String word = acumulation.toString();

        switch (word) {
            case "rule": return new Token(TokenType.RULE, word, startLine);
            case "if":   return new Token(TokenType.IF, word, startLine);
            case "then": return new Token(TokenType.THEN, word, startLine);
            default:     return new Token(TokenType.ID, word, startLine);
        }
    }

    public Token readValue(){
        int startLine = line;
        StringBuilder acumulation = new StringBuilder();

        while (Character.isDigit(peek())) {
            acumulation.append(advance());
        }

        String word = acumulation.toString();
    
        return new Token(TokenType.VALUE, word, startLine);
    }

    public Token readAND() {
        int startLine = line;

        if (peek() != 'A') throw new RuntimeException("Error léxico en línea " + startLine);
        advance();
        if (peek() != 'N') throw new RuntimeException("Error léxico en línea " + startLine);
        advance();
        if (peek() != 'D') throw new RuntimeException("Error léxico en línea " + startLine);
        advance();

        return new Token(TokenType.AND, "AND", startLine);
    }

    public Token readSymbol() {
        int startLine = line;
        char c = advance();
        switch (c) {
            case '>': return new Token(TokenType.GT,    ">", startLine);
            case '<': return new Token(TokenType.LT,    "<", startLine);
            case '=': return new Token(TokenType.EQ,    "=", startLine);
            case ':': return new Token(TokenType.COLON, ":", startLine);
            default:  throw new RuntimeException("Error léxico en línea " + startLine + ": carácter inesperado '" + c + "'");
        }
    }   
}

