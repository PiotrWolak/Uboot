// created by Piotr Wolak


import java.util.Scanner;

public class Gracz {
    boolean[][][] plansza1;
    private static int id = 0;

    public Gracz() {

        id++;
        this.plansza1 = new boolean[10][10][5];

        for (int i = 2; i < 6; i++) {
            System.out.println("Gracz " + id);
            this.umiescStatek(i);
        }
    }


    public void umiescStatek(int n) {

        int x, y, z, k;
        System.out.println("Rozmieszczasz lódź podwodną o dlugosci: " + n + ", wybierz glebokosc");
        Scanner input = new Scanner(System.in);
        z=10;
        while (z > 5 || z < 0) {
            z = input.nextInt();
        }

        System.out.println("rozmieszczasz na glebokosci" + n);
        System.out.println(" wpisz pozycje startowa (X-enter-Y)");
        x = input.nextInt();
        y = input.nextInt();
        System.out.println("podaj kierunek rozmieszczenia");
        printKierunki();
        k = input.nextInt();
        if (sprawdzCzySieDa(x, y, z, n, k)) {

        }

    }

    // int K to umowny kierunek z zakresu (1-4). Kierunki analogicznie do zegara
    // 1 - 12 godzina (y++)
    // 2 - 3  godzina (x++)
    // 3 - 6  godzina (y--)
    // 4 - 9  godzina (x--)
    public boolean sprawdzCzySieDa(int x, int y, int z, int n, int k) {
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
    public void printKierunki() {
        System.out.println("" +
                "(1) - 12 godzina (y++)\n" +
                "(2) - 3  godzina (x++)\n" +
                "(3) - 6  godzina (y--)\n" +
                "(4) - 9  godzina (x--)");
    }

    public void ustawLodz(int x, int y, int z, int n, int k) {

        if (k == 1) {
            for (int w = 1; w <= n; w++) {
               plansza1[x][y+w][z]= true;
            }
        }
        else if (k == 2) {
            for (int j = 1; j <= n; j++) {
                plansza1[x + j][y][z]=true;
            }
        } else if (k == 3) {
            for (int j = 1; j <= n; j++) {
                plansza1[x][y-j][z] = true;
            }
        } else if (k == 4) {
            for (int j = 1; j <= n; j++) {
                plansza1[x - j][y][z] = true;
            }
        } else {
            System.out.println("cos poszlo nie tak ustawstatek");
        }
    }

    public boolean sprawdzTablice(int x, int y, int z) {
        if (plansza1[x][y][z]) {
            return true;
        }
            return false;

    }
}

