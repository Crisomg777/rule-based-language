package main.interpreter;

import java.util.Set;

import main.ast.AtomNode;
import main.ast.ConditionNode;
import main.ast.ProgramNode;
import main.ast.RuleNode;

public class Interpreter {

    private Environment env;

    public Interpreter(Environment env) {
        this.env = env;
    }

    public Set<String> evaluate(ProgramNode program) {
        boolean changed = true; 

        while (changed) {
            changed = false;
            for (RuleNode rule : program.getRules()) {
                boolean result = evalRule(rule);
                if (result) { changed = true; }
            }
        }
        return env.getFacts();
    }
    
    private boolean evalRule(RuleNode rule)  {
        boolean result = evalCondition(rule.getCondition());
        if (!result) {
            return false;                               // No hay accion si falla condicion 
        } else {
            String action = rule.getAction().getName();    // Si la condicion es verdadera entonces si estaba activada de antemano
            if (env.isFact(action)) {                 // NO pasa nada y no hay cambio
                return false;   
            } else {
                env.activateFact(action);                 // Si no lo estaba se activa y se devuelve un cambio
                return true;
            }
        }
    }

    private boolean evalCondition(ConditionNode condition) {
        if (!condition.isAnd()) {
            return evalAtom(condition.getAtom());
        } else {
            return evalCondition(condition.getLeft()) && evalCondition(condition.getRight()); // Recursion para multiples AND
        }
    }

    private boolean evalAtom(AtomNode atom) {           // Evalua
        if (atom.isComparison()) {                                  // Si es comparacion evalua la comparacion logica (True o False)
            double variable = env.getVariable(atom.getVariable());
            double number = Double.parseDouble(atom.getValue());
            char operator = atom.getOperator().charAt(0);

            switch (operator) {
                case '<':
                    return variable < number;
                case '>':
                    return variable > number;
                case '=':
                    return variable == number;
                default:
                    throw new RuntimeException("Operador no identificado: " + operator);
            }
        } else {                                            // Si es un hecho mira si esta en hechos activos en el enviroment
            return env.isFact(atom.getFact());
        }
    }

}   
