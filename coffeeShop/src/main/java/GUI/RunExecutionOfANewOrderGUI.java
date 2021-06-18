package GUI;

import javax.swing.*;

public class RunExecutionOfANewOrderGUI {
    public static void main(String[] args) {
        try {
            ExecutionOfANewOrderGUI executionOfANewOrderGUI = new ExecutionOfANewOrderGUI();
            executionOfANewOrderGUI.showGui();
        } catch(Exception exc) {
            JOptionPane.showMessageDialog(null, "Creation failed, " + exc);
        }
    }
}
