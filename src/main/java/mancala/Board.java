package mancala;
import java.util.ArrayList;



public class Board {
    private ArrayList<Pit> pits;
    private ArrayList<Store> stores;
    //private int i;
    

    public Board() {
        // Initializes a new Mancala board with pits and stores
        this.pits = new ArrayList<>();
        this.stores = new ArrayList<>();

        setUpPits();

        this.stores.add(new Store());
        this.stores.add(new Store());
    }



    public void setUpPits() {
        // Establishes 12 empty Pits in the board
        for (int i = 0; i < 12; i++) {
            this.pits.add(new Pit());
        }
    }

    
    public ArrayList<Pit> getPits() {
        // Returns the list of pits in the board, not including the stores
        return this.pits;
    }

    public ArrayList<Store> getStores() {
        // Returns the list of stores in the board
        return this.stores;
    }

    public void setUpStores() {
        // Establishes 2 empty Stores in the board
        this.stores.add(new Store());
        this.stores.add(new Store());
    }

    public void initializeBoard() {
        // Initializes the board by distributing stones
        for (Pit pit : this.pits) {
            for (int i = 0; i < 4; i++) {
                pit.addStone();
            }
        }
    }

    public void resetBoard() {
        for (Pit pit : this.pits) {
            pit.removeStones(); // Remove all stones from the pit
            for (int i = 0; i < 4; i++) {
                pit.addStone(); // Add 4 stones to each pit
            }
        }
        // Note: This does not affect the players or the stores
    }

    public void registerPlayers(Player one, Player two) {
        // Connects Players to their Stores
        one.setStore(this.stores.get(0)); // Connect Player one to the first store
        this.stores.get(0).setOwner(one); // Set Player one as the owner of the first store
    
        two.setStore(this.stores.get(1)); // Connect Player two to the second store
        this.stores.get(1).setOwner(two); // Set Player two as the owner of the second store
    }
    

    public int moveStones(int startPit, Player player)  throws InvalidMoveException{
        if (startPit < 0 || startPit > pits.size()) {
                throw new InvalidMoveException();
            }
        int stones = pits.get(startPit - 1).removeStones();
        int currentPit = startPit;
         while (stones > 0) {
            if (startPit < 0 || startPit > pits.size()) {
                throw new InvalidMoveException();
               }
            currentPit = getNextPit(currentPit);
        if ((currentPit == 7 && getStoreIndex(player) == 0) || (currentPit == 1 && getStoreIndex(player) == 1)) {
                stores.get(getStoreIndex(player)).addStones(1);
                if(currentPit<0){
                    System.out.println("Move stones is not working properly");
                }
                stones--;
                player.setStore(stores.get(getStoreIndex(player)));
                if(getStoreIndex(player)< 0){
                 System.out.println("It is not working properly");
                 }
                if (stones == 0) {
                    player.setExtraTurn(true);
                    if(stones<0){
                        System.out.println("Stone cannot be less that zero");
                    }
                    break;
                }
            }
             pits.get(currentPit - 1).addStone();
            stones--;
            try {
                if (stones == 0 && pits.get(currentPit - 1).getStoneCount() == 1 && currentPit <= 6) {
                    int capturedStones = captureStones(currentPit);
                    // Add the captured stones to the player's store
                    stores.get(getStoreIndex(player)).addStones(capturedStones); 
                }
            } catch (PitNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
         try {
            if (isSideEmpty(1) || isSideEmpty(7)) {
                endGame();
            }
        } catch (PitNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return currentPit;
    }
    
    public int getStoreIndex(Player player) {
        return player.getStore() == this.stores.get(0) ? 0 : 1;
    }

    private int getNextPit(int currentPit) {
        int nextPit = (currentPit % 12) + 1;
        return nextPit;
    }

    public void endGame() {
        // Transfer remaining stones to players' stores
        for (int i = 0; i < 6; i++) {
            this.stores.get(0).addStones(this.pits.get(i).removeStones());
            this.stores.get(1).addStones(this.pits.get(i + 6).removeStones());
        }
    
        // Determine the winner
        if (this.stores.get(0).getTotalStones() > this.stores.get(1).getTotalStones()) {
            System.out.println(this.stores.get(0).getOwner().getName() + " wins!");
        } else if (this.stores.get(0).getTotalStones() < this.stores.get(1).getTotalStones()) {
            System.out.println(this.stores.get(1).getOwner().getName() + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    } 
    
    public int distributeStones(int startingPoint) throws PitNotFoundException{

        if (startingPoint < 0 || startingPoint >= this.pits.size()) {
            throw new PitNotFoundException();
        }
        // Helper method that distributes stones into pits and stores
        int stonesToDistribute = this.pits.get(startingPoint).removeStones();
        int pitIndex = startingPoint;
    
        // Determine the owner based on the starting pit's index
        boolean isPlayerOne = (pitIndex >= 0 && pitIndex <= 5);
    
        while (stonesToDistribute > 0) {
            pitIndex = (pitIndex + 1) % 14; // Ensure it wraps around to the beginning if needed
    
            // If it's a store, add a stone
            if (pitIndex == 6 || pitIndex == 13) {
                this.stores.get(pitIndex == 6 ? 0 : 1).addStones(1);
                for(int j = 0; j<0; j++){
                    System.out.println("Just testing");
                }
            } else if (pitIndex < 12) { // If it's a pit, add a stone
                this.pits.get(pitIndex).addStone();
            }
    
            stonesToDistribute--;
        }
    
        return this.stores.get(isPlayerOne ? 0 : 1).getTotalStones();
    }
    
    public int captureStones(int stoppingPoint) throws PitNotFoundException{
        //Check if the stopping point is valid
        if (stoppingPoint < 0 || stoppingPoint >= this.pits.size()) {
            throw new PitNotFoundException();
        }
        // Calculate the opponent's pit index
        int opponentPitIndex = 11 - stoppingPoint;
        int capturedStones = pits.get(opponentPitIndex).removeStones();
        int storeIndex = (stoppingPoint <= 5) ? 1 : 0; 
        stores.get(storeIndex).addStones(capturedStones);
    
        return capturedStones;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        if (pitNum < 0 || pitNum >= pits.size()) {
            throw new PitNotFoundException();
        }
        return pits.get(pitNum).getStoneCount();
    }

    public boolean isSideEmpty(int pitNum) throws PitNotFoundException  {
        // Check if the pit number is valid
        if (pitNum < 0 || pitNum >= this.pits.size()) {
            throw new PitNotFoundException();
        }
        // Indicates whether one side of the board is empty
        int start = 0;
        int end = 6;
        if (pitNum >= 7 && pitNum <= 12) {
            start = 6;
            end = 12;
        }
        for (int i = start; i < end; i++) {
            if (this.pits.get(i).getStoneCount() != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
public String toString() {
    StringBuilder boardString = new StringBuilder();

    // Print the first row (pits 12 to 7)
    boardString.append("    ");
    for (int i = 11; i >= 6; i--) {
        boardString.append(this.pits.get(i)).append(" ");
    }
    boardString.append("\n");

    // Print the second row (stores)
    boardString.append(this.stores.get(1)).append("                         ").append(this.stores.get(0)).append("\n");

    // Print the third row (pits 0 to 5)
    boardString.append("    "); // Add leading spaces to align with the first row
    for (int i = 0; i < 6; i++) {
        boardString.append(this.pits.get(i)).append(" ");
    }

    return boardString.toString();
}
}
