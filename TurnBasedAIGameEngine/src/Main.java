import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");
        Player opponent = new Player("X");
        Player computer = new Player("O");

        // make moves in a loop
        while(!ruleEngine.checkGameState(board).isOver()){
            System.out.println("Make your move!");
            int row = sc.nextInt();
            int col = sc.nextInt();

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
        System.out.println("Game over! Winner is: " + ruleEngine.checkGameState(board).getWinner());
        System.out.println(board);
    }
}
