package zad04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class zad04 {
    public static void main(String[] args) {
        program(4);
    }

    static void program(int howManyLinesToShow) {
        try {
            Stream<String> lines = Files.lines(Paths.get("src/zad04/text"));
            //long howManyLines = lines.count();
            for (int i = 0; i < howManyLinesToShow; i++) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
