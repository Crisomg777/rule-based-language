package main.ast;

public class ConditionNode {
    private boolean isAnd; 
    private ConditionNode left; 
    private ConditionNode right;
    private AtomNode atom;

    public ConditionNode(AtomNode atom) {
        this.isAnd = false;
        this.atom = atom;
    }

    public ConditionNode(ConditionNode left, ConditionNode right) { 
        this.isAnd = true; 
        this.left = left; 
        this.right = right;
    }

    public boolean isAnd() { return isAnd; }
    public ConditionNode getLeft() { return left; }
    public ConditionNode getRight() { return right; }
    public AtomNode getAtom() { return atom; }
}