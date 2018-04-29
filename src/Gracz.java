// created by Piotr Wolak


import java.util.Scanner;

public class Gracz {
    boolean[][][] ArrayOfSubmarines;
    private static int id = 0;
    private final String name;
    private int hitPoints = 0;


    public Gracz(String name) {
        this.name = name;
        id++;
        this.ArrayOfSubmarines = new boolean[10][10][5];
        for (int i = 2; i < 6; i++) {
            System.out.println("Gracz " + id);
            this.setUpSubmarine(i);
        }
    }


    // this method ain't safe. Player can enter 15-15-15, evem though it's out of array
    public boolean shot(Gracz player){
        System.out.println("enter the coordinates (X-Y-Z)");
        Scanner shot = new Scanner(System.in);
        int x,y,z;
        x=shot.nextInt();
        y=shot.nextInt();
        z=shot.nextInt();
        if(player.ArrayOfSubmarines[x][y][z]){
            player.ArrayOfSubmarines[x][y][z]= false;
            return true;
        }
        return false;
    }


    public void wipeOut() {
        for (int z = 0; z < 5; z++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    ArrayOfSubmarines[x][y][z] = false;
                }
            }
        }
    }


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
            System.out.println("rozmieszczasz na glebokosci" + shipLength);
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
    // 2 - 3  godzina (x++)
    // 3 - 6  godzina (y--)
    // 4 - 9  godzina (x--)
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

    // just a meaningless comment
    public void printDirections() {
        System.out.println("" +
                "(1) - 12 godzina (y++)\n" +
                "(2) - 3  godzina (x++)\n" +
                "(3) - 6  godzina (y--)\n" +
                "(4) - 9  godzina (x--)");
    }

    public void placeSubmarine(int x, int y, int z, int n, int k) {
//todo
        // string builder odpierdala jakies jaja
        StringBuilder sb = new StringBuilder("Umieszczono łódź na pozycjach\n X,Y,Z ");
        if (k == 1) {
            for (int j = 0; j < n; j++) {
                hitPoints++;
                ArrayOfSubmarines[x][y + j][z] = true;
                System.out.println(sb + "\n");
                sb.append(x)
                        .append(y + j)
                        .append(z);


            }
        } else if (k == 2) {

            for (int w = 0; w < n; w++) {
                hitPoints++;
                ArrayOfSubmarines[x + w][y][z] = true;
                sb.append(x + w)
                        .append(y)
                        .append(z);
                System.out.println(sb + "\n");


            }
        } else if (k == 3) {
            for (int j = 0; j < n; j++) {
                hitPoints++;
                ArrayOfSubmarines[x][y - j][z] = true;
                sb.append(x)
                        .append(y - j)
                        .append(z);
                System.out.println(sb + "\n");
            }
        } else if (k == 4) {
            for (int j = 0; j < n; j++) {
                hitPoints++;
                ArrayOfSubmarines[x - j][y][z] = true;
                sb.append(x - j)
                        .append(y)
                        .append(z);
                System.out.println("\n");
            }
        } else {
            System.out.println("cos poszlo nie tak ustawstatek");
        }

        System.out.println(sb.toString());
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
}


