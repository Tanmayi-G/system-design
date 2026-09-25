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
            GameState rowWin = outerTraversal((i, j) -> ticTacToeBoard.getCellSymbol(i, j));
            if (rowWin.isOver()) return rowWin;

            // check cols
            GameState colWin = outerTraversal((i, j) -> ticTacToeBoard.getCellSymbol(j, i));
            if (colWin.isOver()) return colWin;

            // check left-right diagonal
            GameState diagWin = innerTraversal((i) -> ticTacToeBoard.getCellSymbol(i, i));
            if (diagWin.isOver()) return diagWin;

            // check right-left diagonal
            GameState revDiagWin = innerTraversal((i) -> ticTacToeBoard.getCellSymbol(i, 2-i));
            if (revDiagWin.isOver()) return revDiagWin;

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

//    removing this single-line function, using it directly
//    // checks whether any diagonal is complete according to the Function
//    private GameState checkDiagLine(Function<Integer,String> next) {
//        return traverse(next);
//    }

    // checks whether any line is complete according to the BiFunction
    private GameState outerTraversal(BiFunction<Integer,Integer,String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            final int finalI = i;

            GameState traversal = innerTraversal((j) -> next.apply(finalI,j));
            if (traversal.isOver()){
                result = traversal;
                break;
            }
        }
        return result;
    }

    private GameState innerTraversal(Function<Integer,String> traversal) {
        GameState result = new GameState(false, "-");
        boolean isLineComplete = true;
        for (int j = 0; j < 3; j++) {
            if (traversal.apply(j) == null || !traversal.apply(0).equals(traversal.apply(j))) {
                isLineComplete = false;
                break;
            }
        }
        if (isLineComplete) {
            result = new GameState(true, traversal.apply(0));
        }
        return result;
    }
}
