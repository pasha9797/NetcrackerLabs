package vsu.netcracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MatrixMultiplyingThread extends Thread {
    private Matrix m1, m2, mRes;
    private List<Integer> rowsToCalculate;

    @Override
    public void run() {
        for (int i : rowsToCalculate) {
            for (int j = 0; j < m1.length(0); j++) {
                double res = 0;
                for (int k = 0; k < m1.length(1); k++) {
                    res += m1.get(i, k) * m2.get(k, j);
                }
                mRes.set(i, j, res);
            }
        }
    }

    public MatrixMultiplyingThread(Matrix m1, Matrix m2, Matrix mRes, List<Integer> rowsToCalculate) {

        this.m1 = m1;
        this.m2 = m2;
        this.mRes = mRes;
        this.rowsToCalculate = rowsToCalculate;
    }
}
