package main.ast;

public class RuleNode {
    private String name; 
    private ConditionNode condition; 
    private ActionNode action; 

    public RuleNode(String name, ConditionNode condition, ActionNode action) {
        this.name = name;
        this.condition = condition;
        this.action = action; 
    }

    public String getName() {
        return name;
    }

    public ConditionNode getCondition() {
        return condition;
    }

    public ActionNode getAction() {
        return action;
    }
}