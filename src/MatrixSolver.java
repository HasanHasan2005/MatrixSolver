package src;

public class MatrixSolver {

    public static void main(String[] args) {

        Fraction[][] data = {
                {new Fraction(-4), new Fraction(3), new Fraction(5), new Fraction(6)},
                {new Fraction(3), new Fraction(2), new Fraction(9), new Fraction(0)},
                {new Fraction(7), new Fraction(2), new Fraction(9), new Fraction(4)}};

        Matrix matrix = new Matrix(data);

        matrix = Matrix.clearColumn(matrix, 0, 0);
        matrix = Matrix.clearColumn(matrix, 1, 1);
        matrix = Matrix.clearColumn(matrix, 2, 2);

        Matrix.printMatrix(matrix);
    }
}