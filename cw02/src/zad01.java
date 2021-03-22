import java.util.*;

public class zad01 {
    public static void main(String[] args) {
        int min = 0;
        int max = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj minimalną wartość zakresu z którego będziemy losować 100 liczb:");
        try {
            min = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Nie prawidłowo wpisane dane! System za chwilę zakończy swoje działanie");
            System.exit(-1);
        }
        System.out.println("Podaj maksymalną wartość zakresu z którego będziemy losować 100 liczb:");
        try {
            max = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Nie prawidłowo wpisane dane! System za chwilę zakończy swoje działanie");
            System.exit(-1);
        }
        List<Integer> rn = new ArrayList<Integer>();
        Random rg = new Random();
        for (int i = 0; i < 100; i++) {
            rn.add(rg.nextInt(max) + min);
        }
        System.out.println(rn);
        Integer rMax = rn.stream().max(Integer::compareTo).get();
        System.out.println("Maksymalna wartość to: " + rMax);
        Integer rMin = rn.stream().min(Integer::compareTo).get();
        System.out.println("Minimalna wartość to: " + rMin);
    }
}
