/* This program is free software: you can redistribute it and/or modify
# * it under the terms of the GNU General Public License as published by
# * the Free Software Foundation, either version 3 of the License, or
# * (at your option) any later version.
# *
# * This program is distributed in the hope that it will be useful,
# * but WITHOUT ANY WARRANTY; without even the implied warranty of
# * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# * GNU General Public License for more details.
# *
# * You should have received a copy of the GNU General Public License
# * along with this program.  If not, see <http://www.gnu.org/licenses/>.
#
#   @author Robert Bieber (robertbieber@posteo.de)
# */
package chocolate;

import java.util.Scanner;

public class Chocolate {
    static int x, y, numberOfPlayers, size;
    static char[][] board;
    static boolean gameOngoing = true;
    static String currentPlayer = "Player 2";
    static char horizontalOrVertical;

    //Lets the players enter the size of the board
    public static void enterSize() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x size of board (between 1 and 10, only int)");
        x = sc.nextInt();
        //Player entered a wrong value for x
        if (x > 10 || x < 1) {
            System.out.println("Oh boy, what did I just say...");
            enterSize();
        }
        System.out.println("Enter y size of board (between 1 and 10, only int)");
        y = sc.nextInt();
        //Player entered a wrong value for y
        if (y > 10 || y < 1) {
            System.out.println("Oh boy, what did I just say...");
            enterSize();
        }
    }

    //Sets the board to the size entered by the users
    public static void initializeBoard() {
        board = new char[x][y];
        for (int row = 0; row < y; row++) {
            for (int col = 0; col < x; col++) {
                board[row][col] = 'o';
            }
        }
        //The field in the top left corner gets the soap
        board[0][0] = '*';
    }

    //How many players are there?
    public static void numberOfPlayers() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of players, must be 1 or 2");
        numberOfPlayers = sc.nextInt();
        //Players entered a wrong number of players
        if (!(numberOfPlayers == 1 || numberOfPlayers == 2)) {
            System.out.println("I said: MUST BE 1 OR 2");
            numberOfPlayers();
        }
    }

    //Does the player want to remove a row or column?
    public static void horizontalOrVertical() {
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Break off a horizontal or vertical piece? Enter h or v!");
            horizontalOrVertical = sc.next(".").charAt(0);
            //We want that h or v!
        } while (!(horizontalOrVertical == 'h') && !(horizontalOrVertical == 'v'));
    }

    //How big is the piece which the player wants to remove?
    public static void sizeOfPiece() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the piece of chocolate you want to remove");
        size = sc.nextInt();
        //Piece is too big
        if (horizontalOrVertical == 'h' && size >= y) {
            System.out.println("You might want to consider not trying to break of that much");
            System.out.println("The maximum allowed size is: " + (y - 1));
            sizeOfPiece();
        } else if (horizontalOrVertical == 'v' && size >= x) {
            System.out.println("You might want to consider not trying to break of that much");
            System.out.println("The maximum allowed size is: " + (x - 1));
            sizeOfPiece();
        }
    }

    //Prints the current state of the board to the terminal
    public static void printBoard() {
        for (int row = 0; row < y; row++) {
            for (int column = 0; column < x; column++) {
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    //Is the game still going on?
    public static void game() {
        while (gameOngoing) {
            //Let the next player make a move
            if (x > 1 || y > 1) {
                play();
            } else {
                //Game has ended, print the winning player
                gameOngoing = false;
                System.out.println(currentPlayer + " wins");
            }
        }
    }

    //Removes a line
    public static void removeHorizontal() {
        for (int a = size; a > 0; a--) {
            y--;
        }
        printBoard();
    }

    //Removes a column
    public static void removeVertical() {
        for (int a = size; a > 0; a--) {
            x--;
        }
        printBoard();
    }

    public static void play() {
        //Checks which player gets the next turn
        if (currentPlayer.equals("Player 1")) {
            currentPlayer = "Player 2";
            System.out.println("Your turn, player 2");
        } else if (currentPlayer.equals("Player 2")) {
            currentPlayer = "Player 1";
            System.out.println("Your turn, player 1");
        }

        //Lets the player choose which parts to remove
        horizontalOrVertical();
        sizeOfPiece();

        //Calls the related functions
        if (horizontalOrVertical == 'h') {
            removeHorizontal();
        } else if (horizontalOrVertical == 'v') {
            removeVertical();
        }
        //Gotta check whether the game has ended
        game();
    }

    public static void main(String[] args) {
        //Initial setup
        enterSize();
        initializeBoard();
        numberOfPlayers();
        printBoard();
        //Actually starts the game
        game();
    }
}
