package vsu.netcracker;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        final int size = 8, threadsAmount = 4;


        Matrix m1 = new Matrix(size, size);
        Matrix m2 = new Matrix(size, size);
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                m1.set(i, j, random.nextDouble());
                m2.set(i, j, random.nextDouble());
            }
        }

        MatrixMultiplyer multiplyer = new MatrixMultiplyer();
        Matrix mRes = multiplyer.multiply(m1, m2, threadsAmount);

        System.out.println(mRes);
    }
}
