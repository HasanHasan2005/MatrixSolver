package src;

public class Matrix {

    private final Fraction[][] matrix;
    private final int numRows;
    private final int numCols;

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

    public void printRow(int rowIndex) {
        System.out.print("( " + getEntry(rowIndex, 0));
        for (int i = 1; i < numColumns(); i++) {
            System.out.print(" , " + getEntry(rowIndex, i));
        }
        System.out.print(" )\n");
    }

    public void print() {
        for (int i = 0; i < numRows(); i++) {
            printRow(i);
        }
    }
}