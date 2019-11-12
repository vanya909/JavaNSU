package ru.nsu.programming.arithmetic;

public class Main {

    public static void main(String[] args) {
        Expression a = Variable.X.pow(Variable.Y);

        System.out.println(a.differentiation(Variable.X));

    }

}
