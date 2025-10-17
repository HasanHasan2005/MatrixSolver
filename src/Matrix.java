package src;

public class Matrix {

    private Fraction[][] matrix;
    private int numRows;
    private int numCols;

    /**
     * Constructors
     */
    public Matrix(int numRows, int numCols, Fraction defaultVal) {
        this.numRows = numRows;
        this.numCols = numCols;

        this.matrix = new Fraction[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = defaultVal.getValue();
            }
        }

    }

    public Matrix(Fraction[][] matrix) {
        numRows = matrix.length;
        numCols = matrix[0].length;
        this.matrix = new Fraction[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, numCols);
        }
    }

    public Matrix(double[][] matrix, int decimalPrec) {
        numRows = matrix.length;
        numCols = matrix[0].length;
        this.matrix = new Fraction[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                this.matrix[i][j] = new Fraction(matrix[i][j], decimalPrec);
            }
        }
    }

    public Matrix(float[][] matrix, int decimalPrec) {
        numRows = matrix.length;
        numCols = matrix[0].length;
        this.matrix = new Fraction[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                this.matrix[i][j] = new Fraction(matrix[i][j], decimalPrec);
            }
        }
    }

    public Matrix(int[][] matrix) {
        numRows = matrix.length;
        numCols = matrix[0].length;
        this.matrix = new Fraction[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                this.matrix[i][j] = new Fraction(matrix[i][j]);
            }
        }
    }

    /**
     * Getters
     */
    public int numRows() {return numRows;}

    public int numCols() {return numCols;}

    public int numEntries() {return numRows * numCols;}

    public Fraction[][] getValues() {
        Fraction[][] temp = new Fraction[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            System.arraycopy(matrix[i], 0, temp[i], 0, numCols);
        }
        return temp;
    }

    public Fraction[] getRow(int rowNum) {
        Fraction[] temp = new Fraction[numCols];
        System.arraycopy(temp, 0, matrix[rowNum], 0, numCols);
        return temp;
    }

    public Fraction[] getColumn(int colNum) {
        Fraction[] temp = new Fraction[numRows];
        for (int i = 0; i < numRows; i++) {
            temp[i] = matrix[i][colNum];
        }
        return temp;
    }

    public Fraction getEntry(int rowNum, int columnNum) {
        return matrix[rowNum][columnNum];
    }

    /**
     * Setters
     */
    public void setRow(int rowIndex, Fraction[] row) {
        if (row.length != numCols()) {
            throw new IllegalArgumentException("Invalid row length");
        } else if (rowIndex >= numRows() || rowIndex < 0) {
            throw new IllegalArgumentException("Row index out of bounds");
        }

        System.arraycopy(row, 0, matrix[rowIndex], 0, numCols);
    }

    public void setEntry(int rowIndex, int columnIndex, Fraction entry) {
        if (rowIndex >= numRows() || rowIndex < 0) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (columnIndex >= numCols() || columnIndex < 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        matrix[rowIndex][columnIndex] = entry;
    }

    /**
     * Row Addition
     */

    public void addRowScaled(int indexRowChanged, Fraction[] rowToAdd, Fraction scalar) {
        for (int i = 0; i < numCols; i++) {
            matrix[indexRowChanged][i].add(Fraction.multiply(rowToAdd[i], scalar));
        }
    }

    public void addRowScaled(int indexRowChanged, Fraction[] rowToAdd, int scalar) {
        addRowScaled(indexRowChanged, rowToAdd, new Fraction(scalar));
    }

    public void addRow(int indexRowChanged, Fraction[] rowToAdd) {
        addRowScaled(indexRowChanged, rowToAdd, new Fraction(1));
    }


    public void addRowScaled(int indexRowChanged, int indexRowUnchanged, Fraction scalar) {
        for (int i = 0; i < numCols; i++) {
            matrix[indexRowChanged][i].add(Fraction.multiply(matrix[indexRowUnchanged][i], scalar));
        }
    }

    public void addRowScaled(int indexRowChanged, int indexRowUnchanged, int scalar) {
        addRowScaled(indexRowChanged, indexRowUnchanged, new Fraction(scalar));
    }

    public void addRow(int indexRowChanged, int indexRowUnchanged) {
        addRowScaled(indexRowChanged, indexRowUnchanged, new Fraction(1));
    }

    /**
     * Row Subtraction
     */

    public void subtractRowScaled(int indexRowChanged, Fraction[] rowToAdd, Fraction scalar) {
        addRowScaled(indexRowChanged, rowToAdd, scalar.getAdditiveInverse());
    }

    public void subtractRowScaled(int indexRowChanged, Fraction[] rowToAdd, int scalar) {
        addRowScaled(indexRowChanged, rowToAdd, new Fraction(-scalar));
    }

    public void subtractRow(int indexRowChanged, Fraction[] rowToAdd) {
        addRowScaled(indexRowChanged, rowToAdd, new Fraction(-1));
    }

    public void subtractRowScaled(int indexRowChanged, int indexRowUnchanged, Fraction scalar) {
        addRowScaled(indexRowChanged, indexRowUnchanged, scalar.getAdditiveInverse());
    }

    public void subtractRowScaled(int indexRowChanged, int indexRowUnchanged, int scalar) {
        addRowScaled(indexRowChanged, indexRowUnchanged, new Fraction(-scalar));
    }

    public void subtractRow(int indexRowChanged, int indexRowUnchanged) {
        addRowScaled(indexRowChanged, indexRowUnchanged, new Fraction(-1));
    }

    /**
     * Row Scalars
     */

    public void scaleUpRow(int indexRow, Fraction scalar) {
        for (int i = 0; i < numCols(); i++) {
            matrix[indexRow][i].multiply(scalar);
        }
    }

    public void scaleUpRow(int indexRow, int scalar) {
        scaleUpRow(indexRow, new Fraction(scalar));
    }

    public void scaleDownRow(int indexRow, Fraction scalar) {
        scaleUpRow(indexRow, scalar.getMultiplicativeInverse());
    }

    public void scaleDownRow(int indexRow, int scalar) {
        scaleUpRow(indexRow, (new Fraction(scalar)).getMultiplicativeInverse());
    }

    public void normaliseRow(int rowIndex, int normalColumnIndex) {
        if (rowIndex >= numRows()) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (normalColumnIndex >= numCols()) {
            throw new IllegalArgumentException("Column index out of bounds");
        }

        scaleDownRow(rowIndex, getEntry(rowIndex, normalColumnIndex));
    }

    /**
     * Scaled-Row Getters
     */

    public Fraction[] getRowScaledUp(int rowNum, Fraction scalar) {
        Fraction[] temp = new Fraction[numRows];
        for (int i = 0; i < numCols; i++) {
            temp[i] = Fraction.multiply(matrix[rowNum][i], scalar);
        }
        return temp;
    }

    public Fraction[] getRowScaledUp(int rowNum, int scalar) {
        return getRowScaledUp(rowNum, new Fraction(scalar));
    }

    public Fraction[] getRowScaledDown(int rowNum, Fraction scalar) {
        return getRowScaledUp(rowNum, scalar.getMultiplicativeInverse());
    }

    public Fraction[] getRowScaledDown(int rowNum, int scalar) {
        return getRowScaledUp(rowNum, (new Fraction(scalar)).getMultiplicativeInverse());
    }

    public Fraction[] getNormalisedRow(int rowIndex, int normalColumnIndex) {
        if (rowIndex >= numRows()) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (normalColumnIndex >= numCols()) {
            throw new IllegalArgumentException("Column index out of bounds");
        }

        Fraction[] normalisedRow = new Fraction[numCols()];
        for (int i = 0; i < numCols(); i++) {
            normalisedRow[i] = Fraction.divide(getEntry(rowIndex, i), matrix[rowIndex][normalColumnIndex]);
        }
        return normalisedRow;
    }

    /**
     * Matrix Multiplication
     */

    public static Matrix multiply(Matrix a, Matrix b) {
        if (a.numCols != b.numRows) {
            throw new IllegalArgumentException("Matrix a needs to have the same number of columns as Matrix b has rows");
        }

        Fraction[][] newMatrix = new Fraction[a.numRows][b.numCols];
        for (int i = 0; i < a.numRows; i++) {
            for (int j = 0; j < b.numCols; j++) {
                Fraction sum = new Fraction(0);
                for (int k = 0; k < a.numCols; k++) {
                    sum.add(Fraction.multiply(a.getEntry(i, k), b.getEntry(k, j)));
                }
                newMatrix[i][j] = sum.getValue();
            }
        }
        return new Matrix(newMatrix);
    }

    public void multiply(Matrix m) {
        if (numCols != m.numRows) {
            throw new IllegalArgumentException("Invalid dimensions for Matrix Multiplication");
        }

        Fraction[][] newMatrix = new Fraction[numRows][m.numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < m.numCols; j++) {
                Fraction sum = new Fraction(0);
                for (int k = 0; k < numCols; k++) {
                    sum.add(Fraction.multiply(getEntry(i, k), m.getEntry(k, j)));
                }
                newMatrix[i][j] = sum.getValue();
            }
        }
        matrix = newMatrix;
        numCols = m.numCols;
    }

    /**
     * Matrix Addition
     */

    public static Matrix add(Matrix a, Matrix b) {
        if (a.numRows != b.numRows || a.numCols != b.numCols) {
            throw new IllegalArgumentException("Matrices have different dimensions");
        }

        Fraction[][] newMatrix = new Fraction[a.numRows][a.numCols];
        for (int i = 0; i < a.numRows; i++) {
            for (int j = 0; j < a.numCols; j++) {
                newMatrix[i][j] = Fraction.add(a.getEntry(i, j), b.getEntry(i, j));
            }
        }
        return new Matrix(newMatrix);
    }

    public void add(Matrix m) {
        if (numRows != m.numRows || numCols != m.numCols) {
            throw new IllegalArgumentException("Matrix m need to have the same dimensions for addition to be possible");
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                setEntry(i, j, Fraction.add(getEntry(i, j), m.getEntry(i, j)));
            }
        }
    }

    /**
     * Transpose Functions
     */

    public void transpose() {
        Fraction[][] newMatrix = new Fraction[numCols][numRows];

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                newMatrix[c][r] = matrix[r][c];
            }
        }

        matrix = newMatrix;

        int temp = numCols;
        numCols = numRows;
        numRows = temp;
    }

    public static Matrix getTranspose(Matrix m) {
        Matrix res = new Matrix(m.numCols(), m.numRows(), new Fraction(0));

        for (int r = 0; r < m.numRows(); r++) {
            for (int c = 0; c < m.numCols(); c++) {
                res.setEntry(c, r, m.getEntry(r, c));
            }
        }

        return res;
    }

    /**
     * Matrix Manipulation
     */

    public void swapRows(int indexRowA, int indexRowB) {
        if (indexRowA == indexRowB) return;

        Fraction[] temp = new Fraction[numCols];

        System.arraycopy(matrix[indexRowA], 0, temp, 0, numCols);
        System.arraycopy(matrix[indexRowB], 0, matrix[indexRowA], 0, numCols);
        System.arraycopy(temp, 0, matrix[indexRowB], 0, numCols);
    }

    public void clearColumn(int rowToUse, int scalarColumn) {
        if (rowToUse >= numRows) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (scalarColumn >= numCols) {
            throw new IllegalArgumentException("Column index out of bounds");
        }

        normaliseRow(rowToUse, scalarColumn);

        for (int i = 0; i < numRows; i++) {
            if (i != rowToUse && !matrix[rowToUse][scalarColumn].isZero()) {
                subtractRowScaled(i, rowToUse, matrix[i][scalarColumn]);
            }
        }
    }

    public void bringToRREF() {
        int col = 0;
        int row = 0;

        while (col < numCols && row < numRows) {
            int row_to_use = -1;

            if (getEntry(row, col).isZero()) {
                for (int i = row + 1; i < numRows; i++) {
                    if (!getEntry(i, col).isZero()) {
                        row_to_use = i;
                        break;
                    }
                }
            } else {
                row_to_use = row;
            }

            if (row_to_use != -1) {
                if (row_to_use != row) {
                    swapRows(row, row_to_use);
                }
                clearColumn(row, col);
                row++;
            }
            col++;
        }
    }

    /**
     * Printing Matrices
     */
    public void printRow(int rowIndex) {
        System.out.print("( " + getEntry(rowIndex, 0));
        for (int i = 1; i < numCols(); i++) {
            System.out.print(" , " + getEntry(rowIndex, i));
        }
        System.out.print(" )\n");
    }

    public void printRowAsDecimal(int rowIndex) {
        System.out.printf("( %.3f", getEntry(rowIndex, 0).doubleValue());
        for (int i = 1; i < numCols(); i++) {
            System.out.printf(" , %.3f", getEntry(rowIndex, i).doubleValue());
        }
        System.out.print(" )\n");
    }

    public void print() {
        for (int i = 0; i < numRows(); i++) {
            printRow(i);
        }
    }

    public void printAsDecimal() {
        for (int i = 0; i < numRows(); i++) {
            printRowAsDecimal(i);
        }
    }


    public static void main(String[] args) {
        Matrix m1 = new Matrix(
                new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}
        );

        m1.bringToRREF();
        m1.transpose();
        m1.print();
    }
}