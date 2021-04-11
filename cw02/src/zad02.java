import java.util.*;

public class zad02 {
    public static void main(String[] args) {
        System.out.println("TABLE");
        int[] tab01 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] tab01Tmp01 = new int[tab01.length + 1];
        for (int i = 0; i < tab01.length; i++) {
            tab01Tmp01[i] = tab01[i];
        }
        tab01Tmp01[tab01.length] = 11;
        for (int i = 0; i < tab01Tmp01.length; i++) {
            System.out.print(tab01Tmp01[i] + " ");
        }
        System.out.println();
        int requirement = 0;
        int howManyToChange = 0;
        for (int i = 0; i < tab01Tmp01.length; i++) {
            if (tab01Tmp01[i] == requirement) {
                howManyToChange++;
                System.out.println(tab01Tmp01[i]);
            }
        }
        int[] tab01Tmp02 = new int[tab01Tmp01.length - howManyToChange];
        int counter = 0;
        for (int i = 0; i < tab01Tmp01.length; i++) {
            if (tab01Tmp01[i] != requirement) {
                tab01Tmp02[counter] = tab01Tmp01[i];
                counter++;
            }
        }
        for (int i = 0; i < tab01Tmp02.length; i++) {
            System.out.print(tab01Tmp02[i] + " ");
        }
        System.out.println();
        System.out.println("**********************************************************************************************");
        System.out.println("LIST");
        List<Integer> list01 = new ArrayList<>();
        list01.add(0);
        list01.add(1);
        list01.add(2);
        Integer toAdd = 3;
        list01.add(toAdd);
        System.out.println(list01);
        list01.remove(2);
        System.out.println(list01.get(1));
        list01.remove(toAdd);
        System.out.println(list01);
        System.out.println("**********************************************************************************************");
        System.out.println("SET");
        Set<Integer> set01 = new HashSet<>();
        set01.add(0);
        set01.add(1);
        set01.add(2);
        set01.add(14);
        set01.add(toAdd);
        set01.addAll(list01);
        System.out.println(set01);
        set01.remove(1);
        set01.remove(toAdd);
        System.out.println(set01);
        set01.add(toAdd);
        Integer foundOne = set01.stream().filter(e -> e == toAdd).findFirst().get();
        System.out.println(foundOne);
        System.out.println("**********************************************************************************************");
        System.out.println("MAP");
        Map<Integer, Integer> map01 = new HashMap<>();
        map01.put(1, 1);
        map01.put(2, 2);
        map01.put(4, 4);
        map01.put(toAdd, toAdd);
        map01.put(5, 2);
        System.out.println(map01);
        map01.remove(1);
        System.out.println(map01);
        map01.values().removeIf(value -> value == 2);
        System.out.println(map01);
        System.out.println(map01.get(3));
        System.out.println(map01.values().stream().filter(value -> value == 4).findFirst().get());
    }
}
