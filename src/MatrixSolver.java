package src;

public class MatrixSolver {

    public void normaliseRow(Matrix m, int rowIndex, int normalColumnIndex) {
        if (rowIndex >= m.numRows()) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (normalColumnIndex >= m.numColumns()) {
            throw new IllegalArgumentException("Column index out of bounds");
        }

        m.scaleDownRow(rowIndex, m.getEntry(rowIndex, normalColumnIndex));
    }

    public Fraction[] getNormalisedRow(Matrix m, int rowIndex, int normalColumnIndex) {
        if (rowIndex >= m.numRows()) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (normalColumnIndex >= m.numColumns()) {
            throw new IllegalArgumentException("Column index out of bounds");
        }

        Fraction[] normalisedRow = new Fraction[m.numColumns()];
        for (int i = 0; i < m.numColumns(); i++) {
            normalisedRow[i] = Fraction.divide(m.getEntry(rowIndex, i), m.getEntry(rowIndex, normalColumnIndex));
        }
        return normalisedRow;
    }

    public void clearColumn(Matrix m, int rowToUse, int scalarColumn) {
        if (rowToUse >= m.numRows) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else if (scalarColumn >= m.numCols) {
            throw new IllegalArgumentException("Column index out of bounds");
        }

        normaliseRow(m, rowToUse, scalarColumn);

        for (int i = 0; i < m.numRows; i++) {
            if (i != rowToUse && !m.getEntry(rowToUse, scalarColumn).isZero()) {
                m.subtractRowScaled(i, rowToUse, m.getEntry(i, scalarColumn));
            }
        }
    }

    public void bringToRREF(Matrix m) {
        int col = 0;
        int row = 0;

        while (col < m.numCols && row < m.numRows) {
            int row_to_use = -1;

            if (m.getEntry(row, col).isZero()) {
                for (int i = row + 1; i < m.numRows; i++) {
                    if (!m.getEntry(i, col).isZero()) {
                        row_to_use = i;
                        break;
                    }
                }
            } else {
                row_to_use = row;
            }

            if (row_to_use != -1) {
                m.swapRows(row, row_to_use);
                m.clearColumn(row, col);
                row++;
            }
            col++;
        }
    }

    public static void main(String[] args) {

        MatrixSolver ms = new MatrixSolver();

        //int precision = 3;

        Matrix m1 = new Matrix(new int[][]{
                {2, 0, -2, -6},
                {1, 0, 5, 1},
                {3, 0, 15, 3}});


        m1.print();
        System.out.println();

        ms.bringToRREF(m1);

        m1.printAsDecimal();

        Fraction f = new Fraction(3.35f, 2);
        System.out.println(f);
    }
}