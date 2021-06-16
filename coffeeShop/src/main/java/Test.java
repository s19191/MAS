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

            session.save(loyaltyClubMember01);
            session.save(loyaltyClubMember02);

            Person barista01 = Person.createBarista("Adam", "Małysz", Sex.MALE, LocalDate.of(1988,2,2), address02, LocalDate.now(), BaristaRank.JUNIOR);
            Person barista02 = Person.createBarista("Anna", "Maria", Sex.FEMALE, LocalDate.of(2000,4,1), address01, LocalDate.now(), BaristaRank.SENIOR);

            session.save(barista01);
            session.save(barista02);

            Person shiftManagerLoyaltyClubMember01 = Person.createShiftManagerLoyaltyClubMember("Piotr", "Kowaslki", Sex.MALE, LocalDate.of(2001, 5, 29), address01, LocalDate.now(), 1, "monkey@m.m", "+48333333333", LocalDate.now());
            Person shiftManagerLoyaltyClubMember02 = Person.createShiftManagerLoyaltyClubMember("Marian", "Malinowski", Sex.MALE, LocalDate.of(1977, 12, 30), address02, LocalDate.now(), 2, "mock@mock.mock", "+48444444444", LocalDate.now());

            session.save(shiftManagerLoyaltyClubMember01);
            session.save(shiftManagerLoyaltyClubMember02);

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