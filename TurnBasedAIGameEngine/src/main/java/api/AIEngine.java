package api;

import boards.TicTacToeBoard;
import game.*;

public class AIEngine {

    public Move suggestMove(Board board, Player computer){
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            Move suggestion;
            int threshold = 4;
            if(countMoves(ticTacToeBoard) < threshold) {
                suggestion = getBasicMove(computer, ticTacToeBoard);
            }else{
                suggestion = getSmartMove(computer, ticTacToeBoard);
            }
            if (suggestion != null) return suggestion;
            throw new IllegalStateException();
        }else{
            throw new IllegalArgumentException();
        }
    }

    private Move getBasicMove(Player computer, TicTacToeBoard board) {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getCellSymbol(i,j) == null){
                    if(board.getCellSymbol(i,j) == null){
                        return new Move(new Cell(i, j), computer);
                    }
                }
            }
        }
        return null;
    }

    private Move getSmartMove(Player computer, TicTacToeBoard board) {
        /* smart move logic:
           1. Can AI win with this move?
                - Make the winning move
           2. Will opponent win with their next move?
                - Block opponent from winning
         */
        RuleEngine ruleEngine = new RuleEngine();

        // victorious move
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getCellSymbol(i,j) == null){
                    Move move = new Move(new Cell(i,j), computer);
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.play(move);
                    // If the AI is playing a move, it means the opponent hasn't won yet (else game would be over).
                    // So with this move, the AI either wins or draws a tie - either way game is over.
                    boolean isOver = ruleEngine.checkGameState(boardCopy).isOver();
                    if(isOver) return move;
                }
            }
        }

        // defensive move
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getCellSymbol(i,j) == null){
                    // If opponent plays a move and wins, play that move as computer

                    // Player mockOpponent = new Player(computer.getSymbol().equals("X") ? "O" : "X");
                    Player mockOpponent = computer.flip();
                    Move move = new Move(new Cell(i,j), mockOpponent);
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.play(move);

                    boolean isOver = ruleEngine.checkGameState(boardCopy).isOver();
                    if(isOver) return new Move(new Cell(i,j), computer);
                }
            }
        }

        return getBasicMove(computer,board);
    }

    private int countMoves(TicTacToeBoard board) {
        int count = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getCellSymbol(i,j) != null) count++;
            }
        }
        return count;
    }
}
