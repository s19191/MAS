package zad04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class zad04 {
    public static void main(String[] args) {
        program(4);
        program(20);
    }

    static void program(int howManyLinesToShow) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/zad04/text"));
            long howManyLines = lines.size();
            if (howManyLinesToShow >= howManyLines) {
                System.out.println(lines);
            } else {
                int counter = 0;
                Random random = new Random();
                List<String> choosen = new ArrayList<>();
                while (howManyLinesToShow > 0) {
                    if ((int)random.nextInt(2) % 2 == 0) {
                        if (!choosen.contains(lines.get(counter))) {
                            choosen.add(lines.get(counter));
                            howManyLinesToShow--;
                        }
                    }
                    counter++;
                    if (counter == howManyLines) {
                        counter = 0;
                    }
                }
                System.out.println(choosen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}