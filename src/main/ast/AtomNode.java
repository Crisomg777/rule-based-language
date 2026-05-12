package main.ast;

public class AtomNode {
    private boolean isComparison;
    private String variable;
    private String operator; 
    private String value;
    private String fact; 

    public AtomNode(String fact) {
        isComparison = false;
        this.fact = fact; 
    }

    public AtomNode(String variable, String operator, String value) {
        isComparison = true;
        this.variable = variable;
        this.operator = operator;
        this.value = value; 
    }

    public boolean isComparison() { return isComparison; }
    public String getVariable() { return variable; }
    public String getOperator() { return operator; }
    public String getValue() { return value; }
    public String getFact() { return fact; }
}