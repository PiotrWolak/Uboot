// created by Piotr Wolak


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void start(){
        //   Runtime.getRuntime().exec("cls"); doesn't work on linux
        Scanner name = new Scanner(System.in);
        System.out.println("player"+Gracz.getId() + ": please, enter your name");
        Gracz gracz1 = new Gracz(name.nextLine());
        System.out.println("player"+Gracz.getId() + ": please, enter your name");
        Gracz gracz2 = new Gracz(name.nextLine());
        int turns=0;
        do{
            turns++;
            gracz1.fight(gracz2);
            gracz2.fight(gracz1);
        }
        while(gracz1.getHitPoints()>0 && gracz2.getHitPoints()>0);
        if(gracz1.getHitPoints()>0) System.out.println(Gracz.RED + gracz1.getName() +" WON in " + turns + " turns"+ Gracz.RESET);
        else System.out.println(Gracz.RED + gracz2.getName()+" WON in " + turns + " turns" + Gracz.RESET);


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

        try (final BufferedReader br = new BufferedReader(new FileReader("instruction.txt"))) {

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
        System.out.println("1 - Start the game");
        System.out.println("2 - show instruction");
        System.out.println("3 - highscore (NOT IMPLEMENTED)");
        System.out.println("4 - exit");
    }
    public static void menu(){
        Scanner input = new Scanner(System.in);
        int wybor;
        showMenu();
        wybor = input.nextInt();
        switch (wybor) {
            case 1:

                start();break;
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

//        System.out.println("Witaj w grze UBOOT, czy chcesz wyswietlic instrukcje ? (TAK/NIE)");
//        choice = input.nextLine();
//        choice = choice.toUpperCase();
//        if (choice.equals("TAK")) {
//            try {
//                showInstruction();
//            } catch (IOException e) {
//                System.out.println("cos poszlo nie tak przy probie odczytu instrukcji");
//                e.printStackTrace();
//            }
//        }

        menu();


        // utworzenie graczy i wywolanie konstruktorow (konstruktor umieszcza rowniez statki


    }
}