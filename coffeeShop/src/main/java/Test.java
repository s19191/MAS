import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;

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

            Person loyaltyClubMember01 = Person.createLoyaltyClubMember("Jan", "Kwasowski", Sex.MALE, LocalDate.of(1998,9,23), address01,"s19191@pjwstk.pl","+48111111111", LocalDate.now());
            Person loyaltyClubMember02 = Person.createLoyaltyClubMember("Ala", "Kot", Sex.FEMALE, LocalDate.of(2001,1,11), address02,"ala@makota.pl","+48222222222", LocalDate.now());

            Person barista01 = Person.createBarista("Adam", "Małysz", Sex.MALE, LocalDate.of(1988,2,2), address02, LocalDate.now(), BaristaRank.JUNIOR);
            Person barista02 = Person.createBarista("Anna", "Maria", Sex.FEMALE, LocalDate.of(2000,4,1), address01, LocalDate.now(), BaristaRank.SENIOR);

            Person shiftManagerLoyaltyClubMember01 = Person.createShiftManagerLoyaltyClubMember("Piotr", "Kowaslki", Sex.MALE, LocalDate.of(2001, 5, 29), address01, LocalDate.now(), 1, "monkey@m.m", "+48333333333", LocalDate.now());
            Person shiftManagerLoyaltyClubMember02 = Person.createShiftManagerLoyaltyClubMember("Marian", "Malinowski", Sex.MALE, LocalDate.of(1977, 12, 30), address02, LocalDate.now(), 2, "mock@mock.mock", "+48444444444", LocalDate.now());

            session.save(shiftManagerLoyaltyClubMember01);
            session.save(shiftManagerLoyaltyClubMember02);

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