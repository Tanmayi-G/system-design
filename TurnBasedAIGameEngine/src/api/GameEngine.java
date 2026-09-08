package api;

import boards.TicTacToeBoard;
import game.*;

public class GameEngine {

    public Board start(String type){
        if(type.equals("TicTacToe")){
            return new TicTacToeBoard();
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void play(Board board, Move move){
        if(board instanceof TicTacToeBoard){
            board.play(move);
        }else{
            throw new IllegalArgumentException();
        }
    }
}
