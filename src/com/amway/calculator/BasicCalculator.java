package com.amway.calculator;

import java.util.ArrayDeque;
import java.util.Deque;


public class BasicCalculator implements Calculator {
    private double result;
    private Deque<Double> undoHistory;
    private Deque<Double> redoHistory;

    public BasicCalculator(double val) {
        this.result = val;
        undoHistory = new ArrayDeque<>();
        redoHistory = new ArrayDeque<>();
    }
    public double add(double operand) {
        undoHistory.push(result);
        result += operand;
        return result;
    }

    public double subtract(double operand) {
        undoHistory.push(result);
        result -= operand;
        return result;
    }

    public double multiply(double operand) {
        undoHistory.push(result);
        result *= operand;
        return result;
    }

    public double exp(double power) {
        undoHistory.push(result);
        result = Math.pow(result, power);
        return result;
    }

    public double divideBy(double operand) {
        if (operand == 0) {
            throw new ArithmeticException("The divisor can't be 0.");
        }
        undoHistory.push(result);
        result /= operand;
        return result;
    }

    public double undo() {
        if (undoHistory.isEmpty()) {
            throw new UnsupportedOperationException("No operation to undo.");
        }
        redoHistory.push(result);
        result = undoHistory.pop();
        return result;
    }

    public double redo() {
        if (redoHistory.isEmpty()) {
            throw new UnsupportedOperationException("No operation to redo.");
        }
        undoHistory.push(result);
        result = redoHistory.pop();
        return result;
    }

    public double getResult() {
        return result;
    }
}
