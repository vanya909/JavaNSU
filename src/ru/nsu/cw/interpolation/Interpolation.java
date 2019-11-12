package ru.nsu.cw.interpolation;

import java.io.*;
import java.util.ArrayList;

public class Interpolation {
    private static double H;
    private static double X0;

    public static void main(String[] args) throws IOException {

        ArrayList<Double> interpolErrors = new ArrayList<>(); // Список погрешностей (чтобы не считать заново)
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Output/opData.txt")));

        writeResult(10, interpolErrors, writer);

        writer.close();
    }

    private static void writeResult(int linesCount, ArrayList<Double> interpolErrors, BufferedWriter writer)
                                                                                                 throws IOException {
        writer.write(" \t\t" + "h" + "\t\t\t\t" + "Error" + "\t\t\t\t" + "m" + "\t\t\t\t" + "n" + '\n');
        for (int i = 1; i <= linesCount; i++) {
            interpolErrors.add(Lagrange.error(X0 - 6 * H, X0, H/Math.pow(2,i-1)));

            writer.write(i + "\t\t");
            writer.write(String.format("%f", H/Math.pow(2,i-1)) + "\t\t");
            writer.write(String.format("%e", interpolErrors.get(interpolErrors.size() - 1)) + "\t\t");

            if (i >= 2)
                writer.write(String.format("%.6f", Lagrange.m(interpolErrors)) + "\t\t");
            if (i >= 3)
                writer.write(String.format("%.6f", Lagrange.n(interpolErrors)) + "\t\t");

            writer.write('\n');
        }
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
