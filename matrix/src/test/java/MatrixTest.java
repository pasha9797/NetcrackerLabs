import org.junit.Assert;
import org.junit.Test;
import vsu.netcracker.Matrix;
import vsu.netcracker.MatrixMultiplyer;

public class MatrixTest {

    @Test
    public void testMultiply() throws Exception {
        Matrix m1 = new Matrix(
                new double[][]{
                        new double[]{1, 6, 3, 2},
                        new double[]{4, 6, 7, 9},
                        new double[]{2, 2, 3, 3},
                        new double[]{4, 1, 8, 9},
                }
        );
        Matrix m2 = new Matrix(
                new double[][]{
                        new double[]{2, 5, 6, 4},
                        new double[]{4, 5, 1, 2},
                        new double[]{8, 9, 2, 4},
                        new double[]{1, 5, 3, 4},
                }
        );

        Matrix expected = new Matrix(
                new double[][]{
                        new double[]{52, 72, 24, 36},
                        new double[]{97, 158, 71, 92},
                        new double[]{39, 62, 29, 36},
                        new double[]{85, 142, 68, 86},
                }
        );

        Matrix mRes = (new MatrixMultiplyer()).multiply(m1, m2, 4);
        //System.out.println(mRes.hashCode() + " " + expected.hashCode());
        Assert.assertEquals(expected, mRes);
    }
}
