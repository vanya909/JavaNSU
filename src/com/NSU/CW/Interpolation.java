package com.NSU.CW;

import java.io.*;
import java.util.ArrayList;

public class Interpolation {
    private static double H;
    private static double X0;

    public static void main(String[] args) throws IOException {

        ArrayList<Double> interpolErr = new ArrayList<>(); // Массив погрешностей rh;
        BufferedWriter writer;
        FileWriter fileWriter = new FileWriter(new File("Output/opData.txt"));
        writer = new BufferedWriter(fileWriter);

        for (int i = 1; i <= 4; i++) {
            interpolErr.add(Lagrange.rh3(X0, X0, H/Math.pow(2,i-1))); // Вычисление погрешности и добавление её
            int errCount = interpolErr.size();                           //                                   в массив

            String resultString = i + "\t\t" + H/Math.pow(2,i-1) + "\t\t" + interpolErr.get(errCount - 1);

            if (i >= 2)
                resultString += "\t\t" + Lagrange.m(interpolErr);

            if (i >= 3)
                resultString += "\t\t" + Lagrange.n(interpolErr);

            writer.write(resultString + "\n");
        }
        writer.close();
    }


    static {
        InputStreamReader ipReader = null;
        BufferedReader reader = null;

        try {
            ipReader = new InputStreamReader(new FileInputStream(new File("Input/ipData.txt")));
            reader = new BufferedReader(ipReader);

            H = Double.parseDouble(reader.readLine());
            X0 = Double.parseDouble(reader.readLine());

        } catch (IOException IOE) {
            System.out.println(IOE.getMessage());
            IOE.printStackTrace();

        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException IOE) {
                IOE.printStackTrace();
                System.out.println(IOE.getMessage());
            }
        }
    }

    private Interpolation() {}
}
