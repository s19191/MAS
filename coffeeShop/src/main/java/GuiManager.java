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
import java.awt.event.ActionListener;
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

    public List<Discount> getDiscounts() {
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
            CriteriaQuery<Discount> queryDiscount = criteriaBuilder.createQuery(Discount.class);
            Root<Discount> rootDiscount = queryDiscount.from(Discount.class);
            queryDiscount.select(rootDiscount);
            List<Discount> discounts = session.createQuery(queryDiscount).getResultList();

            session.getTransaction().commit();
            session.close();

            return discounts;
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
//            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//            jf.setPreferredSize(dim);

            JList whatChosen = new JList();
            whatChosen.setEnabled(false);

            JComboBox lCMJComboBox = new JComboBox(getLoyaltyClubMembers().toArray());
            JComboBox dJComboBox = new JComboBox(getDiscounts().toArray());

            JPanel jPanel = new JPanel();
            jPanel.setLayout(new GridLayout(2,2));
            jPanel.add(new JLabel("Członek klubu lojanościowego"));
            jPanel.add(new JLabel("Zniżka"));
            jPanel.add(lCMJComboBox);
            jPanel.add(dJComboBox);

            lCMJComboBox.addActionListener(l -> {
                Person loyaltyClubMember = (Person) lCMJComboBox.getSelectedItem();
                try {
                    ActionListener[] oldListener = dJComboBox.getActionListeners();
                    dJComboBox.removeActionListener(oldListener[0]);
                    dJComboBox.removeAllItems();
                    whatChosen.removeAll();
                    DefaultListModel model = new DefaultListModel();
                    for (Discount d : loyaltyClubMember.getDiscounts()) {
                        dJComboBox.addItem(d);
                        model.addElement(d);
                    }
                    whatChosen.setModel(model);
                    dJComboBox.addActionListener(oldListener[0]);
                } catch (Exception exe) {
                    exe.printStackTrace();
                }
            });

            dJComboBox.addActionListener(l -> {
                Discount discount = (Discount) dJComboBox.getSelectedItem();
                try {
                    ActionListener[] oldListener = lCMJComboBox.getActionListeners();
                    lCMJComboBox.removeActionListener(oldListener[0]);
                    lCMJComboBox.removeAllItems();
                    whatChosen.removeAll();
                    DefaultListModel model = new DefaultListModel();
                    for (Person lCM : discount.getLoyaltyClubMembers()) {
                        lCMJComboBox.addItem(lCM);
                        model.addElement(lCM);
                    }
                    whatChosen.setModel(model);
                    lCMJComboBox.addActionListener(oldListener[0]);
                } catch (Exception exe) {
                    exe.printStackTrace();
                }
            });

            jf.setLayout(new BorderLayout());
            jf.add(jPanel,BorderLayout.NORTH);
            jf.add(whatChosen);
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setVisible(true);
        });
    }
}
