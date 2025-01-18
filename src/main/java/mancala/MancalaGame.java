package mancala;

import java.util.ArrayList;
import java.util.Arrays;

public class MancalaGame {
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Board board;

    public MancalaGame() {
        this.players = new ArrayList<>();
        this.board = new Board();
    }

    public void setPlayers(Player onePlayer, Player twoPlayer) {
        this.players = new ArrayList<>(Arrays.asList(onePlayer, twoPlayer));
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setBoard(Board theBoard) {
        this.board = theBoard;
    }

    public Board getBoard() {
        return this.board;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        if (pitNum < 1 || pitNum > 6) {
            throw new PitNotFoundException();
        }
        return board.getNumStones(pitNum);
    }

    

    
    public int move(int startPit)  throws InvalidMoveException{

    if (startPit < 0 || startPit >= board.getPits().size() || board.getPits().get(startPit).getStoneCount() == 0){
            throw new InvalidMoveException();
        }
        // Perform the move
       
            int lastPit = this.board.moveStones(startPit, currentPlayer);
         
            
        // If the last stone lands in the player's store, the player gets a free turn
        if (lastPit == 6 || lastPit == 12) {
            return this.currentPlayer.getStore().getTotalStones();
        }

        // If the last stone lands in an empty pit on the player's side, capture the stones
        try {
            if (this.board.getPits().get(lastPit).getStoneCount() == 1) {
                int capturedStones = this.board.captureStones(lastPit);
                this.currentPlayer.getStore().addStones(capturedStones);
            }
        } catch (PitNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Switch the current player
        this.currentPlayer = (this.currentPlayer == this.players.get(0)) ? this.players.get(1) : this.players.get(0);

        // Return the total number of stones in the current player's store
        return this.currentPlayer.getStore().getTotalStones();
    }

    public int getStoreCount(Player player) throws NoSuchPlayerException {
        // Check if the player exists
        if (player == null) {
            throw new NoSuchPlayerException();
        }
    
        return player.getStore().getTotalStones();
    }
    

    public Player getWinner() throws GameNotOverException {

        if (!isGameOver()) {
            throw new GameNotOverException();
        }

        int playerOneStoreCount = this.players.get(0).getStoreCount();
        int playerTwoStoreCount = this.players.get(1).getStoreCount();

        if (playerOneStoreCount > playerTwoStoreCount) {
            return this.players.get(0);
        } else if (playerTwoStoreCount > playerOneStoreCount) {
            return this.players.get(1);
        } else {
            return null; // It's a tie
        }
    }

    public boolean isGameOver() {
        try {
            // Check if either side of the board is empty
            boolean playerOneSideEmpty = this.board.isSideEmpty(6);
            boolean playerTwoSideEmpty = this.board.isSideEmpty(13);
    
            return playerOneSideEmpty || playerTwoSideEmpty;
        } catch (PitNotFoundException e) {
            System.out.println(e.getMessage());
            return false; // or handle the exception in another appropriate way
        }
    }
    

    public void startNewGame() {
        this.board.resetBoard();
    }

    @Override
    public String toString() {
        return "MancalaGame:\n" + getBoard();
    }
}