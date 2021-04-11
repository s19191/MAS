package zad06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class zad06csv {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Jan", "Kwasowski", "s19191", 1998, 3, 6));
        list.add(new Student("Adam", "Marian", "s20000", 2000, 1, 1));
        list.add(new Student("Ala", "Kowalska", "s18000", 1995, 4, 7));
        list.add(new Student("Anna", "Maria", "s21922", 2001, 2, 3));
        list.add(new Student("Roman", "Janusz", "s18999", 1997, 3, 5));

        try {
            PrintWriter pw = new PrintWriter(new File("src/zad06/csvStudents.csv"));
            list.forEach(student -> pw.println(student + ","));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Stream<String> lines = Files.lines(Paths.get("src/zad06/csvStudents.csv"), Charset.defaultCharset());
            lines.forEachOrdered(System.out::println);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
