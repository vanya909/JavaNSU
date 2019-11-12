package ru.nsu.programming.arithmetic;

import java.util.HashMap;

public class Pow extends Expression {
    private Expression firstOperand;
    private Expression secondOperand;

    Pow(Expression firstOperand, Expression secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }


    @Override
    public Expression plus(Expression ex) {
        if (ex.isZero()) {
            return this;
        } if (ex.isPow() && firstOperand.equals(ex.getFirstOperand()) && secondOperand.equals(ex.getSecondOperand())) {
            return Num.TWO.multiply(this);
        } else if (ex.isNumber() || ex.isSum() || ex.isMultiplication()) {
            return ex.plus(this);
        }

        return new Sum(this, ex);
    }

    @Override
    public Expression multiply(Expression ex) {
        if (ex.isZero()) {
            return Num.ZERO;
        } else if (ex.isOne()) {
            return this;
        } else if (ex.isDivision()) {
            return this.multiply(ex.getFirstOperand()).divide(ex.getSecondOperand());
        } else if (ex.isPow() && firstOperand.equals(ex.getFirstOperand())) {
            return firstOperand.pow(secondOperand.plus(ex.getSecondOperand()));
        }  else if (ex.isNumber() || ex.isVariable() || ex.isSum() || ex.isMultiplication() || ex.isDivision()) {
            return ex.multiply(this);
        }

        return new Multiplication(this, ex);
    }

    @Override
    public Expression divide(Expression ex) {
        if (ex.isZero()) {
            throw new ArithmeticException();
        } else if (ex.isOne()) {
            return this;
        } else if (ex.equals(this)) {
            return Num.ONE;
        } else if (ex.isVariable() && firstOperand.equals(ex)) {
            return firstOperand.pow(secondOperand.plus(new Num(-1)));
        }

        Expression commonMultiplier = this.commonMultiplier(ex);
        if (!commonMultiplier.equals(Num.ONE)) {
            return this.divide(commonMultiplier).divide(ex.divide(commonMultiplier));
        } else {
            return new Division(this, ex);
        }
    }

    @Override
    protected boolean canBeAdded(Expression ex) {
        return (ex.isMultiplication() || ex.isSum()) && ex.canBeAdded(this);
    }

    @Override
    protected boolean canBeMultiplied(Expression ex) {
        return ex.canBeMultiplied(this);
    }

    @Override
    public Expression differentiation(String variable) {
        return firstOperand.pow(secondOperand).multiply
              (secondOperand.differentiation(variable).multiply(Ln.takeLn(firstOperand)).plus
              (firstOperand.differentiation(variable).divide(firstOperand).multiply(secondOperand)));
    }

    @Override
    public Expression calculateValue(HashMap<String, Double> attribution) {
        return firstOperand.calculateValue(attribution).pow(secondOperand.calculateValue(attribution));
    }

    @Override
    public Expression getFirstOperand() {
        return firstOperand;
    }
    @Override
    public Expression getSecondOperand() {
        return secondOperand;
    }


    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Pow.class &&
               getFirstOperand().equals(((Pow) obj).getFirstOperand()) &&
               getSecondOperand().equals(((Pow) obj).getSecondOperand());
    }

    @Override
    public Pow clone() {
        return new Pow(firstOperand.clone(), secondOperand.clone());
    }

    @Override
    public String toString() {
        String first = firstOperand.isAtomic() ? firstOperand.toString() : "(" + firstOperand + ")";
        String second = secondOperand.isAtomic() ? secondOperand.toString() : "(" + secondOperand + ")";

        return "(" + first + "^" + second + ")";
    }
}
