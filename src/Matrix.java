package src;

public interface Matrix<T extends Scalar<T>> {


    int numRows();
    int numColumns();
    int numEntries();

    T[][] getValues();

    T[] getRow(int rowNum);
    T[] getRowScaled(int rowNum, T scalar);

    T getEntry(int rowNum, int columnNum);


    void setRow(int rowIndex, T[] row);
    void setEntry(int rowIndex, int columnIndex, T entry);
    void setMatrix(T[][] matrix);

    void swapRows(int indexRowA, int indexRowB);

    void addRow(int indexRowChanged, T[] rowToAdd);
    void addRow(int indexRowChanged, int indexRowUnchanged);
    void addRowScaled(int indexRowChanged, T[] rowToAdd, T Scalar);
    void addRowScaled(int indexRowChanged, int indexRowUnchanged, T Scalar);
    void scaleRow(int indexRow, T Scalar);


}
