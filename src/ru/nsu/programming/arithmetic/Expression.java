package ru.nsu.programming.arithmetic;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Expression {

    public abstract Expression plus(Expression ex); // Выносит общий множитель за скобки

    public Expression multiply(Expression ex) {
        if (ex.isZero()) {
            return new Num(0);
        } else if (ex.isOne()) {
            return this;
        }

        return new Multiplication(this, ex);
    }

    public Expression divide(Expression ex) {

        if (this.isNumber() && ex.isNumber()) {
            return new Num(this.asNumber() / ex.asNumber());

        } else if (ex.isOne()) {
            return this;

        } else if (this.isZero()) {
            return new Num(0);

        } else if (ex.isZero()) {
            throw new ArithmeticException("/ by zero");
        }

        return new Division(this, ex);
    }

    public Expression pow(Expression ex) {

        if (this.isNumber() && ex.isNumber()) {
            return new Num(Math.pow(this.asNumber(), ex.asNumber()));

        } else if (this.isOne() || ex.isZero()) {
            return new Num(1);

        } else if (ex.isOne()) {
            return this;

        } else if (this.isZero()) {
            return new Num(1);
        }

        return new Pow(this, ex);
    }


    protected abstract boolean canBeAdded(Expression ex);
    protected abstract boolean canBeMultiplied(Expression ex);


    protected boolean isNumber() {
        return this.getClass() == Num.class;
    }

    protected boolean isZero() {
        return this.isNumber() && ((Num) this).getNumber() == 0;
    }

    protected boolean isOne() {
        return this.isNumber() && ((Num) this).getNumber() == 1;
    }

    protected double asNumber() {
        return ((Num) this).getNumber();
    }


    protected boolean isVariable() {
        return this.getClass() == Variable.class;
    }

    protected boolean isDivision() {
        return this.getClass() == Division.class;
    }

    protected boolean isSum() {
        return this.getClass() == Sum.class;
    }

    protected boolean isMultiplication() {
        return this.getClass() == Multiplication.class;
    }

    protected boolean isPow() {
        return this.getClass() == Pow.class;
    }

    protected boolean isLn() {
        return this.getClass() == Ln.class;
    }


    protected abstract Expression getFirstOperand() throws UnsupportedOperationException;
    protected abstract Expression getSecondOperand() throws UnsupportedOperationException;


    public HashSet<String> allVariables() {
        HashSet<String> variables = new HashSet<>();

        if (this.getClass() == Variable.class) {
            variables.add(this.toString());

        } else if (!this.isAtomic()) {
            variables.addAll(this.getFirstOperand().allVariables());
            variables.addAll(this.getSecondOperand().allVariables());

        } else if (this.getClass() == Ln.class) {
            variables.addAll(((Ln) this).getValue().allVariables());
        }

        return variables;
    }

    public Num calculateValue(double variableValue) {
        if (this.allVariables().size() == 0) {
            return (Num) this;
        }

        if (this.allVariables().size() > 1) {
            throw new UnsupportedOperationException("Too many variables");
        }

        String var = this.allVariables().iterator().next();
        HashMap<String, Double> tmp = new HashMap<>();
        tmp.put(var, variableValue);

        return (Num) calculateValue(tmp);
    }

    public abstract Expression calculateValue(HashMap<String, Double> attribution);


    Expression commonMultiplier(Expression ex) {
        if (this.isOne() || ex.isOne()) {
            return Num.ONE;
        } else if (ex.isSum() || ex.isMultiplication()) {
            return ex.commonMultiplier(this);
        } else if (this.equals(ex)) {
            return this;
        } else {
            return Num.ONE;
        }
    }


    boolean isAtomic() {
        return this.getClass() == Num.class ||
               this.getClass() == Variable.class ||
               this.getClass() == Ln.class;
    }


    protected static String asString(double value) {
        return (value == (int) value) ? Integer.toString((int) value) : Double.toString(value);
    }


    abstract public Expression differentiation(String variable);
    public Expression differentiation(Variable variable) {
        return differentiation(variable.toString());
    }
    public Expression differentiation(char variable) {
        return differentiation(Character.toString(variable));
    }


    @Override
    abstract public Expression clone();
}