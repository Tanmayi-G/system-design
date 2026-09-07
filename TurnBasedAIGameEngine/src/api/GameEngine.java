package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;
import game.Move;
import game.Player;

public class GameEngine {

    public Board start(){
        return new Board();
    }

    public void play(Board board, Player player, Move move){

    }

    public GameState checkGameState(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            String firstCharacter = "-";

            // check rows
            boolean rowComplete = true;
            for(int i=0;i<3;i++){
                rowComplete = true;
                firstCharacter = ticTacToeBoard.cells[i][0];
                for(int j=1;j<3;j++){
                    if(!ticTacToeBoard.cells[i][j].equals(firstCharacter)){
                        rowComplete = false;
                        break;
                    }
                }
                if(rowComplete) break;
            }

            if(rowComplete){
                return new GameState(true, firstCharacter);
            }

            // check cols
            boolean colComplete = true;
            for(int j=0;j<3;j++){
                colComplete = true;
                firstCharacter = ticTacToeBoard.cells[0][j];
                for(int i=1;i<3;i++){
                    if(!ticTacToeBoard.cells[i][j].equals(firstCharacter)){
                        colComplete = false;
                        break;
                    }
                }
                if(colComplete) break;
            }

            if(colComplete){
                return new GameState(true, firstCharacter);
            }

            // check left-right diagonal
            boolean leftRightDiagComplete = true;
            firstCharacter = ticTacToeBoard.cells[0][0];
            for(int i=1;i<3;i++){
                if(!ticTacToeBoard.cells[i][i].equals(firstCharacter)) {
                    leftRightDiagComplete = false;
                    break;
                }
            }

            if(leftRightDiagComplete){
                return new GameState(true, firstCharacter);
            }

            // check right-left diagonal
            boolean rightLeftDiagComplete = true;
            firstCharacter = ticTacToeBoard.cells[0][2];
            for(int i=1;i<3;i++){
                if(!ticTacToeBoard.cells[2-i][2-i].equals(firstCharacter)){
                    rightLeftDiagComplete = false;
                    break;
                }
            }

            if(rightLeftDiagComplete){
                return new GameState(true, firstCharacter);
            }

            int count = 0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(ticTacToeBoard.cells[i][j] != null){
                        count++;
                    }
                }
            }

            if(count==9){
                return new GameState(true, "-");
            }else{
                return new GameState(false, "-");
            }
        }

        return new GameState(false, "-");
    }
}
