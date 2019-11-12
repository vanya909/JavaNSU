package ru.nsu.programming.vectors;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        VectorN vectorN = new VectorN(1, 1, 1);
        VectorN vectorN1 = new VectorN(1, 0, 1);
        VectorN vectorN2 = new VectorN(1, 0, 0);

        ArrayList<VectorN> vectorNS = new ArrayList<>();
        vectorNS.add(vectorN);
        vectorNS.add(vectorN1);
        vectorNS.add(vectorN2);

        System.out.println(VectorN.orthogonalization(vectorNS));

    }
}
