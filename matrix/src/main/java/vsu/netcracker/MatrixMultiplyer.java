package vsu.netcracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplyer {
    private Logger log = LogManager.getLogger(this.getClass());

    /**
     * multiply two matrices with specified amount of threads
     * @param m1 matrix 1
     * @param m2 matrix 2
     * @param threadsAmount amount of threads o be used
     * @return resulting matrix
     * @throws Exception
     */
    public Matrix multiply(Matrix m1, Matrix m2, int threadsAmount) throws Exception {
        //checking that matrices are square and of the same size
        if (m1.length(0) == m2.length(1) &&
                m1.length(1) == m2.length(0)) {

            Matrix mRes = new Matrix(m1.length(0), m2.length(1));
            int rowsPerThread = m1.length(0) / threadsAmount;
            List<MatrixMultiplyingThread> threads= new ArrayList<MatrixMultiplyingThread>();

            //loop creating threads
            for (int i = 0; i < threadsAmount; i++) {
                List<Integer> rows = new ArrayList<Integer>();
                MatrixMultiplyingThread thread;

                //decide which rows will be calculated by this thread
                for (int row = i * rowsPerThread; row < ((i == threadsAmount - 1) ? mRes.length(0) : (i+1)*rowsPerThread); row++){
                    rows.add(row);
                }

                if(rows.size()>0) {
                    thread = new MatrixMultiplyingThread(m1, m2, mRes, rows);
                    thread.start();
                    threads.add(thread);
                }
            }

            //wait for all threads to finish
            for(MatrixMultiplyingThread thread: threads){
                thread.join();
            }

            return mRes;

        } else {
            String msg = String.format("Matrixes must be square and of the same size to multiply them");
            log.error(msg);
            throw new Exception(msg);
        }
    }
}
