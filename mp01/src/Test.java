import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Address address01 = new Address("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Melomanów", 10, 50, "00-712");
        Address address02 = new Address("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Iwicka", 12, "00-735");

        Set<String> organizer01 = new HashSet<>();
        organizer01.add("Jan Kwasowski");
        organizer01.add("Kawiarnia StarDucks");

        Set<String> organizer02 = new HashSet<>();
        organizer02.add("Polskie stowarzyszenie baristów");

        Contest contest01 = new Contest("Konkurs Barista nr.1", 1000, 3000, LocalDateTime.of(2020, 9, 23, 16, 0), address01, organizer01, "https://github.com/s19191", "Super fajny konkurs");
        Contest contest02 = new Contest("Konkurs gminy Warszawa-Mokotów", 500, 1000, LocalDateTime.of(2021, 2, 12, 12, 30), address02, organizer02, "Oficjalny konkurs polskiego stowarzyszenia baristów");
        Contest contest03 = new Contest("Konkurs01", 100, 500, LocalDateTime.of(2021, 1, 30, 10, 15), address02, organizer01, "Opis01");
        Contest contest04 = new Contest("Konkurs02", 1500, 3000, LocalDateTime.of(2021, 2, 22, 10, 15), address02, organizer01, "https://github.com/s19191", "Opis02");
        Contest contest05 = new Contest("Konkurs03", 200, 800, LocalDateTime.of(2021, 4, 16, 9, 0), address01, organizer02, "Opis03");
        Contest contest06 = new Contest("Konkurs04", 10, 50, LocalDateTime.of(2021, 3, 23, 18, 0), address01, organizer02, "https://github.com/s19191", "Opis04");

        System.out.println("Ekstensja");
        Contest.showExtent();
        System.out.println("**************************************************************************************************");

        try {
            System.out.println("Trwałość ekstensji");
            FileOutputStream fileOutputStream = new FileOutputStream("src/extent.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            Contest.writeExtent(objectOutputStream);
            objectOutputStream.close();
            fileOutputStream.close();

            Contest.clearExtent();
            Contest.showExtent();

            FileInputStream fileInputStream = new FileInputStream("src/extent.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Contest.readExtent(objectInputStream);
            objectInputStream.close();
            fileInputStream.close();
            Contest.showExtent();
            System.out.println("**************************************************************************************************");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Atrybut złożony");
        System.out.println("Address= " + contest01.getAddress());
        contest01.setAddress(address02);
        System.out.println("Address= " + contest01.getAddress());
        System.out.println("**************************************************************************************************");

        System.out.println("Atrybut opcjonalny");
        System.out.println(contest01.getUrlAddress());
        System.out.println(contest02.getUrlAddress());
        System.out.println("**************************************************************************************************");

        System.out.println("Atrybut powtarzalny");
        System.out.println(contest01.getOrganizer());
        System.out.println("**************************************************************************************************");

        System.out.println("Atrybut klasowy");
//        TODO:Zrobić argument klasowy
        System.out.println("NIE MA ZRÓB");
        System.out.println("**************************************************************************************************");

        System.out.println("Atrybut pochodny");
        System.out.println(contest01.getAmountOfRestOfThePrizes());
        System.out.println("**************************************************************************************************");

        System.out.println("Metoda klasowa");
        for (Contest contest : Contest.getContests(LocalDateTime.of(1998,9,23, 0,0))) {
            System.out.println(contest);
        }
        System.out.println("**************************************************************************************************");

        System.out.println("Przesłonięcie");
        System.out.println(contest01);
        System.out.println("**************************************************************************************************");

        System.out.println("Przeciążenie nr.1");
        System.out.println("Pierwsza opcja");
        String newOrganizer = "Nowy organizator";
        contest01.addOrganizer(newOrganizer);
        System.out.println(contest01.getOrganizer());
        contest01.removeOrganizer(newOrganizer);
        System.out.println(contest01.getOrganizer());
        System.out.println("Druga opcja");
        List<String> newOrganizerList = new ArrayList<>();
        newOrganizerList.add(newOrganizer);
        newOrganizerList.add("Nowy organizator druga opcja");
        contest01.addOrganizer(newOrganizerList);
        System.out.println(contest01.getOrganizer());
        contest01.removeOrganizer(newOrganizerList);
        System.out.println(contest01.getOrganizer());
        System.out.println("**************************************************************************************************");

        System.out.println("Przeciążenie nr.2");
        System.out.println("Pierwsza opcja");
        for (Contest contest : Contest.getContests(LocalDateTime.of(1998,9,23, 0,0))) {
            System.out.println(contest);
        }
        System.out.println("Druga opcja");
        for (Contest contest : Contest.getContests(LocalDateTime.of(1998,9,23, 0,0), LocalDateTime.of(2020,12, 31, 0, 0))) {
            System.out.println(contest);
        }
        System.out.println("**************************************************************************************************");
    }
}
