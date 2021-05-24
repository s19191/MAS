package Xor;

import Atrybutu.Address;
import Atrybutu.NotNullException;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

        List<String> menu01 = new ArrayList<>();
        menu01.add("Kawa");

        List<String> menu02 = new ArrayList<>();
        menu02.add("Nie kawa");

        Company company01 = null;
        Company company02 = null;
        Company company03 = null;

        CoffeeShop coffeeShop01 = null;
        CoffeeShop coffeeShop02 = null;
        CoffeeShop coffeeShop03 = null;

        Contest contest01 = null;
        Contest contest02 = null;
        Contest contest03 = null;

        try {
            company01 = Company.createCompany("Kawa fajna",address01, "AAAAW#@#@");
            company02 = Company.createCompany("Drutex",address02, "N@@@2");
            company03 = Company.createCompany("Pratex",address01, "PRS");

            contest01 = Contest.createContest("Konkurs Barista nr.1", 1000, 3000, LocalDateTime.of(2020, 9, 23, 16, 0), address01, organizer01, new URL("https://github.com/s19191"), "Super fajny konkurs", LocalTime.of(3,0));
            contest02 = Contest.createContest("Konkurs gminy Warszawa-Mokotów", 500, 1000, LocalDateTime.of(2021, 2, 12, 12, 30), address02, organizer02, new URL("https://github.com/s19191"), LocalTime.of(3,0));
            contest03 = Contest.createContest("Konkurs01", 100, 500, LocalDateTime.of(2021, 1, 30, 10, 15), address02, organizer01, new URL("https://github.com/s19191"), LocalTime.of(3,0));

            coffeeShop01 = CoffeeShop.createCoffeeShop("Starducks", address01, menu01);
            coffeeShop02 = CoffeeShop.createCoffeeShop("Kawka z rana", address02, menu02);
            coffeeShop03 = CoffeeShop.createCoffeeShop("Kawusia synusia", address01, menu01);
        } catch (NotNullException | MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            coffeeShop01.addSponsor(company01);
            contest01.addSponsor(company01);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            company02.addSponsoredContest(contest02);
            company02.addSponsoredCoffeeShop(coffeeShop02);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
