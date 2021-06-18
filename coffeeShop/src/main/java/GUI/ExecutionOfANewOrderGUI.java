package GUI;

import classes.Beverage;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class ExecutionOfANewOrderGUI {
    public void showGui() throws NullPointerException {
        SwingUtilities.invokeLater(() -> {
            JFrame jf = new JFrame("Currency converter");
            jf.setPreferredSize(new Dimension(1080,720));
            jf.setTitle("Kawiarnia StarDucks");
//            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//            jf.setPreferredSize(dim);


            List<Beverage> beverages = Beverage.getAllBeverages();
            String[][] data = new String[beverages.size()][2];
            for (int i = 0; i < Beverage.getAllBeverages().size(); i++) {
                data[i] = new String[]{beverages.get(i).getName(), String.valueOf(beverages.get(i).getPrice())};
            }

            String[] columnNames = new String[] {"Napój", "Cena"};
            JTable jTable = new JTable(data, columnNames);
            jTable.setEnabled(false);


            jf.setLayout(new BorderLayout());
            jf.add(jTable, BorderLayout.CENTER);
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setVisible(true);
        });
    }
}
