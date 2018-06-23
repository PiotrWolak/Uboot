// created by Piotr Wolak


import java.util.Random;
import java.util.Scanner;

public class Gracz {
    // those are colors used in method showBoard() to distinguish free space and occupied space
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    private static int id = 1;

    private final String name;

    private int hitPoints = 0;
    boolean[][][] ArrayOfSubmarines;

    public Gracz(String name) {
        this.name = name;
        System.out.println("Player"+id +" your name is " + this.name);
        this.ArrayOfSubmarines = new boolean[10][10][5];
        // after constructor is executed players will place theirs submarines
        //how many submarines ought to be placed = numbers of iteration
        // i = ship lenght
        for (int i = 2; i < 3; i++) {
            System.out.println(name);
            this.setUpSubmarine(i);
        }
        showBoard();
        id++;
    }

    // it can't be static cause i'm invoking nonstatic shot. have to deal with it later
    public void fight(Gracz p2){
        Random random = new Random();
        System.out.println(this.name + " what would you like to do ? \n 1-shoot, 2-scan");
        Scanner decision = new Scanner(System.in);
        int choice;
        choice = decision.nextInt();
        switch(choice){
            case 1: shot(p2); break;
            case 2: showBoard(p2, random.nextInt(5)); break;
            default: shot(p2);
        }

    }


    // this method ain't safe. Player can enter 15-15-15, evem though it's out of array
    public boolean shot(Gracz player){

        System.out.println(this.name + " shooting...");
        System.out.println("enter the coordinates (X-Y-Z)");
        Scanner shot = new Scanner(System.in);
        int x,y,z;
        x=shot.nextInt();
        y=shot.nextInt();
        z=shot.nextInt();
        if(player.ArrayOfSubmarines[x][y][z]){
            System.out.println(RED + "Enemy submarine has been damaged !" + RESET);
            player.ArrayOfSubmarines[x][y][z]= false;
            player.hitPoints-=1;
            return true;
        }
        return false;
    }



    // this method is redundant for now

    public void wipeOut() {
        for (int z = 0; z < 5; z++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    ArrayOfSubmarines[x][y][z] = false;
                }
            }
        }
    }
    public void showBoard() {
        for (int z = 0; z < 5; z++) {
            System.out.println();
            System.out.print("~~~~~~~~~~~~~~~~"+z+"~~~~~~~~~~~~~~~~");
            for (int x = 0; x < 10; x++) {
                System.out.println();
                for (int y = 0; y < 10; y++) {
                    if(!ArrayOfSubmarines[x][y][z]) System.out.print("0");
                    else if(ArrayOfSubmarines[x][y][z]) System.out.print(RED + "X" + RESET);
                }
            }
        }
        System.out.println();
    }

    public void showBoard(Gracz player, int depth) {

        System.out.println();
        System.out.print(RED+"~~~~~~~~~~~~~~~~"+depth+"~~~~~~~~~~~~~~~~"+RESET);
        for (int x = 0; x < 10; x++) {
            System.out.println();
            for (int y = 0; y < 10; y++) {
                if(!player.ArrayOfSubmarines[x][y][depth]) System.out.print("0");
                    // checking condition else if is unnecessary. There's only 2 case, so if arr[][][]!=true, it means it's =true
                    // although i've encountered some problems so i'll keep it that way and deal with it later
                else if(player.ArrayOfSubmarines[x][y][depth]) System.out.print(RED + "X" + RESET);
            }
        }

        System.out.println();
    }


    // this method gets user input, calls function checkIfAllowed(), in case it's legal it calls function placeSubmarine()

    public void setUpSubmarine(int shipLength) {

        int x, y, z, k;
        System.out.println("Rozmieszczasz lódź podwodną o dlugosci: " + shipLength + ", wybierz glebokosc");
        Scanner input = new Scanner(System.in);
        z = 10;
        while (z > 5 || z < 0) {
            z = input.nextInt();
        }

        boolean placeDone = false;

        do {
            System.out.println("rozmieszczasz na glebokosci" + z);
            System.out.println(" wpisz pozycje startowa (X-enter-Y)");
            x = input.nextInt();
            y = input.nextInt();
            System.out.println("podaj kierunek rozmieszczenia");
            printDirections();
            k = input.nextInt();
            if (checkIfAllowed(x, y, z, shipLength, k)) {
                placeSubmarine(x, y, z, shipLength, k);
                placeDone = true;
            } else {
                System.out.println("coś poszło nie tak, spróbuj jeszcze raz");
            }
        }while(!placeDone);

    }

    // int K to umowny kierunek z zakresu (1-4). Kierunki analogicznie do zegara
    // 1 - 12 godzina (y++)

    public boolean checkIfAllowed(int x, int y, int z, int n, int k) {
        if (k > 4 || k < 1) {
            System.out.println("Nieprawidlowy kierunek");
        } else if (x > 10 || x < 0 || y > 10 || y < 0 || z > 5 || z < 0) {
            System.out.println("nieprawidlowe wspolrzedne");
            return false;
        }
        if (k == 1) {
            if (y + n < 10) return true;
        } else if (k == 2) {
            if (x + n < 10) return true;
        } else if (k == 3) {
            if (y - n > 0) return true;
        } else if (k == 4) {
            if (x - n > 0) return true;
        } else {
            return false;
        }
        return false;
    }
    // 4 - 9  godzina (x--)
    // 3 - 6  godzina (y--)

    // just a meaningless comment
    public void printDirections() {
        System.out.println("" +
                "(1) - 12 godzina (y++)\n" +
                "(2) - 3  godzina (x++)\n" +
                "(3) - 6  godzina (y--)\n" +
                "(4) - 9  godzina (x--)");
    }
    // 2 - 3  godzina (x++)
    public void placeSubmarine(int x, int y, int z, int n, int k) {
//todo
        // string builder odpierdala jakies jaja

        if (k == 1) {
            for (int j = 0; j < n; j++) {
                hitPoints++;
                ArrayOfSubmarines[x][y + j][z] = true;
                System.out.println("submarine placed on (" +x +", " +(y+j) +", " +z +") <x,y,z>");

            }
        } else if (k == 2) {

            for (int w = 0; w < n; w++) {
                hitPoints++;
                ArrayOfSubmarines[x + w][y][z] = true;
                System.out.println("submarine placed on (" + (x+w) +", " +y +", " +z +") <x,y,z>");


            }
        } else if (k == 3) {
            for (int j = 0; j < n; j++) {
                hitPoints++;
                ArrayOfSubmarines[x][y - j][z] = true;
                System.out.println("submarine placed on (" + x +", " + (y-j) +", " +z +") <x,y,z>");

            }
        } else if (k == 4) {
            for (int j = 0; j < n; j++) {
                hitPoints++;
                ArrayOfSubmarines[x - j][y][z] = true;
                System.out.println("submarine placed on (" + (x-j) +", " +y +", " +z +") <x,y,z>");

            }
        } else {
            System.out.println("cos poszlo nie tak ustawstatek");
        }


    }
    public boolean checkArray(int x, int y, int z) {
        if (ArrayOfSubmarines[x][y][z]) {
            return true;
        }
        return false;

    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}