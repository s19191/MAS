package cw03;

import java.util.ArrayList;
import java.util.List;

public class zad03 {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Men());
        list.add(new Woman());
        for (Person p : list) {
            System.out.println(p.sayYourGender());
        }
    }
}
