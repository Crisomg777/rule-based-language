package main.ast;

import java.util.ArrayList;
import java.util.List;

public class ProgramNode {
    private List<RuleNode> rules = new ArrayList<>();

    public ProgramNode(List<RuleNode> rules) {
        this.rules = rules;
    }

    public List<RuleNode> getRules() {
    return rules;
    }
}