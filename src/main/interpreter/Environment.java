package main.interpreter;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class Environment {
    private Map<String, Double> variables = new HashMap<>();  // {"temp": 35.0, "humidity": 80.0}
    private Set<String> facts = new HashSet<>();               // {"alert", "fan_on"}

    public Environment() {}

    public void setVariable(String name, double value) {
        variables.put(name,value);
    }   

    public double getVariable(String name) {
        if (!variables.containsKey(name)) {
            throw new RuntimeException("Variable no definida: " + name);
        }
        double variable = variables.get(name);
        return variable; 
    }         

    public boolean hasVariable(String name) {
        return variables.containsKey(name);
    }            

    public void activateFact(String name) {      
        facts.add(name);
    }               

    public boolean isFact(String name) {               
        return facts.contains(name);
    }   

    public Set<String> getFacts() {
        return facts;
    }            
}
