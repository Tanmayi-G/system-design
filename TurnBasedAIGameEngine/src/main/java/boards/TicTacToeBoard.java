package boards;

import game.Board;
import game.Cell;
import game.Move;

public class TicTacToeBoard implements Board {
    String[][] cells = new String[3][3];

    public String getCellSymbol(int row, int col){
        return cells[row][col];
    }

    public void setCell(String symbol, Cell cell){
        // check for illegal moves
        if (cells[cell.getRow()][cell.getCol()] == null){
            cells[cell.getRow()][cell.getCol()] = symbol;
        }else{

            throw new IllegalArgumentException();
        }
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

    @Override
    public void play(Move move){
        setCell(move.getPlayer().getSymbol(), move.getCell());
    }

    // Prototype design pattern
    @Override
    public TicTacToeBoard copy(){
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.arraycopy(
                        this.cells[i],       // source row
                        0,                   // source starting index
                        ticTacToeBoard.cells[i],       // destination row
                        0,                   // destination starting index
                        3                    // number of elements
                );
            }
        }
        return ticTacToeBoard;
    }
}
