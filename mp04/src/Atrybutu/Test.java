package Atrybutu;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Address address01 = null;
        Address address02 = null;
        try {
            address01 = Address.createAddress("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Melomanów", 10, 50, "00-712");
            address02 = Address.createAddress("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Iwicka", 12, "00-735");
        } catch (NotNullException e) {
            e.printStackTrace();
        }

        Set<String> organizer01 = new HashSet<>();
        organizer01.add("Jan Kwasowski");
        organizer01.add("Kawiarnia StarDucks");

        Set<String> organizer02 = new HashSet<>();
        organizer02.add("Polskie stowarzyszenie baristów");

        Contest contest01 = null;
        Contest contest02 = null;
        Contest contest03 = null;
        Contest contest04 = null;
        try {
            contest01 = Contest.createContest("Konkurs Barista nr.1", 1000, 3000, LocalDateTime.of(2020, 9, 23, 16, 0), address01, organizer01, new URL("https://github.com/s19191"), "Super fajny konkurs", LocalTime.of(3,0));
            contest02 = Contest.createContest("Konkurs gminy Warszawa-Mokotów", 500, 1000, LocalDateTime.of(2021, 2, 12, 12, 30), address02, organizer02, new URL("https://github.com/s19191"), LocalTime.of(3,0));
            contest03 = Contest.createContest("Konkurs01", 100, 500, LocalDateTime.of(2021, 1, 30, 10, 15), address02, organizer01, new URL("https://github.com/s19191"), LocalTime.of(3,0));
            contest04 = Contest.createContest("Konkurs02", 1500, 3000, LocalDateTime.of(2021, 2, 22, 10, 15), address02, organizer01, new URL("https://github.com/s19191"), "Opis02", LocalTime.of(12,0));
        } catch (NotNullException | MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            contest01.setTimeOfEvent(LocalTime.of(1,0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            contest02.setTimeOfEvent(LocalTime.of(10,0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            contest03.setTimeOfEvent(LocalTime.of(7,0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}