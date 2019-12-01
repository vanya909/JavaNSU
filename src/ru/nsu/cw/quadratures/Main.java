package ru.nsu.cw.quadratures;

import java.io.*;

// Студинский_18141_1-2_квадратуры
// Площадь графика математической функции sqrt(x) на отрезке [0,1] - 2/3
// Площадь графика математической функции sin(px)  на отрезке [0,1] - 2 / PI

public class Main {
    private static PrintStream printStream;
    private static final double LOWER_LIMIT = 0;
    private static final double UPPER_LIMIT = 1;
    private static final Quadratures selectedFunc = Quadratures.SQRT;

    public static void main(String[] args) {
        try {
            printStream = new PrintStream(new FileOutputStream(new File("Output/opData.txt")));
        } catch (IOException IOE) {
            IOE.printStackTrace();
            System.exit(1);
        }

        writeHeader();
        for (int i = 0; i < 20; i++) {
            double step = (UPPER_LIMIT - LOWER_LIMIT) / Math.pow(2, i + 1);
            double error = selectedFunc.getArea() - selectedFunc.gaussMethod(step, LOWER_LIMIT, UPPER_LIMIT);

            double integralsDifference12 = selectedFunc.gaussMethod(step, LOWER_LIMIT, UPPER_LIMIT) -
                                           selectedFunc.gaussMethod(step / 2, LOWER_LIMIT, UPPER_LIMIT);

            double integralsDifference24 = selectedFunc.gaussMethod(step / 2, LOWER_LIMIT, UPPER_LIMIT) -
                                           selectedFunc.gaussMethod(step / 4, LOWER_LIMIT, UPPER_LIMIT);

            double ln = Math.log(integralsDifference12 / integralsDifference24) / Math.log(2);
            //double refinedTrapezoid = selectedFunc.refinedTrapezoid(step, LOWER_LIMIT, UPPER_LIMIT);
            //printStream.printf("%-15.0f%-30.16g%-25.16g%-30.16g%n", (UPPER_LIMIT - LOWER_LIMIT) / step, error, ln,
                                                                     //refinedTrapezoid);
            printStream.printf("%-15.0f%-30.16g%-25.16g%n", (UPPER_LIMIT - LOWER_LIMIT) / step, error, ln);
        }

        printStream.close();
    }

    private static void writeHeader() {
        printStream.printf("%-15s%-30s%-25s%n", "Cuts count", "Gauss error", "Ln");
    }
}
