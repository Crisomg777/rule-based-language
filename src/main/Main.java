package main;

import main.lexer.Lexer;
import main.parser.Parser;
import main.analysis.StaticAnalyzer;
import main.ast.ProgramNode;
import main.interpreter.Environment;
import main.interpreter.Interpreter;

import java.nio.file.Files;
import java.util.List;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws Exception {
        Environment env = new Environment();

        try {
            String contenido = Files.readString(Paths.get("src/test/caso1_rules.txt"));

            loadState("src/test/caso1_state.txt", env);

            Lexer lexer = new Lexer(contenido);
            Parser parser = new Parser(lexer.tokenize());
            ProgramNode program = parser.parseProgram();

            Interpreter interpreter = new Interpreter(env);
            interpreter.evaluate(program);

            TreeSet<String> facts = new TreeSet<>(env.getFacts());

            if (facts.isEmpty()) {
                System.out.println("(no output)");
            } else {
                for (String fact : facts) {
                    System.out.println(fact);
                }
            }

            StaticAnalyzer analyzer = new StaticAnalyzer(program.getRules(), env);
            for (String msg : analyzer.analyze()) {
                System.out.println(msg);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadState(String path, Environment env) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));

        for (String line : lines) {
            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }

            if (line.contains("=")) {
                String[] parts = line.split("=");
                String name = parts[0].trim();
                double value = Double.parseDouble(parts[1].trim());
                env.setVariable(name, value);
            } else {
                env.activateFact(line);
            }
        }
    }
}