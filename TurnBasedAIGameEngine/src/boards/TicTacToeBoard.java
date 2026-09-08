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

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            res.append(" ");
            for (int j = 0; j < 3; j++) {
                String cell = cells[i][j];

                if (cell == null) {
                    cell = " ";
                }

                res.append(cell);

                if (j < 2) {
                    res.append(" | ");
                }
            }

            res.append("\n");

            if (i < 2) {
                res.append("---+---+---\n");
            }
        }

        return res.toString();
    }
}
