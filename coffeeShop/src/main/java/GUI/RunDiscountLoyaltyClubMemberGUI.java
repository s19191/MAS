package GUI;

import javax.swing.*;

public class RunDiscountLoyaltyClubMemberGUI {
    public static void main(String[] args) {
        try {
            DiscountLoyaltyClubMemberGUI discountLoyaltyClubMemberGUI = new DiscountLoyaltyClubMemberGUI();
            discountLoyaltyClubMemberGUI.showGui();
        } catch(Exception exc) {
            JOptionPane.showMessageDialog(null, "Creation failed, " + exc);
        }
    }
}