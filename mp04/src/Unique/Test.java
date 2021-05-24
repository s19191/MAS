package Unique;

public class Test {
    public static void main(String[] args) {
        Beverage b01 = null;
        Beverage b02 = null;
        Beverage b03 = null;
        Beverage b04 = null;
        try {
            b01 = Beverage.createBeverage("Late", 24.0, "L01");
            b02 = Beverage.createBeverage("Cappuccino", 32.0, "C01");
            b03 = Beverage.createBeverage("Late mocha", 24.0, "L02");
            b04 = Beverage.createBeverage("Late", 24.0, "L01");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(Beverage.findByCode("L01"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
