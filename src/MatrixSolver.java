package src;

public class MatrixSolver {

    public static Fraction[] scaleColumnRow(Fraction[] row, int column) {
        Fraction scalar = new Fraction(row[column].getDen(), row[column].getNum());
        return scaleRow(row, scalar);
    }

    public static Fraction[] addRow(Fraction[] mainRow, Fraction[] rowToAdd) {
        Fraction[] row = new Fraction[mainRow.length];
        for (int i = 0; i < row.length; i++) {
            row[i] = Fraction.fracAdd(mainRow[i], rowToAdd[i]);
        }
        return row;
    }

    public static Fraction[] scaleRow(Fraction[] inputRow, Fraction scalar) {
        Fraction[] row = new Fraction[inputRow.length];
        for (int i = 0; i < row.length; i++) {
            row[i] = Fraction.fracMul(inputRow[i], scalar);
        }
        return row;
    }


    public static Fraction[][] clearColumn(Fraction[][] matrix, int column) {
        int mainRow = column;

        matrix[mainRow] = scaleColumnRow(matrix[mainRow], column);

        for (int i = 0; i < matrix.length; i++) {
            if (i != mainRow && matrix[i][column].getNum() != 0) {
                matrix[i] = addRow(matrix[i], scaleRow(matrix[mainRow], Fraction.fracMul(matrix[i][column], new Fraction(-1))));
            }
        }

        return matrix;
    }

    public static void printRow(Fraction[] row) {
        System.out.print("( " + row[0]);
        for (int i = 1; i < row.length; i++) {
            System.out.print(" , " + row[i]);
        }
        System.out.print(" )\n");
    }

    public static void printMatrix(Fraction[][] matrix) {
        for (Fraction[] row : matrix) {
            printRow(row);
        }
    }



    public static void main(String[] args) {

        Fraction[][] matrix = {
        {new Fraction(-4), new Fraction(3), new Fraction(5), new Fraction(6)},
        {new Fraction(3), new Fraction(2), new Fraction(9), new Fraction(0)},
        {new Fraction(7), new Fraction(2), new Fraction(9), new Fraction(4)}};

        //matrix[0] = scaleColumnRow(matrix[0], 0);


        matrix = clearColumn(matrix, 0);
        matrix = clearColumn(matrix, 1);
        matrix = clearColumn(matrix, 2);

        printMatrix(matrix);
    }
}
