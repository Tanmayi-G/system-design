package game;

public class GameInfoBuilder {
    private boolean isOver;
    private String winner;
    private Player player;
    private boolean hasFork;
    private int noOfMoves;

    public GameInfoBuilder isOver(boolean isOver) {
        this.isOver = isOver;
        return this;
    }

    public GameInfoBuilder winner(String winner) {
        this.winner = winner;
        return this;
    }

    public GameInfoBuilder player(Player player) {
        this.player = player;
        return this;
    }

    public GameInfoBuilder hasFork(boolean hasFork) {
        this.hasFork = hasFork;
        return this;
    }

    public GameInfoBuilder noOfMoves(int noOfMoves) {
        this.noOfMoves = noOfMoves;
        return this;
    }

    public GameInfo build() {
        return new GameInfo(this.isOver, this.winner, this.hasFork, this.player, this.noOfMoves);
    }
}
