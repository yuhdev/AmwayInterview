package com.amway.calculator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainFunction {
    static String prevArg = null;
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("""
                Welcome to the calculator!
                This calculator supports 5 operations: +, -, *, /, ^(exponent),  and you can undo or redo an operation by typing "undo" or "redo".
                You can exit the calculator by typing "exit".""");
        String firstArg = sc.next();
        if ("exit".equals(firstArg)) {
            System.exit(0);
        }
        while (!isValidOperand(firstArg)) {
            System.out.println("Please enter a number.");
            firstArg = sc.next();
        }
        prevArg = firstArg;
        Calculator calculator = new BasicCalculator(Double.parseDouble(firstArg));



        while (true) {
            String arg = getNextArg(sc);

            if (isValidOperator(prevArg)) {
                if (isValidOperand(arg)) {
                    double res = doOperation(calculator, prevArg, Double.parseDouble(arg));
                    System.out.println(res);
                    prevArg = arg;
                } else {
                    System.out.println("Please enter a number.");
                }
            } else if (isValidOperand(prevArg)) {
                if (isUndoOrRedo(arg)) {
                    undoOrRedo(calculator, arg);
                    prevArg = arg;
                } else if (isValidOperator(arg)) {
                    prevArg = arg;
                } else {
                    System.out.println("Please enter an operator or undo/redo operation.");
                }
            } else if (isUndoOrRedo(prevArg)) {
                if (isUndoOrRedo(arg)) {
                    undoOrRedo(calculator, arg);
                    prevArg = arg;
                } else if (isValidOperand(arg)) {
                    System.out.println("Please enter an operator or undo/redo operation.");
                } else if (isValidOperator(arg)) {
                    prevArg = arg;
                }
            } else {
                System.out.println("Invalid input. Please enter a number, \"undo\" or \"redo\" operation, " +
                        "or an operator from [+, -, *, /].");
            }

        }


    }

    static void undoOrRedo(Calculator calculator, String arg) {
        double res;
        if ("undo".equals(arg)) {
            try {
                res = calculator.undo();
                System.out.println(res);
            } catch (UnsupportedOperationException e) {
                System.out.println("No operation to undo.");
            }

        } else {
            try {
                res = calculator.redo();
                System.out.println(res);
            } catch (UnsupportedOperationException e) {
                System.out.println("No operation to redo.");
            }

        }
    }

    static double doOperation(Calculator calculator, String operator, double operand) {
        return switch (operator) {
            case "+" -> calculator.add(operand);
            case "-" -> calculator.subtract(operand);
            case "*" -> calculator.multiply(operand);
            case "/" -> calculator.divideBy(operand);
            case "^" -> calculator.exp(operand);
            default -> throw new UnsupportedOperationException("The operator is not supported.");
        };
    }
    static boolean isUndoOrRedo(String arg) {
        return "undo".equals(arg) || "redo".equals(arg);
    }

    static String getNextArg(Scanner sc) {
        String arg = sc.next();
        if ("exit".equals(arg)) {
            System.exit(0);
        }
        return arg;
    }

    static boolean isValidOperand(String op) {
        try {
            double first = Double.parseDouble(op);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean isValidOperator(String op) {
        Set<String> validOperators = getValidOperators();
        return validOperators.contains(op);

    }

    static Set<String> getValidOperators() {
        return new HashSet<>(Arrays.asList("+", "-", "*", "/", "^"));
    }
}
