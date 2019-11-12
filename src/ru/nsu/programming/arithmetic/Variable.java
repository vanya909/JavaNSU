package ru.nsu.programming.arithmetic;

import java.util.HashMap;

public class Variable extends Expression {

    public static final Variable X = new Variable("x");
    public static final Variable Y = new Variable('y');
    public static final Variable Z = new Variable("z");

    private String variable;

    Variable(String variable) {
        if (variable.length() != 1) {
            throw new IllegalArgumentException();
        }
        this.variable = variable;
    }
    Variable(char variable) {
        this.variable = Character.toString(variable);
    }


    @Override
    public Expression plus(Expression ex) {
        if (ex.isZero()) {
            return this;
        } else if (this.equals(ex)) {
            return Num.TWO.multiply(this);
        } else if (ex.isSum() || ex.isMultiplication()) {
            return ex.plus(this);
        }

        return new Sum(this, ex);
    }

    @Override
    public Expression multiply(Expression ex) {
        if (this.equals(ex)) {
            return this.pow(Num.TWO);
        } else if (ex.isDivision()) {
            return this.multiply(ex.getFirstOperand()).divide(ex.getSecondOperand());
        } else if (ex.isPow() && this.equals(ex.getFirstOperand())) {
            return ex.getFirstOperand().pow(ex.getSecondOperand().plus(Num.ONE));
        } else if (ex.isNumber() || ex.isSum() || ex.isMultiplication()) {
            return ex.multiply(this);
        }

        return new Multiplication(this, ex);
    }

    @Override
    public Expression divide(Expression ex) {
        if (ex.isZero()) {
            throw new ArithmeticException("/ by zero");
        } else if (ex.equals(this)) {
            return Num.ONE;
        } else if (ex.isDivision()) {
            return this.multiply(ex.getSecondOperand().divide(ex.getFirstOperand()));
        } else if (ex.isNumber()) {
            return Num.ONE.divide(ex).multiply(this);
        } else if ((ex.isMultiplication() || ex.isSum()) && this.commonMultiplier(ex).equals(this)) {
            return Num.ONE.divide(ex.divide(this));
        } else if (ex.isPow() && ex.getFirstOperand().equals(this)) {
            return Num.ONE.divide(ex.getFirstOperand().pow(ex.getSecondOperand().plus(new Num(-1))));
        }

        return new Division(this, ex);
    }

    @Override
    protected boolean canBeAdded(Expression ex) {
        return ex.equals(this);
    }

    @Override
    protected boolean canBeMultiplied(Expression ex) {
        Expression commonMultiplier = this.commonMultiplier(ex);
        return this.equals(ex) ||
               ex.isPow() && this.equals(ex.getFirstOperand()) ||
               ex.isDivision() && ex.canBeMultiplied(this) ||
               ex.isDivision() &&
               this.divide(this.commonMultiplier(ex.getSecondOperand())).isNumber() &&
               ex.getSecondOperand().divide(this.commonMultiplier(ex.getSecondOperand())).isNumber() ||
               ex.isLn() && ex.getSecondOperand().canBeAdded(this);
    }


    @Override
    public Expression differentiation(String variable) {
        return (this.variable).equals(variable) ? new Num(1) : new Num(0);
    }

    @Override
    public Expression calculateValue(HashMap<String, Double> attribution) {
        if (attribution.containsKey(this.toString())) {
            return new Num(attribution.get(this.toString()));

        } else {
            return this;
        }
    }

    @Override
    protected Expression getFirstOperand() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Getter undefined for atomic expressions");
    }
    @Override
    protected Expression getSecondOperand() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Getter undefined for atomic expressions");
    }


    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Variable.class &&
               this.toString().equals(obj.toString());
    }

    @Override
    public Variable clone() {
        return new Variable(variable);
    }

    @Override
    public String toString() {
        return variable;
    }
}
