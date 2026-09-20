package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

public class RuleEngine {
    public GameState checkGameState(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            String firstCharacter = "-";

            // check rows
            boolean rowComplete = false;
            for(int i=0;i<3;i++){
                firstCharacter = ticTacToeBoard.getCellSymbol(i, 0);
                if(firstCharacter == null){
                    rowComplete = false;
                    continue;
                }
                rowComplete = true;
                for(int j=1;j<3;j++){
                    if(!firstCharacter.equals(ticTacToeBoard.getCellSymbol(i, j))) {
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
                firstCharacter = ticTacToeBoard.getCellSymbol(0, j);
                if(firstCharacter == null) {
                    colComplete = false;
                    continue;
                }
                colComplete = true;
                for(int i=1;i<3;i++){
                    if(!firstCharacter.equals(ticTacToeBoard.getCellSymbol(i, j))) {
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
            firstCharacter = ticTacToeBoard.getCellSymbol(0, 0);
            if(firstCharacter != null) {
                leftRightDiagComplete = true;
                for(int i=1;i<3;i++){
                    if(!firstCharacter.equals(ticTacToeBoard.getCellSymbol(i, i))) {
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
            firstCharacter = ticTacToeBoard.getCellSymbol(0, 2);
            if(firstCharacter != null) {
                rightLeftDiagComplete = true;
                for(int i=1;i<3;i++){
                    if(!firstCharacter.equals(ticTacToeBoard.getCellSymbol(i, 2-i))) {
                        rightLeftDiagComplete = false;
                        break;
                    }
                }
            }

            if(rightLeftDiagComplete) {
                return new GameState(true, firstCharacter);
            }

            // check for tie
            int count = 0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(ticTacToeBoard.getCellSymbol(i, j) != null){
                        count++;
                    }
                }
            }

            if(count==9){
                return new GameState(true, "-"); //tie
            }else{
                return new GameState(false, "-"); //continue game
            }
        }

        return new GameState(false, "-");
    }

}
