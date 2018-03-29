package vsu.netcracker;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        final int size = 3, threadsAmount = 4;


        Matrix m1 = new Matrix(size, size-1);
        Matrix m2 = new Matrix(size-1, size);
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size-1; j++) {
                m1.set(i, j, random.nextDouble());
            }
        }

        for (int i = 0; i < size-1; i++) {
            for (int j = 0; j < size; j++) {
                m2.set(i, j, random.nextDouble());
            }
        }

        MatrixMultiplyer multiplyer = new MatrixMultiplyer();
        Matrix mRes = multiplyer.multiply(m1, m2, threadsAmount);

        System.out.println(mRes);
    }
}
