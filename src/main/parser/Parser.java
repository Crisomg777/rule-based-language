package main.parser;

import main.lexer.Token;
import main.lexer.TokenType;
import main.ast.ProgramNode;
import main.ast.RuleNode;
import main.ast.ConditionNode;
import main.ast.AtomNode;
import main.ast.ActionNode;
import java.util.List;
import java.util.ArrayList;

public class Parser {
    private List<Token> tokens;
    private int pos;

    public Parser(List<Token> tokens) {
    this.tokens = tokens;
    this.pos = 0;
    }

    public Token peek() {
        return tokens.get(pos);
    }

    public Token consume(TokenType expected) {
        Token token = tokens.get(pos);
        if (token.getType() != expected) {
        throw new RuntimeException("Se esperaba " + expected + " pero llegó " + token.getType()); 
        }
        pos++;
        return token;
    }

    public ProgramNode parseProgram() {
        List<RuleNode> rules = new ArrayList<>();
        while (peek().getType() != TokenType.EOF){
            RuleNode resultado = parseRule();
            rules.add(resultado);
        }
        return new ProgramNode(rules);
    }

    public RuleNode parseRule() {
        consume(TokenType.RULE);
        Token name = consume(TokenType.ID);
        consume(TokenType.COLON);
        consume(TokenType.IF);
        ConditionNode condition = parseCondition();
        consume(TokenType.THEN);
        ActionNode action = parseAction();
        return new RuleNode(name.getValue(), condition, action);
    }
    
    public ConditionNode parseCondition() {
    ConditionNode left = new ConditionNode(parseAtom()); // ya envuelto    
        if (peek().getType() == TokenType.AND) {
        consume(TokenType.AND);
        ConditionNode right = parseCondition();  // ← recursividad
        return new ConditionNode(left, right);  // 
    } else {
        return left; // hecho
        }   
    }

    public AtomNode parseAtom() {
        Token token = consume(TokenType.ID);

        if (peek().getType() == TokenType.EQ || 
            peek().getType() == TokenType.LT || 
            peek().getType() == TokenType.GT) {

            Token operator = consume(peek().getType());
            Token value = consume(TokenType.VALUE);
            return new AtomNode(token.getValue(), operator.getValue(), value.getValue());
        } else {
            return new AtomNode(token.getValue());
        }
    }

    public ActionNode parseAction() {
        Token token = consume(TokenType.ID);
        return new ActionNode(token.getValue());
    }
}

