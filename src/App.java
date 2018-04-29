// created by Piotr Wolak

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void start(String player1, String player2) throws IOException {
        Gracz gracz1 = new Gracz(player1);
        Runtime.getRuntime().exec("cls");
        Gracz gracz2 = new Gracz(player2);
        Runtime.getRuntime().exec("cls");
        int turns=0;
        while(gracz1.getHitPoints()!=0 || gracz2.getHitPoints()!=0){
            turns++;
            //todo
            // If a player shoot succeed then let him shot again. over and over again
            // possible mistake - what if the player kills the opponent in 1 turn (doesn't miss at all)
            do {
                gracz1.shot(gracz2);
            }while(gracz1.shot(gracz2));
            do {
                gracz2.shot(gracz2);
            }while(gracz2.shot(gracz1));


        }

    }
// todo
    // this funcion is supposed to return a path to highscore in case of exception. When exception appears i have to print out
    //the path
    public static void printHighScore() throws IOException{
        try (final BufferedReader br = new BufferedReader(new FileReader("highscore.txt"))) {

            String text;

            while ((text=br.readLine())!= null) {
                System.out.println(text);
                text = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something terrible happened with highscore");
        }
    }

    // todo
    // this funcion is supposed to return a path to instruction in case of exception. When exception appears i have to print out
    //the path
    public static void showInstruction() throws IOException {

        try (final BufferedReader br = new BufferedReader(new FileReader("instrukcja.txt"))) {

            String text;

            while ((text=br.readLine())!= null) {
                System.out.println(text);
                text = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find instruction");
        }
    }

    public static void showMenu() {
        System.out.println("1 - zacznij nową grę");
        System.out.println("2 - wyświetl instrukcję");
        System.out.println("3 - wyświetl highscore");
        System.out.println("4 - wyjdź");
    }
    public static void menu(){
        Scanner input = new Scanner(System.in);
        int wybor;
        showMenu();
        wybor = input.nextInt();
        switch (wybor) {
            case 1:

                System.out.println("Wprowadz imie pierwszego gracza");
                String p1 = input.nextLine();
                System.out.println("Wprowadz imie pierwszego gracza");
                String p2 = input.nextLine();
                //todo error handling
                try {
                    start(p1, p2);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Something wrong happened");
                }
                break;
            case 2:
                try {
                    showInstruction();
                } catch (IOException e) {
                    System.out.println("Could not find Instruction");
                }
                break;
            case 3:
                try {
                    printHighScore();
                } catch (IOException e) {
                    System.out.println("Could not find Highscore");
                }
                break;
            case 4: System.exit(0); break;
            default:
                System.out.println("You haven't chose any legal option, please try again");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String choice = new String();
        // wyswietlanie instrukcji
        System.out.println("Witaj w grze UBOOT, czy chcesz wyswietlic instrukcje ? (TAK/NIE)");
        choice = input.nextLine();
        choice = choice.toUpperCase();
        if (choice.equals("TAK")) {
            try {
                showInstruction();
            } catch (IOException e) {
                System.out.println("cos poszlo nie tak przy probie odczytu instrukcji");
                e.printStackTrace();
            }
        }


        // utworzenie graczy i wywolanie konstruktorow (konstruktor umieszcza rowniez statki


    }
}