import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class zad03 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < 100; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println(list.stream().collect(Collectors.summingInt(Integer::intValue)));
        System.out.println(list.stream().mapToInt(Integer::intValue).sum());
        System.out.println(list.stream().mapToInt(Integer::intValue).sum());
    }
}