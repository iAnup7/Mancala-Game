package mancala;

public class Store {
    private Player owner;
    private int totalStones;

    public Store() {
        // Initializes a new store
        this.totalStones = 0;
    }

    public void setOwner(Player player) {
        // Sets the owner of the store
        this.owner = player;
    }

    public Player getOwner() {
        // Gets the owner of the store
        return this.owner;
    }

    public void addStones(int amount) {
        // Adds stones to the store
        this.totalStones += amount;
    }

    public int getTotalStones() {
        // Gets the total number of stones in the store
        return this.totalStones;
    }

    public int emptyStore() {
        // Empties the store and returns the number of stones that were in it
        int stones = this.totalStones;
        this.totalStones = 0;
        return stones;
    }

    @Override
    public String toString() {
        return String.format("[%s]", this.getTotalStones());
    }
}