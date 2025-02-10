package src;

public class Matrix {
    Fraction[][] matrix;

    public Matrix(Fraction[][] matrix) {
        this.matrix = matrix;
    }

    public static Fraction[] sumRows(Fraction[] row1, Fraction[] row2) {
        Fraction[] row = new Fraction[row1.length];

        for (int i = 0; i < row.length; i++) {
            row[i] = Fraction.fracAdd(row1[i], row2[i]);
        }
        return row;
    }

    public static Fraction[] scaledRow(Fraction[] inputRow, Fraction scalar) {
        Fraction[] row = new Fraction[inputRow.length];

        for (int i = 0; i < row.length; i++) {
            row[i] = Fraction.fracMul(inputRow[i], scalar);
        }
        return row;
    }

    public static Fraction[] normalisedRow(Fraction[] row, int normalColumn) {
        Fraction scalar = new Fraction(row[normalColumn].getDen(), row[normalColumn].getNum());
        return scaledRow(row, scalar);
    }

    public static Matrix clearColumn(Matrix inputMatrix, int scalarColumn, int rowToUse) {

        Matrix newMatrix = new Matrix(inputMatrix.matrix);

        newMatrix.matrix[rowToUse] = normalisedRow(newMatrix.matrix[rowToUse], scalarColumn);

        for (int i = 0; i < newMatrix.matrix.length; i++) {
            if (i != rowToUse && newMatrix.matrix[i][scalarColumn].getNum() != 0) {
                newMatrix.matrix[i] = sumRows(newMatrix.matrix[i], scaledRow(newMatrix.matrix[rowToUse],
                        Fraction.fracMul(newMatrix.matrix[i][scalarColumn], new Fraction(-1))));
            }
        }
        return newMatrix;
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

    public static void printMatrix(Matrix matrix) {
        for (Fraction[] row : matrix.matrix) {
            printRow(row);
        }
    }

}
