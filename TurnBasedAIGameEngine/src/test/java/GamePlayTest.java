import api.AIEngine;
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
    AIEngine aiEngine;
    RuleEngine ruleEngine;
    Player opponent;
    Player computer;

    @BeforeEach
    public void setup(){
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
        opponent = new Player("X");
        computer = new Player("O");
    }

    private void playGame(Board board, int[][] moves) {
        int next = 0;

        // make moves in a loop
        while(!ruleEngine.checkGameState(board).isOver()){
            System.out.println("Make your move!");
            int row = moves[next][0];
            int col = moves[next][1];
            next++;

            // user move
            Move oppMove = new Move(new Cell(row,col), opponent);
            gameEngine.play(board, oppMove);
            System.out.println(board);

            if(ruleEngine.checkGameState(board).isOver()){
                break;
            }

            // computer move
            System.out.println("Computer playing...");
            Move compMove = aiEngine.suggestMove(board, computer);
            gameEngine.play(board, compMove);
            System.out.println(board);
        }
    }

    @Test
    public void checkForRowWin(){
        Board board = gameEngine.start("TicTacToe");

        int[][] moves = new int[][]{{1,0},{1,1},{1,2}};
        playGame(board, moves);

        Assertions.assertTrue(ruleEngine.checkGameState(board).isOver());
        Assertions.assertEquals("X", ruleEngine.checkGameState(board).getWinner());
    }

    @Test
    public void checkForColWin(){
        Board board = gameEngine.start("TicTacToe");

        int[][] moves = new int[][]{{0,0},{0,1},{0,2}};
        playGame(board, moves);

        Assertions.assertTrue(ruleEngine.checkGameState(board).isOver());
        Assertions.assertEquals("X", ruleEngine.checkGameState(board).getWinner());
    }

    @Test
    public void checkForLeftRightDiagWin(){
        Board board = gameEngine.start("TicTacToe");

        int[][] moves = new int[][]{{0,0},{1,1},{2,2}};
        playGame(board, moves);

        Assertions.assertTrue(ruleEngine.checkGameState(board).isOver());
        Assertions.assertEquals("X", ruleEngine.checkGameState(board).getWinner());
    }

    @Test
    public void checkForRightLeftDiagWin(){
        Board board = gameEngine.start("TicTacToe");

        int[][] moves = new int[][]{{0,2},{1,1},{2,0}};
        playGame(board, moves);

        Assertions.assertTrue(ruleEngine.checkGameState(board).isOver());
        Assertions.assertEquals("X", ruleEngine.checkGameState(board).getWinner());
    }

    @Test
    public void checkForComputerWin(){
        Board board = gameEngine.start("TicTacToe");

        int[][] moves = new int[][]{{1,0},{1,1},{2,0}};
        playGame(board, moves);

        Assertions.assertTrue(ruleEngine.checkGameState(board).isOver());
        Assertions.assertEquals("O", ruleEngine.checkGameState(board).getWinner());
    }
}
