package vsu.netcracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Matrix {
    private double[][] matrix;
    private Logger log = LogManager.getLogger(this.getClass());

    /**
     * Get the value of matrix element
     *
     * @param x row index
     * @param y column index
     * @return required element
     * @throws IndexOutOfBoundsException
     */
    public double get(int x, int y) throws IndexOutOfBoundsException {
        if (checkIndices(x, y)) {
            return matrix[x][y];
        } else {
            return -1;
        }
    }

    /**
     * Get the size of matrix
     *
     * @param dimension needed dimension
     * @return size of matrix on specified dimension
     */
    public int length(int dimension) {
        if (dimension == 0)
            return matrix.length;
        if (dimension == 1)
            return matrix[0].length;
        else
            return -1;
    }

    /**
     * set the value of specified element
     *
     * @param x     row index
     * @param y     column index
     * @param value value to set
     * @throws IndexOutOfBoundsException
     */
    public void set(int x, int y, double value) throws IndexOutOfBoundsException {
        if (checkIndices(x, y)) {
            matrix[x][y] = value;
        }
    }

    private boolean checkIndices(int x, int y) throws IndexOutOfBoundsException {
        if (x >= matrix.length || y >= matrix[0].length || x < 0 || y < 0) {
            String msg = String.format("Matrix indices out of bounds. (%d, %d) max: (%d, %d)", x, y, matrix.length, matrix[0].length);
            log.error(msg);
            throw new IndexOutOfBoundsException(msg);
        }
        return true;
    }

    public Matrix(int width, int height) throws IndexOutOfBoundsException {
        if (width > 0 && height > 0) {
            matrix = new double[width][height];
        } else {
            String msg = String.format("Matrix size must be > 0");
            log.error(msg);
            throw new IndexOutOfBoundsException(msg);
        }
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        String s = "";
        for (double[] row : matrix) {
            for (double element : row) {
                s += element + " ";
            }
            s += '\n';
        }
        return s;
    }

    @Override
    public boolean equals(Object matrix) {
        return (matrix instanceof Matrix) && (matrix != null) && (matrix.hashCode()==this.hashCode());
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(matrix[0]);
        for (int i = 1; i < matrix.length; i++)
            result = 31 * result + Arrays.hashCode(matrix[i]);
        return result;
    }
}
