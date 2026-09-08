package game;

public class Player {
    private String playerSymbol;

    public Player(String playerSymbol){
        this.playerSymbol = playerSymbol;
    }

    public String getSymbol(){
        return this.playerSymbol;
    }
}
