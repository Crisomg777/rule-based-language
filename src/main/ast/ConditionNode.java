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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ConditionNode other = (ConditionNode) obj;

        if (this.isAnd() != other.isAnd()) {
            return false;
        }

        if (this.isAnd()) {
            return this.getLeft().equals(other.getLeft()) &&
            this.getRight().equals(other.getRight());
        }

        if (this.getAtom() == null && other.getAtom() == null) {
            return true;
        }

        if (this.getAtom() == null || other.getAtom() == null) {
            return false;
        }

        return this.getAtom().equals(other.getAtom());
    }

    public boolean isAnd() { return isAnd; }
    public ConditionNode getLeft() { return left; }
    public ConditionNode getRight() { return right; }
    public AtomNode getAtom() { return atom; }
}