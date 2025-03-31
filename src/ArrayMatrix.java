package src;

public class ArrayMatrix<T> implements Matrix<T> {

    private Fraction[][] matrix;

    public ArrayMatrix(Fraction[][] matrix) {
        this.matrix = matrix;
    }

    public int numRows() {return matrix.length;}

    public int numColumns() {return matrix[0].length;}

    public int numEntries() {return matrix.length * matrix[0].length;}

    public Fraction[] getRow(int rowNum) {return matrix[rowNum];}

    public Fraction getEntry(int rowNum, int columnNum) {return matrix[rowNum][columnNum];}

    public void setRow(int rowIndex, Fraction[] row) {
        if (row.length != numColumns()) {
            throw new IllegalArgumentException("Invalid row length");
        } else if (rowIndex >= numRows() || rowIndex < 0) {
            throw new IllegalArgumentException("Row index out of bounds");
        }
        matrix[rowIndex] = row;
    }

    public void setEntry(int rowIndex, int columnIndex, Fraction entry) {
        if (rowIndex >= numRows() || rowIndex < 0) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (columnIndex >= numColumns() || columnIndex < 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        matrix[rowIndex][columnIndex] = entry;
    }

    public void setMatrix(Fraction[][] matrix) {
        if (matrix[0].length != numColumns()) {
            throw new IllegalArgumentException("Invalid row length");
        } else if (matrix.length != numRows()) {
            throw new IllegalArgumentException("Invalid column length");
        }
        this.matrix = matrix;
    }

    public static Fraction[] addRows(Fraction[] row1, Fraction[] row2) {
        Fraction[] row = new Fraction[row1.length];

        for (int i = 0; i < row.length; i++) {
            row[i] = Fraction.add(row1[i], row2[i]);
        }
        return row;
    }


    public void multiplyRow(int rowIndex, Fraction scalar) {
        if (rowIndex >= numRows()) {
            throw new IllegalArgumentException("Row index out of range");
        }
        setRow(rowIndex, getMultipliedRow(rowIndex, scalar));
    }

    public Fraction[] getMultipliedRow(int rowIndex, Fraction scalar) {
        if (rowIndex >= numRows()) {
            throw new IllegalArgumentException("Row index out of range");
        }

        Fraction[] row = new Fraction[numColumns()];
        for (int i = 0; i < numColumns(); i++) {
            row[i] = Fraction.multiply(getEntry(rowIndex, i), scalar);
        }
        return row;
    }

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
