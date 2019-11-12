package ru.nsu.programming.arithmetic;

import java.util.HashMap;

public class Ln extends Expression {

    private Expression value;

    Ln(Expression value) {
        this.value = value;
    }


    @Override
    public Expression plus(Expression ex) {
        if (ex.isZero()) {
            return this;
        } else if (ex.isLn()) {
            return new Ln(value.multiply(((Ln) ex).getValue()));
        } else if (ex.isSum() || ex.isMultiplication()) {
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
        } else if (ex.isNumber() || ex.isSum() || ex.isMultiplication() || ex.isDivision()) {
            return ex.multiply(this);
        }

        return new Multiplication(this, ex);
    }


    @Override
    protected boolean canBeAdded(Expression ex) {
        return ex.isLn() ||
              (ex.isMultiplication() || ex.isSum()) && ex.canBeAdded(this);
    }

    @Override
    protected boolean canBeMultiplied(Expression ex) {
        return ex.canBeMultiplied(this);
    }

    public static Expression takeLn(Expression exp) {
        return exp.getClass() == Num.class ? new Num(Math.log(((Num) exp).getNumber())) : new Ln(exp);
    }

    @Override
    public Expression calculateValue(HashMap<String, Double> attribution) {
        return Ln.takeLn(value.calculateValue(attribution));
    }


    Expression getValue() {
        return value;
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
    public Expression differentiation(String variable) {
        return (Num.ONE.divide(value)).multiply(value.differentiation(variable));
    }


    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Variable.class &&
               getValue().equals(((Ln) obj).getValue());
    }

    @Override
    public Expression clone() {
        return new Ln(value);
    }

    @Override
    public String toString() {
        return "Ln(" + value + ")";
    }
}
