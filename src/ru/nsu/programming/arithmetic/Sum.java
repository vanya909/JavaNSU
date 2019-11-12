package ru.nsu.programming.arithmetic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Сумма
 * @author Ivan
 */

public class Sum extends Expression {

    /**
     * Лист всех слагаемых
     */
    private ArrayList<Expression> addends = new ArrayList<>();

    /**
     * Приватный конструктор без параметров
     * Используется только для создания клона выражения
     */
    private Sum() {
    }

    /**
     * Используется в случае, если первый и второй операнд не могут быть сложены вместе
     * @param firstOperand - первый операнд
     * @param secondOperand - второй операнд
     */
    Sum(Expression firstOperand, Expression secondOperand) {
        addends.add(firstOperand);
        addends.add(secondOperand);
    }

    /**
     * Операция "плюс"
     * @param ex - выражение, которое будет складывать с this
     * @return возвращает результат метода plusWithoutOptimization() с вынесенным общем множителем
     */
    @Override
    public Expression plus(Expression ex) {
        if (ex.isZero()) {
            return this.clone();
        }
        Expression result = clone();
        ArrayList<Expression> adds = ((Sum) result).addends;

        if (ex.isSum()) {
            Sum T=(Sum)ex;
            for (int i = 0; i < T.getAddendsCount(); i++) {
                result = result.plus(T.get(i));
            }
            return result;
        }
        for (int i = 0; i < adds.size(); i++) {
            if (adds.get(i).canBeAdded(ex)) {

                adds.set(i, adds.get(i).plus(ex));
                if (adds.get(i).equals(Num.ZERO)) {
                    adds.remove(i);
                }
                if (adds.size() == 1) {
                    return adds.get(0);
                }
                return result;
            }
        }
        adds.add(ex);
        return result;
    }

    @Override
    public Expression multiply(Expression ex) {
        Expression result = Num.ZERO;
        for (int i = 0; i < addends.size(); i++) {
            result = result.plus(ex.multiply(addends.get(i)));
        }

        return result;
    }


    @Override
    public Expression differentiation(String variable) {
        Expression result = Num.ZERO;

        for (Expression addend : addends) {
            result = result.plus(addend.differentiation(variable));
        }

        return result;
    }

    @Override
    public Expression calculateValue(HashMap<String, Double> attribution) {
        Expression result = Num.ZERO;

        for (Expression addend : addends) {
            result = result.plus(addend.calculateValue(attribution));
        }

        return result;
    }





    //------------------------------------------------------------------------------------------------------------------
    // Optimization:
    //------------------------------------------------------------------------------------------------------------------

    /**
     * Блок с оптимизациями
     * Все методы из блока направлены на привидение выражения к читабельному виду
     */

    @Override
    protected boolean canBeAdded(Expression ex) {
        throw new UnsupportedOperationException("Sum can't be added to anything");
    }

    @Override
    protected boolean canBeMultiplied(Expression ex) {
        throw new UnsupportedOperationException("Sum can't be multiplied to anything");
    }

    @Override
    Expression commonMultiplier(Expression ex) {
        Expression commonMultiplier = ex;
        for (Expression addend : addends) {
            commonMultiplier = addend.commonMultiplier(commonMultiplier);
        }
        return commonMultiplier;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Sum.class) {
            return false;
        } else if (getAddendsCount() != ((Sum) obj).getAddendsCount()) {
            return false;
        }
        for (int i = 0; i < getAddendsCount(); i++) {
            if (!get(i).equals(((Sum) obj).get(i))) {
                return false;
            }
        }
        return true;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Optimization end.
    //------------------------------------------------------------------------------------------------------------------





    Expression get(int i) {
        return addends.get(i);
    }

    int getAddendsCount() {
        return addends.size();
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
    public Sum clone() {
        Sum result = new Sum();

        for (Expression addend : addends) {
            result.addends.add(addend.clone());
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < addends.size(); i++) {
            if (i != addends.size() - 1) {
                sb.append(addends.get(i));
                sb.append("+");
            } else {
                sb.append(addends.get(i));
            }
        }

        return sb.toString();
    }
}
