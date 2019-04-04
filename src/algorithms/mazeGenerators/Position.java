package algorithms.mazeGenerators;


public class Position {
    int RowIndex;
    int ColumnIndex;

    /**
     *
     * @param rowIndex number of row
     * @param columnIndex number of column
     */
    public Position(int rowIndex, int columnIndex) {
        if(rowIndex>=0 && columnIndex>=0){
            RowIndex = rowIndex;
            ColumnIndex = columnIndex;
        }
    }

    /**
     *
     * @return the index of the row
     */
    public int getRowIndex() {
        return RowIndex;
    }

    /**
     *
     * @param rowIndex number of row
     */
    public void setRowIndex(int rowIndex) {
        if(rowIndex >= 0)
            RowIndex = rowIndex;
    }

    /**
     *
     * @return the index of the column
     */
    public int getColumnIndex() {
        return ColumnIndex;
    }

    /**
     *
     * @param columnIndex number of column
     */
    public void setColumnIndex(int columnIndex) {
        if(columnIndex>=0)
            ColumnIndex = columnIndex;
    }

    /**
     *
     * @return a string presentation of Position
     */
    @Override
    public String toString() {
        return "RowIndex=" + RowIndex +
                ", ColumnIndex=" + ColumnIndex;
    }
}
