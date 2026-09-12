package game;

public interface Board {
    void play(Move move);

    Board copy();
}
