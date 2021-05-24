package OgraniczenieWlasne;

import Atrybutu.Address;
import Atrybutu.NotNullException;

public class Test {
    public static void main(String[] args) {
        Address address01 = null;
        Address address02 = null;
        try {
            address01 = Address.createAddress("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Melomanów", 10, 50, "00-712");
            address02 = Address.createAddress("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Iwicka", 12, "00-735");
        } catch (NotNullException e) {
            e.printStackTrace();
        }

        Company company01 = null;
        Company company02 = null;
        Company company03 = null;

        try {
            company01 = Company.createCompany("Kawa fajna",address01, "PL999999999");
            company02 = Company.createCompany("Drutex",address02, "PL999999999");
            company03 = Company.createCompany("Pratex",address01, "PL88888888");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            company01.setNIP("PL88888888");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
