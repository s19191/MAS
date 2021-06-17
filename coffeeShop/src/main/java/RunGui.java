import javax.swing.*;

public class RunGui {
    public static void main(String[] args) {
        try {
            GuiManager guiManager = new GuiManager();
            guiManager.showGui();
        } catch(Exception exc) {
            JOptionPane.showMessageDialog(null, "Creation failed, " + exc);
        }
    }
}