package com.NSU.CW;

import java.util.ArrayList;

public class Interpolation {
    private static final double H = 0.1;
    private static final double X0 = 0.5;



    public static void main(String[] args) {

        ArrayList<Double> interpolErr = new ArrayList<>(); // Массив погрешностей rh;

        for (int i = 1; i <= 4; i++) {

            interpolErr.add(Lagrange.rh2(X0, X0, H/Math.pow(2,i-1))); // Вычисление погрешности и добавление её
            int errCount = interpolErr.size();                           //                                   в массив

            String resultString = i + "\t\t" + H/Math.pow(2,i-1) + "\t\t" + interpolErr.get(errCount - 1);


            if (i >= 2) {
                double errAbsRelation = Math.abs( interpolErr.get(errCount-2) / interpolErr.get(errCount-1) );
                double m = Math.log(errAbsRelation) / Math.log(2); // Переход к новому основанию логарифма

                resultString += "\t\t" + m;
            }

            if (i >= 3) {
                double lastErrDifference = Math.abs(interpolErr.get(errCount-3) - interpolErr.get(errCount-2));
                double errDifference = Math.abs(interpolErr.get(errCount-2) - interpolErr.get(errCount-1));

                double n = Math.log(lastErrDifference / errDifference) / Math.log(2);

                resultString += "\t\t" + n;
            }

            System.out.println(resultString);
            System.out.println();
        }
    }



    private Interpolation() {}
}
