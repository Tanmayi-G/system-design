package game;

import java.util.function.Function;

public class Rule<T extends Board> {
    public Function<T, GameState> condition;

    public Rule(Function<T, GameState> condition) {
        this.condition = condition;
    }
}
