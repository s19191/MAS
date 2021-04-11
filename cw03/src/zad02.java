import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class zad02 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < 100; i++) {
            list.add(random.nextInt(100));
        }
        list.stream().filter(i->i%2==1).forEach(System.out::println);
    }
}
