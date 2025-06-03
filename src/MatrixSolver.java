package src;

public class MatrixSolver {

    public static void main(String[] args) {

        int precision = 3;

        Matrix m1 = new Matrix(new double[][]{
                {0.022, 0.033, 23.44, 607.87},
                {51.22, 81.09, 5.6007, 1.8902},
                {6.667, 3.446, 4.555, 3.001}}, precision);

        Matrix m2 = new Matrix(new int[][]{
                {1, 8},
                {2, 7},
                {3, 6},
                {4, 5}});

        m1.multiply(m2);
        m1.printAsDecimal();
    }
}