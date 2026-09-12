import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;
    Player opponent;
    Player computer;

    @BeforeEach
    public void setup(){
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
        opponent = new Player("X");
        computer = new Player("O");
    }

    private void playGame(Board board, int[][] opponentMoves, int[][] computerMoves) {
        int next = 0;

        // make moves in a loop
        while(!ruleEngine.checkGameState(board).isOver()){
            System.out.println("Make your move!");
            int row = opponentMoves[next][0];
            int col = opponentMoves[next][1];

            // user move
            Move oppMove = new Move(new Cell(row,col), opponent);
            gameEngine.play(board, oppMove);
            System.out.println(board);

            if(ruleEngine.checkGameState(board).isOver()){
                break;
            }

            // computer move
            System.out.println("Computer playing...");
            int sRow = computerMoves[next][0];
            int sCol = computerMoves[next][1];
            Move compMove = new Move(new Cell(sRow,sCol), computer);
            gameEngine.play(board, compMove);
            System.out.println(board);

            next++;
        }
    }

    @Test
    public void checkForRowWin(){
        Board board = gameEngine.start("TicTacToe");

        int[][] opponentMoves = new int[][]{{1,0},{1,1},{1,2}};
        int[][] computerMoves = new int[][]{{0,0},{0,1},{0,2}};
        playGame(board, opponentMoves, computerMoves);

        Assertions.assertTrue(ruleEngine.checkGameState(board).isOver());
        Assertions.assertEquals("X", ruleEngine.checkGameState(board).getWinner());
    }

    @Test
    public void checkForColWin(){
        Board board = gameEngine.start("TicTacToe");

        int[][] opponentMoves = new int[][]{{0,0},{1,0},{2,0}};
        int[][] computerMoves = new int[][]{{0,1},{0,2},{1,1}};
        playGame(board, opponentMoves, computerMoves);

        Assertions.assertTrue(ruleEngine.checkGameState(board).isOver());
        Assertions.assertEquals("X", ruleEngine.checkGameState(board).getWinner());
    }

    @Test
    public void checkForLeftRightDiagWin(){
        Board board = gameEngine.start("TicTacToe");

        int[][] opponentMoves = new int[][]{{0,0},{1,1},{2,2}};
        int[][] computerMoves = new int[][]{{0,1},{0,2},{1,0}};
        playGame(board, opponentMoves, computerMoves);

        Assertions.assertTrue(ruleEngine.checkGameState(board).isOver());
        Assertions.assertEquals("X", ruleEngine.checkGameState(board).getWinner());
    }

    @Test
    public void checkForRightLeftDiagWin(){
        Board board = gameEngine.start("TicTacToe");

        int[][] opponentMoves = new int[][]{{0,2},{1,1},{2,0}};
        int[][] computerMoves = new int[][]{{0,0},{0,1},{1,0}};
        playGame(board, opponentMoves, computerMoves);

        Assertions.assertTrue(ruleEngine.checkGameState(board).isOver());
        Assertions.assertEquals("X", ruleEngine.checkGameState(board).getWinner());
    }

    @Test
    public void checkForComputerWin(){
        Board board = gameEngine.start("TicTacToe");

        int[][] opponentMoves = new int[][]{{1,0},{1,1},{2,0}};
        int[][] computerMoves = new int[][]{{0,0},{0,1},{0,2}};
        playGame(board, opponentMoves, computerMoves);

        Assertions.assertTrue(ruleEngine.checkGameState(board).isOver());
        Assertions.assertEquals("O", ruleEngine.checkGameState(board).getWinner());
    }
}
