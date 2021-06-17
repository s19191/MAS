import classes.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;

        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            Address address01 = Address.createAddress("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Melomanów", 10, 50, "00-712");
            Address address02 = Address.createAddress("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Iwicka", 12, "00-735");
            Set<String> organizer01 = new HashSet<>();
            organizer01.add("Jan Kwasowski");
            organizer01.add("Kawiarnia StarDucks");

            Set<String> organizer02 = new HashSet<>();
            organizer02.add("Polskie stowarzyszenie baristów");

            Person loyaltyClubMember01 = Person.createLoyaltyClubMember("Jan", "Kwasowski", Sex.MALE, LocalDate.of(1998,9,23), address01,"s19191@pjwstk.pl","+48111111111", LocalDate.now());
            Person loyaltyClubMember02 = Person.createLoyaltyClubMember("Ala", "Kot", Sex.FEMALE, LocalDate.of(2001,1,11), address02,"ala@makota.pl","+48222222222", LocalDate.now());

            Person barista01 = Person.createBarista("Adam", "Małysz", Sex.MALE, LocalDate.of(1988,2,2), address02, LocalDate.now(), BaristaRank.JUNIOR);
            Person barista02 = Person.createBarista("Anna", "Maria", Sex.FEMALE, LocalDate.of(2000,4,1), address01, LocalDate.now(), BaristaRank.SENIOR);
            Person barista03 = Person.createBarista("Janina", "Janikowska", Sex.FEMALE, LocalDate.of(1988,2,2), address02, LocalDate.now(), BaristaRank.MASTER);
            Person barista04 = Person.createBarista("Marianna", "Kowal", Sex.FEMALE, LocalDate.of(2000,4,1), address01, LocalDate.now(), BaristaRank.SENIOR);

            Person shiftManagerLoyaltyClubMember01 = Person.createShiftManagerLoyaltyClubMember("Piotr", "Kowaslki", Sex.MALE, LocalDate.of(2001, 5, 29), address01, LocalDate.now(), 1, "monkey@m.m", "+48333333333", LocalDate.now());
            Person shiftManagerLoyaltyClubMember02 = Person.createShiftManagerLoyaltyClubMember("Marian", "Malinowski", Sex.MALE, LocalDate.of(1977, 12, 30), address02, LocalDate.now(), 2, "mock@mock.mock", "+48444444444", LocalDate.now());

            Contest contest01 = Contest.createContest("Konkurs Barista nr.1", 1000, 3000, LocalDateTime.of(2020, 9, 23, 16, 0), address01, organizer01, new URL("https://github.com/s19191"), "Super fajny konkurs");
            Contest contest02 = Contest.createContest("Konkurs gminy Warszawa-Mokotów", 500, 1000, LocalDateTime.of(2021, 2, 12, 12, 30), address02, organizer02, new URL("https://github.com/s19191"), "Oficjalny konkurs gminy");
            Contest contest03 = Contest.createContest("Konkurs01", 100, 500, LocalDateTime.of(2021, 1, 30, 10, 15), address02, organizer01, new URL("https://github.com/s19191"), "Opis01");
            Contest contest04 = Contest.createContest("Konkurs02", 1500, 3000, LocalDateTime.of(2021, 2, 22, 10, 15), address02, organizer01, new URL("https://github.com/s19191"), "Opis02");
            Contest contest05 = Contest.createContest("Konkurs03", 200, 800, LocalDateTime.of(2021, 4, 16, 9, 0), address01, organizer02, new URL("https://github.com/s19191"), "Opis03");
            Contest contest06 = Contest.createContest("Konkurs04", 10, 50, LocalDateTime.of(2021, 3, 23, 18, 0), address01, organizer02, new URL("https://github.com/s19191"), "Opis04");

            contest01.addBarista(barista01);
            contest01.addBarista(barista02);
            contest01.addBarista(barista03);
            contest01.addBarista(barista04);
            contest01.addBarista(shiftManagerLoyaltyClubMember01);
            contest01.addBarista(shiftManagerLoyaltyClubMember02);
            contest01.setWinner(barista01);

            contest02.addBarista(barista04);
            contest02.addBarista(shiftManagerLoyaltyClubMember01);
            contest02.setWinner(shiftManagerLoyaltyClubMember01);

            contest03.addBarista(barista01);
            contest03.addBarista(barista02);
            contest03.addBarista(barista03);
            contest03.addBarista(barista04);
            contest03.addBarista(shiftManagerLoyaltyClubMember01);
            contest03.addBarista(shiftManagerLoyaltyClubMember02);
            contest03.setWinner(barista04);

            contest04.addBarista(barista02);
            contest04.addBarista(barista03);
            contest04.addBarista(shiftManagerLoyaltyClubMember02);
            contest04.setWinner(shiftManagerLoyaltyClubMember02);

            contest05.addBarista(barista02);
            contest05.setWinner(barista02);

            contest06.addBarista(barista01);
            contest06.addBarista(barista02);
            contest06.addBarista(barista03);
            contest06.addBarista(barista04);
            contest06.addBarista(shiftManagerLoyaltyClubMember01);
            contest06.addBarista(shiftManagerLoyaltyClubMember02);
            contest06.setWinner(barista04);

            Discount discount01 = Discount.createDiscount(2.50, "Na dobry początek", "NEWMEMBER");
            Discount discount02 = Discount.createDiscount(4.50, "Za usterki techniczne", "U01");
            Discount discount03 = Discount.createDiscount(8.99, "Na piątkowe wypoczynki", "FRIDAY899");
            Discount discount04 = Discount.createDiscount(4.30, "Szczęśliwego nowego roku!", "NEWYEAR2021");
            Discount discount05 = Discount.createDiscount(1.22, "Na dobry początek dnia", "MORNINGCOFFEE");
            Discount discount06 = Discount.createDiscount(2.0, "Na jedną kawkę", "1COFFEE");
            Discount discount07 = Discount.createDiscount(10.50, "Odpocznij w wakacje!", "WAKACJE2021");
            Discount discount08 = Discount.createDiscount(1.0, "Tak bez okazji!", "BEZOKAZJI");
            Discount discount09 = Discount.createDiscount(3.99, "Miej cudowny dzień!", "CUDOWNYDZIEN");

            loyaltyClubMember01.addDiscount(discount01);
            loyaltyClubMember01.addDiscount(discount09);
            loyaltyClubMember01.addDiscount(discount03);
            loyaltyClubMember02.addDiscount(discount01);
            loyaltyClubMember02.addDiscount(discount04);
            loyaltyClubMember02.addDiscount(discount08);
            loyaltyClubMember02.addDiscount(discount02);
            loyaltyClubMember02.addDiscount(discount05);
            loyaltyClubMember02.addDiscount(discount06);
            shiftManagerLoyaltyClubMember01.addDiscount(discount01);
            shiftManagerLoyaltyClubMember01.addDiscount(discount07);
            shiftManagerLoyaltyClubMember02.addDiscount(discount01);
            shiftManagerLoyaltyClubMember02.addDiscount(discount04);
            shiftManagerLoyaltyClubMember02.addDiscount(discount05);
            shiftManagerLoyaltyClubMember02.addDiscount(discount09);

            session.save(loyaltyClubMember01);
            session.save(loyaltyClubMember02);

            session.save(barista01);
            session.save(barista02);
            session.save(barista03);
            session.save(barista04);

            session.save(shiftManagerLoyaltyClubMember01);
            session.save(shiftManagerLoyaltyClubMember02);

            session.save(discount01);
            session.save(discount02);
            session.save(discount03);
            session.save(discount04);
            session.save(discount05);
            session.save(discount06);
            session.save(discount07);
            session.save(discount08);
            session.save(discount09);

            session.save(contest01);
            session.save(contest02);
            session.save(contest03);
            session.save(contest04);
            session.save(contest05);
            session.save(contest06);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        finally {
            if(sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}