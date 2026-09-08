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

    public void play(Board board, Player player, Move move){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            ticTacToeBoard.setCell(player.getSymbol(), move.getCell());
        }else{
            throw new IllegalArgumentException();
        }
    }

    public GameState checkGameState(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            String firstCharacter = "-";

            // check rows
            boolean rowComplete = false;
            for(int i=0;i<3;i++){
                firstCharacter = ticTacToeBoard.getCell(i, 0);
                if(firstCharacter == null){
                    rowComplete = false;
                    continue;
                }
                rowComplete = true;
                for(int j=1;j<3;j++){
                    if(!firstCharacter.equals(ticTacToeBoard.getCell(i, j))) {
                        rowComplete = false;
                        break;
                    }
                }
                if(rowComplete) break;
            }

            if(rowComplete) {
                return new GameState(true, firstCharacter);
            }

            // check cols
            boolean colComplete = false;
            for(int j=0;j<3;j++){
                firstCharacter = ticTacToeBoard.getCell(0, j);
                if(firstCharacter == null) {
                    colComplete = false;
                    continue;
                }
                colComplete = true;
                for(int i=1;i<3;i++){
                    if(!firstCharacter.equals(ticTacToeBoard.getCell(i, j))) {
                        colComplete = false;
                        break;
                    }
                }
                if(colComplete) break;
            }

            if(colComplete) {
                return new GameState(true, firstCharacter);
            }

            // check left-right diagonal
            boolean leftRightDiagComplete = false;
            firstCharacter = ticTacToeBoard.getCell(0, 0);
            if(firstCharacter != null) {
                leftRightDiagComplete = true;
                for(int i=1;i<3;i++){
                    if(!firstCharacter.equals(ticTacToeBoard.getCell(i, i))) {
                        leftRightDiagComplete = false;
                        break;
                    }
                }
            }

            if(leftRightDiagComplete) {
                return new GameState(true, firstCharacter);
            }

            // check right-left diagonal
            boolean rightLeftDiagComplete = false;
            firstCharacter = ticTacToeBoard.getCell(0, 2);
            if(firstCharacter != null) {
                rightLeftDiagComplete = true;
                for(int i=1;i<3;i++){
                    if(!firstCharacter.equals(ticTacToeBoard.getCell(i, 2-i))) {
                        rightLeftDiagComplete = false;
                        break;
                    }
                }
            }

            if(rightLeftDiagComplete) {
                return new GameState(true, firstCharacter);
            }

            int count = 0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(ticTacToeBoard.getCell(i, j) != null){
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

    public Move suggestMove(Board board, Player player){
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(ticTacToeBoard.getCell(i,j) == null){
                        return new Move(new Cell(i,j));
                    }
                }
            }
            throw new IllegalStateException();
        }else{
            throw new IllegalArgumentException();
        }
    }
}
