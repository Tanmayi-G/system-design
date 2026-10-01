package game;

public class GameInfo {
    private boolean isOver;
    private String winner;
    private Player player;
    private boolean hasFork;

    public GameInfo(GameState gameState, Player player, boolean hasFork) {
        this.isOver = gameState.isOver();
        this.winner = gameState.getWinner();
        this.player = player;
        this.hasFork = hasFork;
    }
}
