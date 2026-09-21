package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    public GameState checkGameState(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            String firstCharacter = "-";

            Function<Integer, String> getRow = (i) -> ticTacToeBoard.getCellSymbol(i, 0);
            Function<Integer, String> getCol = (j) -> ticTacToeBoard.getCellSymbol(0, j);
            Function<Integer, String> firstCharacterSupplier;

            BiFunction<Integer, Integer, String> getNextRow = (i,j) -> ticTacToeBoard.getCellSymbol(i,j);
            BiFunction<Integer, Integer, String> getNextCol = (i,j) -> ticTacToeBoard.getCellSymbol(j,i);

            // check rows
            firstCharacterSupplier = getRow;
            boolean isLineComplete = false;
            for(int i=0;i<3;i++){
                firstCharacter = firstCharacterSupplier.apply(i);
                if(firstCharacter == null){
                    isLineComplete = false;
                    continue;
                }
                isLineComplete = true;
                for(int j=1;j<3;j++){
                    if(!firstCharacter.equals(getNextRow.apply(i,j))) {
                        isLineComplete = false;
                        break;
                    }
                }
                if(isLineComplete){
                    return new GameState(true, firstCharacter);
                }
            }

            // check cols
            firstCharacterSupplier = getCol;
            for(int i=0;i<3;i++){
                firstCharacter = firstCharacterSupplier.apply(i);
                if(firstCharacter == null) {
                    isLineComplete = false;
                    continue;
                }
                isLineComplete = true;
                for(int j=1;j<3;j++){
                    if(!firstCharacter.equals(getNextCol.apply(i,j))) {
                        isLineComplete = false;
                        break;
                    }
                }
                if(isLineComplete) {
                    return new GameState(true, firstCharacter);
                }
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
