package mancala;

public class Player {
    private String playerName;
    private Store stores;
    private boolean secondTurn;
    private Player currentPlayer;

    public Player() {
        // Initializes a new player
        this.stores = new Store();
    }

    public Player(String name) {
        // Initializes a new player with a name
        this.playerName = name;
        this.stores = new Store();
    }

    public String getName() {
        // Gets the name of the player
        return this.playerName;
    }

    public void setName(String name) {
        // Sets the player's name
        this.playerName = name;
    }

    public Store getStore() {
        // Gets the player's store where they collect stones
        return this.stores;
    }

    public void setStore(Store store) {
        // Sets the player's store
        this.stores = store;
    }

    public int getStoreCount() {
        // Gets the count of the number of stones in the player's store
        return this.stores.getTotalStones();
    }

    @Override
public String toString() {
    String storeString = (stores == null) ? "No store" : stores.toString();
    return "Player: " + getName() + "\n" + storeString;
}

public void setExtraTurn(boolean extraTurn) {
    this.secondTurn = extraTurn;
}


public Player getCurrentPlayer() {
    return this.currentPlayer;
}

public void setCurrentPlayer(Player player) {
    this.currentPlayer = player;
}

}

