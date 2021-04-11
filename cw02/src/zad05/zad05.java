package zad05;

import java.util.ArrayList;
import java.util.List;

public class zad05 {
    public static void main(String[] args) {
        List<Produkt01> list01 = new ArrayList<>();
        list01.add(new Produkt01(100));
        list01.add(new Produkt01(1000));
        list01.add(new Produkt01(10000));
        list01.add(new Produkt01(100000));
        list01.add(new Produkt01(1000000));
        list01.add(new Produkt01(10000000));

        for (Produkt01 p : list01) {
            if (p.sprawdz(10000)) {
                System.out.println("Produkt ma wyższą cene od podanej warości.");
            } else {
                System.out.println("Produkt ma niższą, bądź równą od podanej wartości.");
            }
        }

        int prog01 = 100000;
        int suma01 = 0;
        for (Produkt01 p : list01) {
            if (p.sprawdz(10000)) {
                suma01 += p.getCena();
            }
        }
        if (suma01 > prog01) {
            System.out.println("Suma cen produktów większa od progu.");
        }

        List<Produkt02> list02 = new ArrayList<>();
        list02.add(new Produkt02(100));
        list02.add(new Produkt02(1000));
        list02.add(new Produkt02(10000));
        list02.add(new Produkt02(100000));
        list02.add(new Produkt02(1000000));
        list02.add(new Produkt02(10000000));

        for (Produkt02 p : list02) {
            if (p.cena > 10000) {
                System.out.println("Produkt ma wyższą cene od podanej warości.");
            } else {
                System.out.println("Produkt ma niższą, bądź równą od podanej wartości.");
            }
        }

        int prog02 = 100000;
        int suma02 = 0;
        for (Produkt02 p : list02) {
            if (p.cena > 10000) {
                suma02 += p.cena;
            }
        }
        if (suma02 > prog02) {
            System.out.println("Suma cen produktów większa od progu.");
        }
    }
}
