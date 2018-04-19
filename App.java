// created by Piotr Wolak

import java.io.*;
import java.util.Scanner;

public class App {
    public static void pokazInstrukcje() throws IOException {
        FileReader fr = null;
        try {
            fr = new FileReader("instrukcja.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono instrukcji :(");
        }

        BufferedReader br = new BufferedReader(fr);
        String text = null;


        text = br.readLine();


        while(text!=null){
            System.out.println(text);
            text = br.readLine();
        }
        br.close();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String choice = new String();
        // wyswietlanie instrukcji
        System.out.println("Witaj w grze UBOOT, czy chcesz wyswietlic instrukcje ? (TAK/NIE)");
        choice = input.nextLine();
        // todo
        // dla TAK wyswietla, dla "tak" juz nie
        choice.toUpperCase();
        if(choice.equals(new String("TAK"))){
            try {
                pokazInstrukcje();
            } catch (IOException e) {
                System.out.println("cos poszlo nie tak przy probie odczytu instrukcji");
                e.printStackTrace();
            }
        }
        // utworzenie graczy i wywolanie konstruktorow (konstruktor umieszcza rowniez statki
        Gracz gracz1 = new Gracz();
        Gracz gracz2 = new Gracz();

    }
}
