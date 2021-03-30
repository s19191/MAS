package zad04;

import java.util.ArrayList;
import java.util.List;

public class zad04 {
    public static void main(String[] args) {
        List<Silnik> list = new ArrayList<>();
        list.add(new Benzyna(1000));
        list.add(new Disel(1000));

        for (Silnik s : list) {
            System.out.println("Wydajność silnika " + s.jakiRodzaj() + ": " + s.wyliczWydajnosc());
        }
    }
}
