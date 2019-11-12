package ru.nsu.programming.arithmetic;

import java.util.ArrayList;
import java.util.HashMap;

public class Multiplication extends Expression {
    private ArrayList<Expression> multipliers = new ArrayList<>();

    private Multiplication() {
    }

    Multiplication(Expression firstOperand, Expression secondOperand) {
        multipliers.add(firstOperand);
        multipliers.add(secondOperand);
    }


    @Override
    public Expression plus(Expression ex) {
        if (ex.isZero()) {
            return this;
        } else if (ex.isSum()) {
            return ex.plus(this);
        } else if (ex.isVariable() || ex.isMultiplication() || ex.isPow()) {
            if (this.canBeAdded(ex)) {
                Expression commonMultiplier = this.commonMultiplier(ex);
                return commonMultiplier.multiply(this.divide(commonMultiplier).plus(ex.divide(commonMultiplier)));
            }
        }

        return new Sum(this, ex);
    }

    @Override
    public Expression multiply(Expression ex) {
        if (ex.isZero()) {
            return new Num(0);
        } else if (ex.isOne()) {
            return this;
        } else if (ex.isSum()) {
            return ex.multiply(this);
        }

        Expression result = clone();
        ArrayList<Expression> mults = ((Multiplication) result).multipliers;

        if (ex.isMultiplication()) {
            for (int i = 0; i < ((Multiplication) ex).getMultipliersCount(); i++) {
                result = result.multiply(((Multiplication) ex).get(i));
            }
            return result;
        }
        for (int i = 0; i < mults.size(); i++) {
            if (mults.get(i).canBeMultiplied(ex)) {

                mults.set(i, mults.get(i).multiply(ex));
                if (mults.get(i).equals(Num.ONE)) {
                    mults.remove(i);
                }
                if (mults.size() == 1) {
                    return mults.get(0);
                }
                return result;
            }
        }
        if (ex.isNumber()) {
            mults.add(0, ex);
        } else {
            mults.add(ex);
        }
        return result;
    }

    @Override
    public Expression divide(Expression ex) {
        Multiplication result = clone();

        if (ex.isZero()) {
            throw new ArithmeticException("/ by zero");
        } else if (ex.isOne()) {
            return this;
        } else if (ex.isDivision()) {
            return this.multiply(ex.getSecondOperand().divide(ex.getFirstOperand()));
        } else if (!ex.isMultiplication() && !ex.isSum()) {
            Expression commonMultiplier = commonMultiplier(ex);
            if (!commonMultiplier.equals(Num.ONE)) {
                for (int i = 0; i < result.multipliers.size(); i++) {
                    if (result.multipliers.get(i).equals(commonMultiplier)) {
                        result.multipliers.remove(i);
                        return result.multipliers.size() > 1 ? result : result.multipliers.get(0);
                    }
                }
            } else {
                return new Division(this, ex);
            }
        } else if (ex.isMultiplication()) {
            Expression commonMultiplier = commonMultiplier(ex);
            if (!commonMultiplier.equals(Num.ONE)) {
                Expression numerator = this.equals(commonMultiplier) ? Num.ONE : this.divide(commonMultiplier);
                Expression denominator = ex.equals(commonMultiplier) ? Num.ONE : ex.divide(commonMultiplier);

                return numerator.divide(denominator);
            }
        } else if (ex.isSum()) {
            Expression commonMultiplier = commonMultiplier(ex);
            Expression numerator = this.clone();
            Expression denominator = Num.ZERO;

            if (!commonMultiplier.equals(Num.ONE)) {
                numerator = numerator.divide(commonMultiplier);
                for (int i = 0; i < ((Sum) ex).getAddendsCount(); i++) {
                    denominator = denominator.plus(((Sum) ex).get(i).divide(commonMultiplier));
                }
            }
        }

        return new Division(this, ex);
    }


    @Override
    protected boolean canBeAdded(Expression ex) {
        Expression commonMultiplier = this.commonMultiplier(ex);
        return this.divide(commonMultiplier).isNumber() && ex.divide(commonMultiplier).isNumber();
    }

    @Override
    protected boolean canBeMultiplied(Expression ex) {
        for (Expression multiplier : multipliers) {
            if (multiplier.canBeMultiplied(ex)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Expression differentiation(String variable) {
        Expression result = Num.ZERO;

        for (int i = 0; i < multipliers.size(); i++) {
            Expression operand = multipliers.get(i).differentiation(variable);

            for (int j = 0; j < multipliers.size(); j++) {
                if (j == i) {
                    continue;
                } else {
                    operand = operand.multiply(multipliers.get(j));
                }
            }

            result = result.plus(operand);
        }

        return result;
    }

    @Override
    public Expression calculateValue(HashMap<String, Double> attribution) {
        Expression result = Num.ONE;

        for (Expression multiplier : multipliers) {
            result = result.multiply(multiplier.calculateValue(attribution));
        }

        return result;
    }


    @Override
    Expression commonMultiplier(Expression ex) {

        if (ex.isSum()) {
            return ex.commonMultiplier(this);
        } else if (!ex.isMultiplication()) {
            for (Expression multiplier : multipliers) {
                if (multiplier.equals(ex)) {
                    return ex;
                }
            }

            return Num.ONE;
        } else {
            Expression commonMultiplier = Num.ONE;

            for (int i = 0; i < ((Multiplication) ex).getMultipliersCount(); i++) {
                for (int j = 0; j < this.getMultipliersCount(); j++) {
                    if (((Multiplication) ex).get(i).equals(this.get(j))) {
                        commonMultiplier = commonMultiplier.multiply(this.get(j));
                        break;
                    }
                }
            }

            return commonMultiplier;
        }
    }


    Expression get(int i) {
        return multipliers.get(i);
    }

    int getMultipliersCount() {
        return multipliers.size();
    }

    @Override
    protected Expression getFirstOperand() {
        throw new UnsupportedOperationException("Use \"get()\"");
    }
    @Override
    protected Expression getSecondOperand() {
        throw new UnsupportedOperationException("Use \"get()\"");
    }


    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Multiplication.class ||
            multipliers.size() != ((Multiplication) obj).getMultipliersCount()) {
            return false;
        }
        for (int i = 0; i < multipliers.size(); i++) {
            if (!multipliers.get(i).equals(((Multiplication) obj).get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Multiplication clone() {
        Multiplication result = new Multiplication();

        for (Expression multiplier : multipliers) {
            result.multipliers.add(multiplier.clone());
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Expression multiplier : multipliers) {
            if (multiplier.isSum()) {
                sb.append("(");
                sb.append(multiplier.toString());
                sb.append(")");
            } else {
                sb.append(multiplier.toString());
            }
        }

        return sb.toString();
    }
}
