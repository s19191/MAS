package GUI;

import classes.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ExecutionOfANewOrderGUI {

    public void showGui() throws NullPointerException {
        SwingUtilities.invokeLater(() -> {
            JFrame jf = new JFrame("Kawiarnia StarDucks");
            jf.setPreferredSize(new Dimension(1080,720));
//            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//            jf.setPreferredSize(dim);

            DefaultTableModel jTableModel = new DefaultTableModel(new Object[]{"Napój", "Cena", ""}, 0) {
                @Override
                public Class getColumnClass(int columnIndex) {
                    if (columnIndex == 2) {
                        return Boolean.class;
                    } else {
                        return String.class;
                    }
                }
                @Override
                public boolean isCellEditable(int row, int col) {
                    if (col == 2) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            List<Beverage> allBeverages = Beverage.getAllBeverages();
            for (int index = 0; index < allBeverages.size(); index++) {
                jTableModel.addRow(new Object[]{allBeverages.get(index).getName(), String.valueOf(allBeverages.get(index).getPrice()), false});
            }

            JTable beveragesJT = new JTable(jTableModel);
            JScrollPane beveragesSP = new JScrollPane(beveragesJT);

            JButton nextJB = new JButton("Przejdź do kasy");
            nextJB.addActionListener(e-> {
                List<Beverage> chosenBeverages = new ArrayList<>();
                for (int i = 0; i < beveragesJT.getRowCount(); i++) {
                    Boolean ifChecked = (Boolean) beveragesJT.getValueAt(i, 2);
                    if (ifChecked) {
                        chosenBeverages.add(allBeverages.get(i));
                    }
                }
                if (chosenBeverages.size() >0 ) {
                    showSecondStep(chosenBeverages);
                    jf.dispose();
                }
            });

            jf.setLayout(new BorderLayout());
            jf.add(beveragesSP, BorderLayout.CENTER);
            jf.add(nextJB, BorderLayout.SOUTH);
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setVisible(true);
        });
    }

    private void showSecondStep(List<Beverage> beverages) throws NullPointerException {
        SwingUtilities.invokeLater(() -> {
            JFrame jf = new JFrame("Kawiarnia StarDucks");
            jf.setPreferredSize(new Dimension(1080,720));
//            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//            jf.setPreferredSize(dim);

            Person mockLoyaltyMember = getLoyaltyClubMember();

            JLabel enterCodeJL = new JLabel("Wprowadź kod zniżkowy");
            JTextField codeJTF = new JTextField();
            JButton enterJB = new JButton("Wprowadź");
            enterJB.addActionListener(e1-> {
                try {
                    String code = codeJTF.getText();
                    if (Discount.checkDiscountCode02(mockLoyaltyMember, code)) {
                        Discount discount = getDiscountByCode(code);
                        showThirdStep(beverages, mockLoyaltyMember, discount);
                        jf.dispose();
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            JButton skipJB = new JButton("Pomiń");
            skipJB.addActionListener(e2-> {
                showThirdStep(beverages, mockLoyaltyMember, null);
                jf.dispose();
                jf.dispose();
            });
            JPanel southJP = new JPanel();
            southJP.setLayout(new GridLayout(1,2));
            southJP.add(enterJB);
            southJP.add(skipJB);

            JPanel centerJP = new JPanel();
            centerJP.setLayout(new GridLayout(1,2));
            centerJP.add(enterCodeJL);
            centerJP.add(codeJTF);

            jf.setLayout(new BorderLayout());
            jf.add(southJP, BorderLayout.SOUTH);
            jf.add(centerJP, BorderLayout.CENTER);
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setVisible(true);
        });
    }

    private void showThirdStep(List<Beverage> beverages, Person loyaltyClubMember, Discount discount) throws NullPointerException {
        SwingUtilities.invokeLater(() -> {
            JFrame jf = new JFrame("Kawiarnia StarDucks");
            jf.setPreferredSize(new Dimension(1080,720));
//            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//            jf.setPreferredSize(dim);

            DefaultTableModel jTableModel = new DefaultTableModel(new Object[]{"Napój", "Cena"}, 0) {
                @Override
                public Class getColumnClass(int columnIndex) {
                    return String.class;
                }
            };
            for (int index = 0; index < beverages.size(); index++) {
                jTableModel.addRow(new Object[]{beverages.get(index).getName(), String.valueOf(beverages.get(index).getPrice()), false});
            }

            JTable beveragesJT = new JTable(jTableModel);
            JScrollPane beveragesSP = new JScrollPane(beveragesJT);

            JLabel summaryJL = new JLabel("Podsumowanie:");
            JLabel positionsJL = new JLabel("Pozycje:");
            JLabel amountJL = new JLabel("Kwota:");
            JLabel amountInNumberJL = new JLabel();

            Double sum = 0.0;
            for (Beverage b : beverages) {
                sum += b.getPrice();
            }
            amountInNumberJL.setText(sum + " zł");

            JPanel centerJP = new JPanel();
            centerJP.setLayout(new BorderLayout());
            centerJP.add(positionsJL, BorderLayout.WEST);
            centerJP.add(beveragesSP, BorderLayout.CENTER);

            JPanel southCenterJP = new JPanel();
            southCenterJP.setLayout(new GridLayout(1,2));
            southCenterJP.add(amountJL);
            southCenterJP.add(amountInNumberJL);

            centerJP.add(southCenterJP, BorderLayout.SOUTH);

            JButton orderJB = new JButton("Zamów");

            orderJB.addActionListener(e -> {
                saveOrderWithDiscount(beverages, loyaltyClubMember, discount);
                jf.dispose();
            });

            jf.setLayout(new BorderLayout());
            jf.add(orderJB, BorderLayout.SOUTH);
            jf.add(summaryJL, BorderLayout.NORTH);
            jf.add(centerJP, BorderLayout.CENTER);
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setVisible(true);
        });
    }

    private Person getLoyaltyClubMember() {
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

            return loyaltyClubMembers.get(0);
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

    private Order saveOrderWithDiscount(List<Beverage> beverages, Person loyaltyClubMember, Discount discount) {
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

            Order newOrder;
            if (discount != null) {
                newOrder = Order.createOrder(beverages, loyaltyClubMember, discount);
            } else {
                newOrder = Order.createOrder(beverages, loyaltyClubMember);
            }

            session.save(newOrder);

            session.getTransaction().commit();
            session.close();

            return newOrder;
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

    private Discount getDiscountByCode(String code) {
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
            queryDiscount.where(criteriaBuilder.equal(rootDiscount.get("code"), code));

            List<Discount> discounts = session.createQuery(queryDiscount).getResultList();

            session.getTransaction().commit();
            session.close();

            return discounts.get(0);
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
}
