package GUI;

import javax.swing.*;

public class RunShowBaristasContestsGUI {
    public static void main(String[] args) {
        try {
            ShowBaristasContestsGUI showBaristasContestsGUI = new ShowBaristasContestsGUI();
            showBaristasContestsGUI.showGui();
        } catch(Exception exc) {
            JOptionPane.showMessageDialog(null, "Creation failed, " + exc);
        }
    }
}
