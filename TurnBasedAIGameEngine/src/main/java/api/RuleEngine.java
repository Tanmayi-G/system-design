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

            // check rows
            GameState rowWin = checkLine((i, j) -> ticTacToeBoard.getCellSymbol(i, j));
            if (rowWin != null) return rowWin;

            // check cols
            GameState colWin = checkLine((i, j) -> ticTacToeBoard.getCellSymbol(j, i));
            if (colWin != null) return colWin;

            // check left-right diagonal
            GameState diagWin = checkDiagLine((i) -> ticTacToeBoard.getCellSymbol(i, i));
            if (diagWin != null) return diagWin;

            // check right-left diagonal
            GameState revDiagWin = checkDiagLine((i) -> ticTacToeBoard.getCellSymbol(i, 2-i));
            if (revDiagWin != null) return revDiagWin;

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

    // checks whether any diagonal is complete according to the Function
    private GameState checkDiagLine(Function<Integer,String> next) {
        boolean diagComplete = true;
        for(int i=0;i<3;i++){
            if(next.apply(i) == null || !next.apply(0).equals(next.apply(i))) {
                diagComplete = false;
                break;
            }
        }

        if(diagComplete) {
            return new GameState(true, next.apply(0));
        }
        return null;
    }

    // checks whether any line is complete according to the BiFunction
    private GameState checkLine(BiFunction<Integer,Integer,String> next) {
        for (int i = 0; i < 3; i++) {
            boolean isLineComplete = true;
            for (int j = 0; j < 3; j++) {
                if (next.apply(i,j) == null || !next.apply(i,0).equals(next.apply(i, j))) {
                    isLineComplete = false;
                    break;
                }
            }
            if (isLineComplete) {
                return new GameState(true, next.apply(i,0));
            }
        }
        return null;
    }
}
