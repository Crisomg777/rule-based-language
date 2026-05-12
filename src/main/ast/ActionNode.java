package main.ast;

public class ActionNode {
    private String name;

    public ActionNode(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}