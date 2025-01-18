package ui;
import mancala.Player;
//import mancala.Store;
//import mancala.Pit;
import mancala.MancalaGame;
import java.util.ArrayList;
import java.util.Scanner;
import mancala.Board;
//import mancala.InvalidMoveException;
import mancala.GameNotOverException;
import mancala.InvalidMoveException;


public class TextUI{
    public static void main(String[] args)  {
    try{
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

       // Create a Mancala game
        MancalaGame mancalaGame = new MancalaGame();
        mancalaGame.setPlayers(player1, player2);

        // Set up the board and register players
        Board board = new Board();
        board.setUpPits();
        board.setUpStores();
        board.initializeBoard();
        board.registerPlayers(player1, player2);

        mancalaGame.setBoard(board);

        // Set the initial player
        mancalaGame.setCurrentPlayer(player1);

        // Play the game until it's over
        while (!mancalaGame.isGameOver()) {
            // Print the current state of the game
            System.out.println(mancalaGame);
    
            // Allow the current player to make a move
            System.out.println("Current player: " + mancalaGame.getCurrentPlayer().getName());
    
            int startPit = getPlayerMove(mancalaGame.getCurrentPlayer(), mancalaGame.getPlayers());
            mancalaGame.move(startPit);
        }

        // Print the final state of the game
        System.out.println("Game Over!");
        System.out.println("Final state: " + mancalaGame);

        // Determine and print the winner
        Player winner = mancalaGame.getWinner();
        if (winner != null) {
            System.out.println("Winner: " + winner.getName());
        } else {
            System.out.println("It's a tie!");
        }
    } catch(GameNotOverException | InvalidMoveException e){
        e.printStackTrace();
    }
    }

    private static int getPlayerMove(Player currentPlayer,  ArrayList<Player> players) {
    Scanner scanner = new Scanner(System.in);

    int startPit = -1;
    int lowerBound = (currentPlayer == players.get(0)) ? 1 : 7;
int upperBound = (currentPlayer == players.get(0)) ? 6 : 12;

while (startPit < lowerBound || startPit > upperBound) {
    System.out.print("Enter the index of the pit to start the move (" + lowerBound + "-" + upperBound + "): ");
    try {
        startPit = scanner.nextInt();
        System.out.println("Entered startPit: " + startPit);
    } catch (Exception e) {
        System.out.println("Invalid input. Please enter a number.");
        scanner.nextLine(); // Consume the invalid input
    }
}

    return startPit;
}
}