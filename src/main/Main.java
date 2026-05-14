package main;

import main.lexer.Lexer;
import main.lexer.Token;
import main.parser.Parser;
import main.ast.ProgramNode;
import main.interpreter.Environment;
import main.interpreter.Interpreter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "src/test/caso1.txt";
        String rulesText = Files.readString(Paths.get(path));

        Lexer lexer = new Lexer(rulesText);
        List<Token> tokens = lexer.tokenize();

        Parser parser = new Parser(tokens);
        ProgramNode program = parser.parseProgram();

        Environment env = new Environment();

        // Estado inicial definido directamente aquí
        env.setVariable("temp", 35);

        Interpreter interpreter = new Interpreter(env);
        Set<String> result = interpreter.evaluate(program);

        Set<String> sorted = new TreeSet<>(result);

        if (sorted.isEmpty()) {
            System.out.print("no output");
        } else {
            for (String fact : sorted) {
                System.out.println(fact);
            }
        }
    }
}