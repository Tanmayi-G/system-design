package api;

import boards.TicTacToeBoard;
import game.*;

public class AIEngine {

    public Move suggestMove(Board board, Player computer){
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(ticTacToeBoard.getCell(i,j) == null){
                        return new Move(new Cell(i,j), computer);
                    }
                }
            }
            throw new IllegalStateException();
        }else{
            throw new IllegalArgumentException();
        }
    }
}
