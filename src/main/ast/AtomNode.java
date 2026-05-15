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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {      // Mira si son el mismo objeto en memoria es decir x.equals(x)
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {  // Si no son de la misma clase de objeto de una vez no son iguales
            return false;
        }

        AtomNode other = (AtomNode) obj;

        if (this.isComparison() != other.isComparison()) {
            return false;
        }

        if (!this.isComparison()) {
            return this.getFact().equals(other.getFact());
        }

        return this.getVariable().equals(other.getVariable()) &&
           this.getOperator().equals(other.getOperator()) &&
           this.getValue().equals(other.getValue());
    }

    public boolean isComparison() { return isComparison; }
    public String getVariable() { return variable; }
    public String getOperator() { return operator; }
    public String getValue() { return value; }
    public String getFact() { return fact; }
}