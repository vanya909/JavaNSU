package ru.nsu.programming.arithmetic;

import java.util.HashMap;

public class Division extends Expression {
    private Expression firstOperand;
    private Expression secondOperand;

    Division(Expression firstOperand, Expression secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }


    @Override
    public Expression plus(Expression ex) {
        if (ex.isZero()) {
            return this;
        } else if (ex.isDivision() && secondOperand.equals(ex.getSecondOperand())) {
            return (firstOperand.plus(ex.getFirstOperand())).divide(secondOperand);
        } else if (ex.isSum() || ex.isMultiplication()) {
            return ex.plus(this);
        }

        return new Sum(this, ex);
    }

    @Override
    public Expression multiply(Expression ex) {
        if (ex.isDivision()) {
            return firstOperand.multiply(ex.getFirstOperand()).divide(secondOperand.multiply(ex.getSecondOperand()));
        } else if (ex.isSum() || ex.isMultiplication()) {
            return ex.multiply(this);
        }

        return ex.multiply(firstOperand).divide(secondOperand);
    }


    @Override
    public Expression differentiation(String variable) {
        Expression first = firstOperand.differentiation(variable).multiply(secondOperand);
        Expression second = firstOperand.multiply(secondOperand.differentiation(variable));

        return first.plus(new Num(-1).multiply(second)).divide(secondOperand.pow(new Num(2)));
    }

    @Override
    public Expression calculateValue(HashMap<String, Double> attribution) {
        return firstOperand.calculateValue(attribution).divide(secondOperand.calculateValue(attribution));
    }


    //------------------------------------------------------------------------------------------------------------------
    // Optimization:
    //------------------------------------------------------------------------------------------------------------------

    @Override
    protected boolean canBeAdded(Expression ex) {
        return ex.isDivision() && secondOperand.equals(ex.getSecondOperand()) ||
                (ex.isMultiplication() || ex.isSum()) && ex.canBeAdded(this);
    }

    @Override
    protected boolean canBeMultiplied(Expression ex) {
        Expression commonMultiplier = this.commonMultiplier(ex);
        return firstOperand.canBeMultiplied(ex) || Num.ONE.divide(secondOperand).canBeMultiplied(ex);
    }


    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Division.class &&
                getFirstOperand().equals(((Division) obj).getFirstOperand()) &&
                getSecondOperand().equals(((Division) obj).getSecondOperand());
    }

    //------------------------------------------------------------------------------------------------------------------
    // Optimization end.
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public Expression getFirstOperand() {
        return firstOperand;
    }
    @Override
    public Expression getSecondOperand() {
        return secondOperand;
    }


    @Override
    public Division clone() {
        return new Division(firstOperand.clone(), secondOperand.clone());
    }

    @Override
    public String toString() {
        String first = firstOperand.isAtomic() ? firstOperand.toString() : "(" + firstOperand + ")";
        String second = secondOperand.isAtomic() ? secondOperand.toString() : "(" + secondOperand + ")";

        return "(" + first + " / " + second + ")";
    }
}
