package com.amway.calculator;

public interface Calculator {
    double add(double operand);

    double subtract(double operand);

    double multiply(double operand);

    double divideBy(double operand);

    double exp(double power);

    double undo();

    double redo();

    double getResult();
}
