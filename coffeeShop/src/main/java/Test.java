import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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

            System.out.println("************************************************************************************************Klasa************************************************************************************************");
            session.beginTransaction();
            LoyaltyClubMember loyaltyClubMember01 = new LoyaltyClubMember("Jan", "Kwasowski", Sex.MALE, LocalDate.now(), "s19191@pjwstk.edu.pl", "+48 111-111-111", LocalDate.now(), 0);
            LoyaltyClubMember loyaltyClubMember02 = new LoyaltyClubMember("Ala", "Kot", Sex.FEMALE, LocalDate.now(), "ala@makota.pl", "+48 222-222-222", LocalDate.now(), 0);

            session.save(loyaltyClubMember01);
            session.save(loyaltyClubMember02);

            // UPDATE
//            loyaltyClubMember01.setDateOfBirth(LocalDate.of(1998,9,23));
//            session.update(loyaltyClubMember01);

            //DELETE
//            session.delete(loyaltyClubMember02);

            session.getTransaction().commit();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<LoyaltyClubMember> queryLoyaltyClubMember = criteriaBuilder.createQuery(LoyaltyClubMember.class);
            Root<LoyaltyClubMember> rootLoyaltyClubMember = queryLoyaltyClubMember.from(LoyaltyClubMember.class);
            queryLoyaltyClubMember.select(rootLoyaltyClubMember);
            queryLoyaltyClubMember.where(criteriaBuilder.equal(rootLoyaltyClubMember.get("phoneNumber"), "+48 222-222-222"));

            List<LoyaltyClubMember> loyaltyClubMemberList = session.createQuery(queryLoyaltyClubMember).getResultList();

            for (LoyaltyClubMember lcm : loyaltyClubMemberList) {
                System.out.println(lcm);
            }

            System.out.println("************************************************************************************************Asocjacja *_* ************************************************************************************************");
            session.beginTransaction();
            Barista barista01 = new Barista("Jan", "Kwasowski", Sex.MALE, LocalDate.now());
            Barista barista02 = new Barista("Ala", "Kot", Sex.FEMALE, LocalDate.now());
            Barista barista03 = new Barista("Adam", "Adam", Sex.MALE, LocalDate.now());

            Set<String> organizer01 = new HashSet<>();
            organizer01.add("Jan Kwasowski");
            organizer01.add("Kawiarnia StarDucks");

            Set<String> organizer02 = new HashSet<>();
            organizer02.add("Polskie stowarzyszenie baristów");

            Contest contest01 = new Contest("Super konkurs", 1000, 2500, LocalDateTime.now(), organizer01, new URL("https://github.com/s19191"));
            Contest contest02 = new Contest("Wielka kawka", 1000, 4000, LocalDateTime.now(), organizer02, new URL("https://github.com/s19191"));

            contest01.addBarista(barista01);
            contest01.addBarista(barista02);

            barista03.addContest(contest02);

            barista01.addContestWon(contest01);
            barista03.addContestWon(contest02);

            session.save(contest01);
            session.save(contest02);

            session.save(barista01);
            session.save(barista02);
            session.save(barista03);

            CriteriaQuery<Contest> queryContest = criteriaBuilder.createQuery(Contest.class);
            Root<Contest> rootContest = queryContest.from(Contest.class);
            queryContest.select(rootContest);

            List<Contest> contestList = session.createQuery(queryContest).getResultList();

            for (Contest c : contestList) {
                System.out.println(c);
            }

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
