package api;

import boards.TicTacToeBoard;
import game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    // maps each board type to the list of rules applicable to it
    Map<String, List<Rule<TicTacToeBoard>>> ruleMap = new HashMap<>();

    public RuleEngine() {
        ruleMap.put(TicTacToeBoard.class.getName(), new ArrayList<>());
        List<Rule<TicTacToeBoard>> ticTacToeBoardRules = ruleMap.get(TicTacToeBoard.class.getName());

        // Rule 1: check rows
        ticTacToeBoardRules.add(new Rule<>((board) -> outerTraversal((i, j) -> board.getCellSymbol(i, j))));

        // Rule 2: check cols
        ticTacToeBoardRules.add(new Rule<>((board) -> outerTraversal((i, j) -> board.getCellSymbol(i, j))));

        // Rule 3: check left-right diagonal
        ticTacToeBoardRules.add(new Rule<>((board) -> innerTraversal((i) -> board.getCellSymbol(i, i))));

        // Rule 4: check right-left diagonal
        ticTacToeBoardRules.add(new Rule<>((board) -> innerTraversal((i) -> board.getCellSymbol(i, 2-i))));

        // Rule 5: check for tie
        ticTacToeBoardRules.add(new Rule<>((board) -> {
            int count = 0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board.getCellSymbol(i, j) != null){
                        count++;
                    }
                }
            }
            if(count==9){
                return new GameState(true, "-"); //tie
            }
            return new GameState(false, "-");
        }));
    }

    public GameState checkGameState(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            List<Rule<TicTacToeBoard>> rules = ruleMap.get(TicTacToeBoard.class.getName());
            for(Rule<TicTacToeBoard> rule : rules) {
                GameState gameState = rule.condition.apply(ticTacToeBoard);
                if(gameState.isOver()){
                    return gameState;
                }
            }
            return new GameState(false, "-"); //continue game
        }else{
            throw new IllegalArgumentException();
        }
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

    public GameInfo getGameInfo(Board board){
        if(board instanceof TicTacToeBoard){
            GameState gameState = checkGameState(board);
            /*
            * X-O
            * -O-
            * X-X
            */
            String[] players = new String[]{"X", "O"};
            for(int index = 0; index < 2; index++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Board board1 = board.copy();
                        Player player = new Player(players[index]);
                        // make move as X
                        board1.play(new Move(new Cell(i, j), player));
                        // after making a move as X, if O is still winning, then it's a fork for O
                        boolean forkDetected = false;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                Board board2 = board1.copy();
                                board2.play(new Move(new Cell(k, l), player.flip()));
                                if (checkGameState(board2).getWinner().equals(player.flip().getSymbol())) {
                                    forkDetected = true;
                                    break;
                                }
                            }
                            if (forkDetected) break;
                        }
                        if (forkDetected) {
                            return new GameInfoBuilder()
                                    .isOver(gameState.isOver())
                                    .winner(gameState.getWinner())
                                    .hasFork(true)
                                    .player(player.flip())
                                    .build();
                        }
                    }
                }
            }
            return new GameInfoBuilder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .build();
        }else{
            throw new IllegalArgumentException();
        }
    }
}
