package game;

public class Move {
    private Cell cell;

    public Move(Cell cell){
        this.cell = cell;
    }

    public Cell getCell(){
        return this.cell;
    }
}
