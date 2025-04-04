package src;

public class ArrayMatrix {

    private final Fraction[][] matrix;
    private final int m;
    private final int n;

    public ArrayMatrix(Fraction[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;

        this.matrix = new Fraction[m][n];

        for (int i = 0; i < m; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, n);
        }
    }

    public int numRows() {return m;}

    public int numColumns() {return n;}

    public int numEntries() {return m * n;}

    public Fraction[][] getValues() {
        Fraction[][] temp = new Fraction[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(matrix[i], 0, temp[i], 0, n);
        }
        return temp;
    }


    public Fraction[] getRow(int rowNum) {
        Fraction[] temp = new Fraction[m];
        System.arraycopy(temp, 0, matrix[rowNum], 0, n);
        return temp;
    }

    public Fraction[] getRowScaledUp(int rowNum, Fraction scalar) {
        Fraction[] temp = new Fraction[m];
        for (int i = 0; i < n; i++) {
            temp[i] = Fraction.multiply(matrix[rowNum][i], scalar);
        }
        return temp;
    }

    public Fraction[] getRowScaledUp(int rowNum, int scalar) {
        return getRowScaledUp(rowNum, new Fraction(scalar));
    }

    public Fraction[] getRowScaledDown(int rowNum, Fraction scalar) {
        Fraction[] temp = new Fraction[m];
        for (int i = 0; i < n; i++) {
            temp[i] = Fraction.divide(matrix[rowNum][i], scalar);
        }
        return temp;
    }

    public Fraction[] getRowScaledDown(int rowNum, int scalar) {
        return getRowScaledDown(rowNum, new Fraction(scalar));
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

        System.arraycopy(row, 0, matrix[rowIndex], 0, n);
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
        Fraction[] temp = new Fraction[n];

        System.arraycopy(matrix[indexRowA], 0, temp, 0, n);
        System.arraycopy(matrix[indexRowB], 0, matrix[indexRowA], 0, n);
        System.arraycopy(temp, 0, matrix[indexRowB], 0, n);
    }



    public void addRow(int indexRowChanged, Fraction[] rowToAdd) {
        for (int i = 0; i < numColumns(); i++) {
            matrix[indexRowChanged][i].add(rowToAdd[i]);
        }
    }

    public void addRow(int indexRowChanged, int indexRowUnchanged) {
        for (int i = 0; i < numColumns(); i++) {
            matrix[indexRowChanged][i].add(matrix[indexRowUnchanged][i]);
        }
    }

    public void subtractRow(int indexRowChanged, Fraction[] rowToAdd) {
        for (int i = 0; i < numColumns(); i++) {
            matrix[indexRowChanged][i].subtract(rowToAdd[i]);
        }
    }

    public void subtractRow(int indexRowChanged, int indexRowUnchanged) {
        for (int i = 0; i < numColumns(); i++) {
            matrix[indexRowChanged][i].subtract(matrix[indexRowUnchanged][i]);
        }
    }



    public void scaleUpRow(int indexRow, Fraction scalar) {
        for (int i = 0; i < numColumns(); i++) {
            matrix[indexRow][i].multiply(scalar);
        }
    }

    public void scaleUpRow(int indexRow, int scalar) {
        scaleUpRow(indexRow, new Fraction(scalar));
    }


    public void scaleDownRow(int indexRow, Fraction scalar) {
        scaleUpRow(indexRow, scalar.getMulInverse());
    }

    public void scaleDownRow(int indexRow, int scalar) {
        scaleDownRow(indexRow, (new Fraction(scalar)).getMulInverse());
    }

    /*


    public void normaliseRow(int rowIndex, int normalColumnIndex) {
        if (rowIndex >= numRows()) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (normalColumnIndex >= numColumns()) {
            throw new IllegalArgumentException("Column index out of bounds");
        }

        multiplyRow(rowIndex, getEntry(rowIndex, normalColumnIndex).getMulInverse());
    }
    public Fraction[] getNormalisedRow(int rowIndex, int normalColumnIndex) {
        if (rowIndex >= numRows()) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (normalColumnIndex >= numColumns()) {
            throw new IllegalArgumentException("Column index out of bounds");
        }

        Fraction normalScalar = getEntry(rowIndex, normalColumnIndex).getMulInverse();
        Fraction[] normalisedRow = new Fraction[numColumns()];
        for (int i = 0; i < numColumns(); i++) {
            normalisedRow[i] = Fraction.multiply(getEntry(rowIndex, i), normalScalar);
        }
        return normalisedRow;
    }

    public void clearColumn(int rowToUse, int scalarColumn) {
        if (rowToUse >= numRows()) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (scalarColumn >= numColumns()) {
            throw new IllegalArgumentException("Column index out of bounds");
        }

        normaliseRow(rowToUse, scalarColumn);

        for (int i = 0; i < numRows(); i++) {
            if (i != rowToUse && !getEntry(i, scalarColumn).isZero()) {
                Fraction scalar = getEntry(i, scalarColumn).getAddInverse();
                Fraction[] scalarRow = getMultipliedRow(rowToUse, scalar);
                Fraction[] summedRows = addRows(getRow(i), scalarRow);
                setRow(i, summedRows);
            }
        }
    }
    */

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
