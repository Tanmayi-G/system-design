package game;

public class Cell {
    int row, col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }
}
