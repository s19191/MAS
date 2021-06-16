import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiManager {
    public List<Person> getLoyaltyClubMembers() {
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

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<Person> queryPerson = criteriaBuilder.createQuery(Person.class);
            Root<Person> rootPerson = queryPerson.from(Person.class);
            queryPerson.select(rootPerson);
//            Join<Object, Object> loyaltyClub = rootPerson.join(Person.)
//            queryPerson.where(criteriaBuilder.equal(rootPerson.get("phoneNumber"), "+48 222-222-222"));

            List<Person> personList = session.createQuery(queryPerson).getResultList();

            List<Person> loyaltyClubMembers = new ArrayList<>();
            for (Person p : personList) {
                if (p.getPersonKind().contains(PersonType.LOYALTYCLUBMEMBER)) {
                    loyaltyClubMembers.add(p);
                }
            }
            session.getTransaction().commit();

            session.close();
            return loyaltyClubMembers;

        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
            return null;
        }
        finally {
            if(sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    public void showGui() throws NullPointerException {
        SwingUtilities.invokeLater(() -> {
            JFrame jf = new JFrame("Currency converter");
            jf.setPreferredSize(new Dimension(1080,720));

//            DefaultListModel<String> l1 = new DefaultListModel<>();
//            for (String s: currencyRates.keySet()) {
//                l1.addElement(s);
//            }
//            JList currencyJList = new JList(l1);

            jf.setLayout(new BorderLayout());
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setVisible(true);
        });
    }
}
