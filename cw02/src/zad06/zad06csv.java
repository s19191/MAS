package zad06;

import java.util.ArrayList;
import java.util.List;

public class zad06csv {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Jan", "Kwasowski", "s19191", 1998, 3, 6));
        list.add(new Student("Adam", "Marian", "s20000", 2000, 1, 1));
        list.add(new Student("Ala", "Kowalska", "s18000", 1995, 4, 7));
        list.add(new Student("Anna", "Maria", "s21922", 2001, 2, 3));
        list.add(new Student("Roman", "Janusz", "s18999", 1997, 3, 5));
    }
}
