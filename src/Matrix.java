package src;

public class Matrix {

    private Fraction[][] matrix;
    int numRows;
    int numCols;

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
     * Getters and Helpers
     */
    public int numRows() {return numRows;}

    public int numColumns() {return numCols;}

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

    public Fraction getEntry(int rowNum, int columnNum) {
        return matrix[rowNum][columnNum];
    }

    public void setRow(int rowIndex, Fraction[] row) {
        if (row.length != numColumns()) {
            throw new IllegalArgumentException("Invalid row length");
        } else if (rowIndex >= numRows() || rowIndex < 0) {
            throw new IllegalArgumentException("Row index out of bounds");
        }

        System.arraycopy(row, 0, matrix[rowIndex], 0, numCols);
    }

    public void setEntry(int rowIndex, int columnIndex, Fraction entry) {
        if (rowIndex >= numRows() || rowIndex < 0) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (columnIndex >= numColumns() || columnIndex < 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        matrix[rowIndex][columnIndex] = entry;
    }

    public void swapRows(int indexRowA, int indexRowB) {
        if (indexRowA == indexRowB) return;

        Fraction[] temp = new Fraction[numCols];

        System.arraycopy(matrix[indexRowA], 0, temp, 0, numCols);
        System.arraycopy(matrix[indexRowB], 0, matrix[indexRowA], 0, numCols);
        System.arraycopy(temp, 0, matrix[indexRowB], 0, numCols);
    }

    /**
     * Add
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
     * Subtract rows
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
     * Scaling Row
     */

    public void scaleUpRow(int indexRow, Fraction scalar) {
        for (int i = 0; i < numColumns(); i++) {
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
        } else if (normalColumnIndex >= numColumns()) {
            throw new IllegalArgumentException("Column index out of bounds");
        }

        scaleDownRow(rowIndex, getEntry(rowIndex, normalColumnIndex));
    }

    public Fraction[] getNormalisedRow(int rowIndex, int normalColumnIndex) {
        if (rowIndex >= numRows()) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (normalColumnIndex >= numColumns()) {
            throw new IllegalArgumentException("Column index out of bounds");
        }

        Fraction[] normalisedRow = new Fraction[numColumns()];
        for (int i = 0; i < numColumns(); i++) {
            normalisedRow[i] = Fraction.divide(getEntry(rowIndex, i), matrix[rowIndex][normalColumnIndex]);
        }
        return normalisedRow;
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

    /**
     * Multiplication
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
            throw new IllegalArgumentException("This matrix needs to have the same number of columns as Matrix m has rows");
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
     * Addition
     */

    public static Matrix add(Matrix a, Matrix b) {
        if (a.numRows != b.numRows || a.numCols != b.numCols) {
            throw new IllegalArgumentException("Matrices a and b need to have the same dimensions for addition to be possible");
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
     * Print
     */
    public void printRow(int rowIndex) {
        System.out.print("( " + getEntry(rowIndex, 0));
        for (int i = 1; i < numColumns(); i++) {
            System.out.print(" , " + getEntry(rowIndex, i));
        }
        System.out.print(" )\n");
    }

    public void printRowAsDecimal(int rowIndex) {
        System.out.printf("( %.3f", getEntry(rowIndex, 0).doubleValue());
        for (int i = 1; i < numColumns(); i++) {
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
}