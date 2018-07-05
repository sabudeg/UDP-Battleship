package battleship;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    int konum;

    static Game gameobject = new Game();

    char [] boardServer = new char[10];
    char [] boardClient = new char[10];

    char [] clientsOpponent = new char[10];
    char [] serversOpponent = new char[10];


    static void welcome(){
        System.out.println("        --- BATTLESHIP GAME ---");
        System.out.println("Place your ships with the numbers between 1-10");
    }

    void fillBoardServer() throws InputMismatchException {
        for (int i=1; i<4; i++) {
            try{
                System.out.print("Place "+i+". ship: ");
                Scanner input = new Scanner(System.in);
                int target = input.nextInt();
                if(target <=10 && target > 0) {
                    if (gameobject.boardServer[target - 1] == 'S') {
                        System.out.println("This place is already filled.");
                        i--;
                    } else gameobject.boardServer[target - 1] = 'S';
                    continue;
                }
                else System.out.println("Please enter valid number."); i--;
            }
            catch (InputMismatchException ime){
                System.out.println("Please enter valid number."); i--;
            }
        }
        printBoard(gameobject.boardServer);
    }

    void fillBoardClient() throws InputMismatchException {
        for (int i=1; i<4; i++) {
            try{
                System.out.print("Place "+i+". ship: ");
                Scanner input = new Scanner(System.in);
                int target = input.nextInt();
                if(target <=10 && target > 0) {
                    if (gameobject.boardClient[target - 1] == 'S') {
                        System.out.println("This place is already filled.");
                        i--;
                    } else gameobject.boardClient[target - 1] = 'S';
                    continue;
                }
                else System.out.println("Please enter valid number."); i--;
            }
            catch (InputMismatchException ime){
                System.out.println("Please enter valid number."); i--;
            }
        }
        printBoard(gameobject.boardClient);
    }

    void hitClientsBoard(int x){
        konum = x-1;
        if (gameobject.boardClient[konum] == 'S'){
            gameobject.boardClient[konum] = 'X';
            System.out.println("Server hit: " + x);
        }

        else System.out.println("Server missed.");

        Client.isOver();

        printBoard(gameobject.boardClient);
    }

    void hitClientsOpponent(int x){
        konum = x-1;
        if (gameobject.boardServer[konum] == 'X'){
            gameobject.clientsOpponent[konum] = 'X';
        }
    }

    void hitServersBoard(int x)
    {
        konum = x-1;
        if (gameobject.boardServer[konum] == 'S') {
            gameobject.boardServer[konum] = 'X';
            System.out.println("Client hit: " + x);
        }
        else System.out.println("Client missed.");

        Server.isOver();

        printBoard(gameobject.boardServer);


    }

    void printBoard(char[] board){
        System.out.print("Your Board:     ");
        for (char index:board) {
            System.out.print("-" +index+ "- " );
        }
        System.out.println();
    }

    void printOpponent(char[] board){

            System.out.print("Opponent Board: ");
            for (char ind : board) {
                System.out.print("-" + ind + "- ");
            }
        System.out.println();
        }

}


