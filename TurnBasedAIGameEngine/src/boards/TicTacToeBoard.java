package boards;

import game.Board;
import game.Cell;

public class TicTacToeBoard extends Board {
    String[][] cells = new String[3][3];

    public String getCell(int row, int col){
        return cells[row][col];
    }

    public void setCell(String symbol, Cell cell){
        cells[cell.getRow()][cell.getCol()] = symbol;
    }
}
