package classes;

import java.time.LocalDate;

public class MethodTest {
    public static void main(String[] args) {
        try {
            Address address01 = Address.createAddress("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Melomanów", 10, 50, "00-712");
            Address address02 = Address.createAddress("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Iwicka", 12, "00-735");

            Person barista01 = Person.createBarista("Adam", "Małysz", Sex.MALE, LocalDate.of(1988,2,2), address02, LocalDate.now(), BaristaRank.JUNIOR);
            Person barista02 = Person.createBarista("Anna", "Maria", Sex.FEMALE, LocalDate.of(2000,4,1), address01, LocalDate.now(), BaristaRank.SENIOR);
            Person barista03 = Person.createBarista("Janina", "Janikowska", Sex.FEMALE, LocalDate.of(1988,2,2), address02, LocalDate.now(), BaristaRank.MASTER);
            Person barista04 = Person.createBarista("Marianna", "Kowal", Sex.FEMALE, LocalDate.of(2000,4,1), address01, LocalDate.now(), BaristaRank.SENIOR);

            System.out.println(Person.getEmployees(LocalDate.of(1000, 2, 2), LocalDate.now().plusYears(2)));

            Order.deleteOldOrders();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
