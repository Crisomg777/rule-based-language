package main.lexer;

public class Token {
    public final TokenType type;
    public final String value;
    public final int line; 

    //constructor
    public Token(TokenType type, String value, int line) {
        this.type = type;
        this.value = value;
        this.line = line; 
    }
    
    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "[" + type + ": \"" + value + "\" (line " + line + ")]";
    }
}