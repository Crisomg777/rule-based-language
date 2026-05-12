package main;

import main.lexer.Lexer;
import main.lexer.Token;
import main.parser.Parser;
import main.ast.ProgramNode;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        String input = "rule r1 if temp > 30 then alert";

        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        Parser parser = new Parser(tokens);
        ProgramNode ast = parser.parseProgram();

    // Prueba: imprime el nombre de la primera regla
        System.out.println(ast.getRules().get(0).getName()); // → r1
    }
}