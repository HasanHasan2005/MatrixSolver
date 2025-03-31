package src;

public class MatrixSolver {

    public static void main(String[] args) {

        Fraction[][] data = {
                {new Fraction(2), new Fraction(3), new Fraction(0), new Fraction(1), new Fraction(6)},
                {new Fraction(6), new Fraction(1), new Fraction(-6), new Fraction(6), new Fraction(7)},
                {new Fraction(5), new Fraction(5), new Fraction(0), new Fraction(-1), new Fraction(9)}};

        ArrayMatrix matrix = new ArrayMatrix(data);

        matrix.clearColumn(0,0);
        matrix.clearColumn(1,1);
        matrix.clearColumn(2,2);

        matrix.print();
    }
}