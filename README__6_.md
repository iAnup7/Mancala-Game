# Project Title

Mancala Board Game

## Description

Mancala, also known as "منقلة manqalah" in Arabic, encompasses a variety of two-player strategy board games that involve turn-based gameplay. Typically played with small stones, beans, or seeds, the game utilizes rows of holes or pits on the ground, a board, or another playing surface. The primary goal is generally to seize all or a specified set of the adversary's pieces.

The game's origins trace back to periods preceding the 3rd century, with indications suggesting its existence in Ancient Egypt. As one of the earliest known games that continues to enjoy widespread popularity today, Mancala holds a significant place in the history of gaming.

Embark on a journey through time with this captivating Mancala board game – an ancient masterpiece that has stood the test of centuries! Immerse yourself in the strategic thrill of this timeless classic that originated in Africa and the Middle East.

## Getting Started

Welcome to the Mancala board. This game two players to play. The rules for the game are as follows:

* Setup
 

  The Mancala 'board' is made up of two rows of six holes, or pits, each. If you don't have a Mancala board handy, an empty egg carton can work. Next, four pieces -- marbles or stones -- are placed in each of the 12 holes. The color of the pieces is irrelevant.
  Each player has a 'store' to the right side of the Mancala board. (Cereal bowls work well for this purpose.)

 

* Play
 

  The game begins with one player picking up all of the pieces in any one of the holes on his side.Moving counter-clockwise, the player deposits one of the stones in each hole until the stones run out.

 

      1. If you run into your own store, deposit one piece in it. If you run into your opponent's store, skip it.

      2. If the last piece you drop is in your own store, you get a free turn.

      3. If the last piece you drop is in an empty hole on your side, you capture that piece and any pieces in the hole directly opposite.

      4. Always place all captured pieces in your store.

 

* Winning the game
 

  The game ends when all six spaces on one side of the Mancala board are empty. The player who still has pieces on his side of the board when the game ends captures all of those pieces. Count all the pieces in each store. The winner is the player with the most pieces.

### Dependencies

* Gradle Software is required for building and running the program.
* This program builds and runs on all modern systems

### Executing program

* Type the gradle build command on the terminal to build the program
```
$ gradle build
```
* Expected output 
```
BUILD SUCCESSFUL in 30s
3 actionable tasks: 3 executed
user@linux-04:~/GP3$
```
* Now, inorder to get the command to run the program, type the following command
```
$ gradle echo
```
* Expected output
```
user@linux-01:~/GP3$ gradle echo

> Task :echo
To run the program from jar:
java -jar build/libs/Mancala.jar
To run the program from class files:
java -cp build/classes/java/main ui.TextUI
```
* To run the program, enter the following command
```
java -cp build/classes/java/main ui.TextUI
```
## Limitations

* There might be minor bugs for certain values entered by the user which could could disrupt the flow of the game.
* If it does occur, re-run the program and it should work correctly  


## Author Information

Name: Anup Doddaningappanvar

Email ID: adoddani@uoguelph.ca

## Development History

Differences between the AI generated Code and My code

* The major difference between the programs is the fact that my code is completely functional whereas the AI generated code builds effectively, but the correctness is debatable
* The toString() methods in the AI generated code are have very poor readability that makes it hard to play the game.
* For more detailed comparision, refer CodeReview.md in aiSolution directory

## Acknowledgments

* Game rules - https://www.officialgamerules.org/mancala 
* AI used (Generative AI task) - https://openai.com/chatgpt
* General mancala game information - https://en.wikipedia.org/wiki/Mancala