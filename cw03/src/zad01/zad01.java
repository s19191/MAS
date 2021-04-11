package zad01;

public class zad01 {
    public static void main(String[] args) {
        MyMap<Integer, String> map01 = new MyMap<>(01, "Janek");
        System.out.println(map01);

        MyMap<String, Double> map02 = new MyMap<>("Bułka", 24.5);
        System.out.println(map02);
    }
}
