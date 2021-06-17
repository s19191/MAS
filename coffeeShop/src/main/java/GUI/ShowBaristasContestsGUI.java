package GUI;

import classes.Contest;
import classes.Person;
import classes.PersonType;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShowBaristasContestsGUI {
    public List<Person> getBaristas() {
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
                if (p.getPersonKind().contains(PersonType.BARISTA)) {
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
//            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//            jf.setPreferredSize(dim);

            JList whatChosenJL = new JList();
            whatChosenJL.setEnabled(false);

            JComboBox lCMJCB = new JComboBox(getBaristas().toArray());

            JComboBox All_WonJCB = new JComboBox();
            All_WonJCB.addItem("Wszystkie");
            All_WonJCB.addItem("Wygrane");

            ActionListener actionListenerForJComboBoxes = e -> {
                String tmp = (String) All_WonJCB.getSelectedItem();
                if (tmp.equals("Wszystkie")){
                    try {
                        whatChosenJL.removeAll();
                        Person barista = (Person) lCMJCB.getSelectedItem();
                        DefaultListModel model = new DefaultListModel();
                        for (Contest c : barista.getContests()) {
                            model.addElement(c);
                        }
                        whatChosenJL.setModel(model);
                    } catch (Exception exe) {
                        exe.printStackTrace();
                    }
                } else {
                    try {
                        whatChosenJL.removeAll();
                        Person barista = (Person) lCMJCB.getSelectedItem();
                        DefaultListModel model = new DefaultListModel();
                        for (Contest c : barista.getContestsWon()) {
                            model.addElement(c);
                        }
                        whatChosenJL.setModel(model);
                    } catch (Exception exe) {
                        exe.printStackTrace();
                    }
                }
            };

            lCMJCB.addActionListener(actionListenerForJComboBoxes);
            All_WonJCB.addActionListener(actionListenerForJComboBoxes);

            JLabel incorrectValueDateFromJL = new JLabel();
            incorrectValueDateFromJL.setForeground(Color.RED);

            JLabel incorrectValueDateToJL = new JLabel();
            incorrectValueDateToJL.setForeground(Color.RED);

            JButton calculateButton = new JButton("Wprowadź okres");
            calculateButton.setEnabled(false);

            boolean ifEnableCalculateButtons[] = new boolean[2];

            JTextField dateFromJTF = new JTextField();
            dateFromJTF.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        LocalDate tmpDate = LocalDate.parse(dateFromJTF.getText());
                        incorrectValueDateFromJL.setText("");
                        ifEnableCalculateButtons[0] = true;
                        if (ifEnableCalculateButtons[0] == true && ifEnableCalculateButtons[1] == true) calculateButton.setEnabled(true);
                    } catch (DateTimeException exception) {
                        incorrectValueDateFromJL.setText("Podano nieprawidłowy format daty!");
                        ifEnableCalculateButtons[0] = false;
                    }
                }
            });

            JTextField dateToJTF = new JTextField();
            dateToJTF.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        LocalDate tmpDate = LocalDate.parse(dateToJTF.getText());
                        incorrectValueDateToJL.setText("");
                        ifEnableCalculateButtons[1] = true;
                        if (ifEnableCalculateButtons[0] == true && ifEnableCalculateButtons[1] == true) calculateButton.setEnabled(true);
                    } catch (DateTimeException exception) {
                        incorrectValueDateToJL.setText("Podano nieprawidłowy format daty!");
                        ifEnableCalculateButtons[1] = false;
                    }
                }
            });

            JPanel chooseBaristasContestsJP = new JPanel();
            chooseBaristasContestsJP.setLayout(new GridLayout(2,3));
            chooseBaristasContestsJP.add(new JLabel("Barista: "));
            chooseBaristasContestsJP.add(lCMJCB);
            chooseBaristasContestsJP.add(new JLabel("Pokaż konkursy: "));
            chooseBaristasContestsJP.add(All_WonJCB);

            JPanel jPanel1 = new JPanel();
            jPanel1.setLayout(new GridLayout(3,1));

            JPanel dateJP = new JPanel();
            dateJP.setLayout(new GridLayout(2,2));
            dateJP.add(new JLabel("Data od: "));
            dateJP.add(dateFromJTF);
            dateJP.add(incorrectValueDateFromJL);
            dateJP.add(new JLabel("Data do: "));
            dateJP.add(dateToJTF);
            dateJP.add(incorrectValueDateToJL);

            jPanel1.add(new JLabel("Datę wprowadzamy w formacie RRRR-MM-DD, np: 2001-09-23"));
            jPanel1.add(dateJP);
            jPanel1.add(calculateButton);

            jf.setLayout(new BorderLayout());
            jf.add(chooseBaristasContestsJP,BorderLayout.NORTH);
            jf.add(whatChosenJL, BorderLayout.CENTER);
            jf.add(jPanel1, BorderLayout.SOUTH);
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setVisible(true);
        });
    }
}