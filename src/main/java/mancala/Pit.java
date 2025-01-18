package mancala;

public class Pit {
    private int stoneCount;

    public Pit() {
        // Initializes a new pit
        this.stoneCount = 0;
    }

    public int getStoneCount() {
        // Gets the number of stones in the pit
        return this.stoneCount;
    }

    public void addStone() {
        // Adds a stone to the pit
        this.stoneCount++;
    }

    public int removeStones() {
        // Removes and returns the stones from the pit
        int stones = this.stoneCount;
        this.stoneCount = 0;
        return stones;
    }

    @Override
    public String toString() {
        return String.format("[%s]", "" + this.getStoneCount());
    }
}