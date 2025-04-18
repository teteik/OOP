package Calculator.src.main.java.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorStack {
    private List<String> stack;
    private Map<String, Double> map;
    private int head;

    public CalculatorStack() {
        this.head = -1;
        stack = new ArrayList<>();
        map = new HashMap<>();
    }

    public void push(String line) {
        this.head++;
        this.stack.add(line);
    }

    public void define(List<String> line) {
        map.put(line.get(1), Double.valueOf(line.get(2)));
    }

    public double pop() {
        if (head == -1) {
            throw new RuntimeException("Stack is empty");
        }
        this.head--;
        if (map.containsKey(stack.get(head + 1))) {
            return map.remove(stack.remove(head + 1));
        }
        return Double.valueOf(stack.remove(head + 1));
    }

    public void print() {
        if (stack.size() == 0) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println(stack.get(head));
    }
}
