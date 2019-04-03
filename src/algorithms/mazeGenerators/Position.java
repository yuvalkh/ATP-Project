package algorithms.mazeGenerators;

public class Position {
    int RowIndex;
    int ColumnIndex;

    public Position(int rowIndex, int columnIndex) {
        RowIndex = rowIndex;
        ColumnIndex = columnIndex;
    }

    public int getRowIndex() {
        return RowIndex;
    }

    public void setRowIndex(int rowIndex) {
        RowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return ColumnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        ColumnIndex = columnIndex;
    }

    @Override
    public String toString() {
        return "RowIndex=" + RowIndex +
                ", ColumnIndex=" + ColumnIndex;
    }
}
