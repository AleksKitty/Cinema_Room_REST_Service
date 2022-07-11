package cinema.support_classes;

public class Purchase {
    private int row;
    private int column;

    public Purchase() {
        // Constructor for @PostMapping
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
