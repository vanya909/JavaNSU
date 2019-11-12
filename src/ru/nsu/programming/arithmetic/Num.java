package ru.nsu.programming.arithmetic;

import java.util.HashMap;

public class Num extends Expression {

    public static final Num ZERO = new Num(0);
    public static final Num ONE = new Num(1);
    public static final Num TWO = new Num(2);
    public static final Num TEN = new Num(10);

    private double number;

    Num(double number) {
        this.number = number;
    }


    @Override
    public Expression plus(Expression ex) {
        if (this.isZero()) {
            return ex;
        } else if (ex.isZero()) {
            return this;
        } else if (ex.isNumber()) {
            return new Num(this.asNumber() + ex.asNumber());
        } else if (ex.isSum() || ex.isMultiplication()) {
            return ex.plus(this);
        }

        return new Sum(this, ex);
    }

    @Override
    public Expression multiply(Expression ex) {
        if (this.isZero() || ex.isZero()) {
            return new Num(0);
        } else if (this.isOne()) {
            return ex;
        } else if (ex.isOne()) {
            return this;
        } else if (ex.isNumber()) {
            return new Num(this.asNumber() * ex.asNumber());
        } else if (ex.isLn()) {
            return Ln.takeLn(((Ln) ex).getValue().pow(this));
        } else if (ex.isPow() && this.equals(ex.getFirstOperand())) {
            return this.pow(ex.getSecondOperand().plus(Num.ONE));
        } else if (ex.isSum() || ex.isDivision() || ex.isMultiplication()) {
            return ex.multiply(this);
        }

        return new Multiplication(this, ex);
    }


    @Override
    protected boolean canBeAdded(Expression ex) {
        return ex.isNumber();
    }

    @Override
    protected boolean canBeMultiplied(Expression ex) {
        return ex.isNumber() ||
               ex.isDivision() && ex.canBeMultiplied(this)||
               ex.isPow() && ex.getFirstOperand().equals(this);
    }


    @Override
    public Expression differentiation(String variable) {
        return new Num(0);
    }

    @Override
    public Expression calculateValue(HashMap<String, Double> attribution) {
        return this.clone();
    }

    @Override
    protected Expression getFirstOperand() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Getter undefined for atomic expressions");
    }
    @Override
    protected Expression getSecondOperand() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Getter undefined for atomic expressions");
    }

    public double getNumber() {
        return number;
    }


    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Num.class &&
               asNumber() == ((Num) obj).asNumber();
    }

    @Override
    public Num clone() {
        return new Num(number);
    }

    @Override
    public String toString() {
        return asString(number);
    }
}

