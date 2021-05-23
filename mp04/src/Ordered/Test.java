package Ordered;

import Atrybutu.Address;
import Atrybutu.NotNullException;
import Subset.Sex;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Set<String> organizer01 = new HashSet<>();
        organizer01.add("Jan Kwasowski");
        organizer01.add("Kawiarnia StarDucks");

        Set<String> organizer02 = new HashSet<>();
        organizer02.add("Polskie stowarzyszenie baristów");

        Address address01;
        Address address02;
        Contest contest01 = null;
        Contest contest02 = null;
        Barista barista01 = null;

        try {
            address01 = Address.createAddress("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Melomanów", 10, 50, "00-712");
            address02 = Address.createAddress("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Iwicka", 12, "00-735");
            contest01 = Contest.createContest("Konkurs Barista nr.1", 1000, 3000, LocalDateTime.of(2020, 9, 23, 16, 0), address01, organizer01, new URL("https://github.com/s19191"), "Super fajny konkurs", LocalTime.of(3,0));
            contest02 = Contest.createContest("Konkurs gminy Warszawa-Mokotów", 500, 1000, LocalDateTime.of(2021, 2, 12, 12, 30), address02, organizer02, new URL("https://github.com/s19191"), LocalTime.of(3,0));
            barista01 = Barista.createBarista("Jan", "Kwasowski", Sex.MALE, LocalDate.now());
        } catch (NotNullException | MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            barista01.addContest(contest02);
            barista01.addContest(contest01);

            for (Contest c : barista01.getContests()) {
                System.out.println(c.getDateOfTheEvent());
            }

            System.out.println(barista01.findTheClosesContest());
        } catch (NotNullException e) {
            e.printStackTrace();
        }
    }
}
