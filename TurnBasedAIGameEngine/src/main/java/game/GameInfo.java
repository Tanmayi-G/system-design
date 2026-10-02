package game;

public class GameInfo {
    private boolean isOver;
    private String winner;
    private Player player;
    private boolean hasFork;
    private int noOfmoves;

    public GameInfo(boolean isOver, String winner, boolean hasFork, Player player, int noOfmoves) {
        this.isOver = isOver;
        this.winner = winner;
        this.player = player;
        this.hasFork = hasFork;
        this.noOfmoves = noOfmoves;
    }
}

