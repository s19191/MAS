import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) {

        Set<String> organizer01 = new HashSet<>();
        organizer01.add("Jan Kwasowski");
        organizer01.add("Kawiarnia StarDucks");

        Set<String> organizer02 = new HashSet<>();
        organizer02.add("Polskie stowarzyszenie baristów");

        List<String> coffees01 = new ArrayList<>();
        coffees01.add("Kawka");
        coffees01.add("Herbatka");
        coffees01.add("Mięta");

        List<String> coffees02 = new ArrayList<>();
        coffees02.add("Kawka2");
        coffees02.add("Herbatka2");
        coffees02.add("Mięta2");

        Address address01 = null;
        Address address02 = null;
        Contest contest01 = null;
        Contest contest02 = null;
        Barista barista01 = null;
        Barista barista02 = null;
        Barista barista03 = null;
        Beverage beverage01 = null;
        Beverage beverage02 = null;
        Ingredient ingredient01 = null;
        Ingredient ingredient02 = null;
        Order order01 = null;
        Order order02 = null;
        try {
            address01 = Address.createAddress("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Melomanów", 10, 50, "00-712");
            address02 = Address.createAddress("Poland", "Mazowieckie", "Warszawski", "Warszawa-Mokotów", "Warszawa", "Iwicka", 12, "00-735");
            contest01 = Contest.createContest("Konkurs Barista nr.1", 1000, 3000, LocalDateTime.of(2020, 9, 23, 16, 0), address01, organizer01, new URL("https://github.com/s19191"), "Super fajny konkurs");
            contest02 = Contest.createContest("Konkurs gminy Warszawa-Mokotów", 500, 1000, LocalDateTime.of(2021, 2, 12, 12, 30), address02, organizer02, new URL("https://github.com/s19191"));
            barista01 = Barista.createBarista("Jan", "Kwasowski", Sex.MALE, LocalDate.now());
            barista02 = Barista.createBarista("Anna", "Maria", Sex.FEMALE, LocalDate.now());
            barista03 = Barista.createBarista("Adam", "Adam", Sex.MALE, LocalDate.now());
            beverage01 = Beverage.createBeverage("Late", 20.0, "C01");
            beverage02 = Beverage.createBeverage("Cappuccino", 21.0, "C02");
            ingredient01 = Ingredient.createIngredient("Robusta", 20.0, "kg");
            ingredient02 = Ingredient.createIngredient("Arabica", 10.0, "kg");
            order01 = Order.createOrder(coffees01);
            order02 = Order.createOrder(coffees02);
        } catch (NotNullException | MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("************************************************************************************************Zwykła asocjacja************************************************************************************************");
        System.out.println();
        try {
            barista01.addContest(contest01);
            // dodanie po raz drugi tego samego konkursu
            barista01.addContest(contest01);
            contest01.addBarista(barista03);
            barista03.addContest(contest02);
            contest02.addBarista(barista02);

            System.out.println("**********************Stan początkowy contest01**********************");
            for (Barista b : contest01.getBaristas()) {
                System.out.println(b);
            }
            System.out.println();
            System.out.println("**********************Stan początkowy barista03**********************");
            for (Contest c : barista03.getContests()) {
                System.out.println(c);
            }
            System.out.println();

            System.out.println("**********************Stan po usunięciu contest02 z baristy03**********************");
            barista03.removeContest(contest02);
            for (Contest c : barista03.getContests()) {
                System.out.println(c);
            }
            System.out.println();

            System.out.println("**********************Stan po usunięciu baristy03 z contest01**********************");
            contest01.removeBarista(barista03);
            for (Barista b : contest01.getBaristas()) {
                System.out.println(b);
            }
            System.out.println();
        } catch (NotNullException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("************************************************************************************************Asocjacja z atrybutem************************************************************************************************");
        System.out.println();
        try {
            Composition composition01 = Composition.createComposition(0.1, 100.0, beverage01, ingredient01);
            Composition composition02 = Composition.createComposition(0.2, 200.0, beverage01, ingredient02);
            Composition composition03 = Composition.createComposition(0.3, 300.0, beverage02, ingredient01);
            Composition composition04 = Composition.createComposition(0.4, 400.0, beverage02, ingredient02);
            System.out.println("**********************Wyświetlanie composition z beverage01**********************");
            for (Composition c : beverage01.getCompositions()) {
                System.out.println(c);
            }
            System.out.println();
            System.out.println("**********************Wyświetlanie composition z ingredient01**********************");
            for (Composition c : ingredient01.getCompositions()) {
                System.out.println(c);
            }
            System.out.println();
//            System.out.println("**********************Beverage oraz Ingredient z composition01**********************");
//            System.out.println(composition01.getBeverage());
//            System.out.println(composition01.getIngredient());
//            System.out.println();
//            System.out.println("**********************Beverage oraz Ingredient z composition02**********************");
//            System.out.println(composition02.getBeverage());
//            System.out.println(composition02.getIngredient());
//            System.out.println();
//            System.out.println("**********************Beverage oraz Ingredient z composition03**********************");
//            System.out.println(composition03.getBeverage());
//            System.out.println(composition03.getIngredient());
//            System.out.println();
//            System.out.println("**********************Beverage oraz Ingredient z composition04**********************");
//            System.out.println(composition04.getBeverage());
//            System.out.println(composition04.getIngredient());
//            System.out.println();

            Composition composition05 = Composition.createComposition(1.0, 1000.0, beverage01, ingredient01);
            System.out.println("**********************Beverage oraz Ingredient z composition05 początkowo (beverage01 oraz ingredient01)**********************");
            System.out.println(composition05.getBeverage());
            System.out.println(composition05.getIngredient());
            System.out.println();

            composition05.setBeverage(beverage02);
            composition05.setIngredient(ingredient02);
            System.out.println("**********************Beverage oraz Ingredient z composition05 po zmianach (beverage02 oraz ingredient02)**********************");
            System.out.println(composition05.getBeverage());
            System.out.println(composition05.getIngredient());
            System.out.println();
        } catch (NotNullException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("************************************************************************************************Asocjacja kwalifikowana************************************************************************************************");
        System.out.println();
        try {
            barista01.addOrderQualif(order01);
            System.out.println("**********************Znalezienie i wyświetlenie z barista01 znalezienie po nrOrder 1**********************");
            System.out.println(barista01.findOrderQualif(1));
            System.out.println(order01.getAssignedBaristas());
            System.out.println();

//            System.out.println("**********************Początkowo order2 jest połączone z barista02**********************");
//            barista02.addOrderQualif(order02);
//            System.out.println(order02.getAssignedBaristas());
//            System.out.println();

            order02.addAssignedBarista(barista01);
            System.out.println("**********************Znalezienie i wyświetlenie z barista01 znalezienie po nrOrder 2**********************");
            System.out.println(barista01.findOrderQualif(2));
            System.out.println(order01.getAssignedBaristas());
            System.out.println();
        } catch (NotNullException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("************************************************************************************************Kompozycja************************************************************************************************");
        System.out.println();
        try {
            LoyaltyClubMember loyaltyClubMember01 = LoyaltyClubMember.createLoyaltyClubMember("Jan", "Kwasowski", Sex.MALE, LocalDate.now(), "s19191@pjwstk.edu.pl", "+48 111-111-111", LocalDate.now(), 0);
            loyaltyClubMember01.createDiscount(0.2, "Purpose01", "D01");
            System.out.println("**********************Znalezienie i wyświetlenie z loyaltyClubMember01 discountAmount oraz purpose z discount o kodzie D01**********************");
            System.out.println(loyaltyClubMember01.getDiscountAmount("D01"));
            System.out.println(loyaltyClubMember01.getDiscountPurpose("D01"));
            System.out.println();

            loyaltyClubMember01.createDiscount(0.3, "Purpose02", "D02");
            System.out.println("**********************Znalezienie i wyświetlenie z loyaltyClubMember01 discountAmount oraz purpose z discount o kodzie D02**********************");
            System.out.println(loyaltyClubMember01.getDiscountAmount("D02"));
            System.out.println(loyaltyClubMember01.getDiscountPurpose("D02"));
            System.out.println();

            System.out.println("**********************Próba dodania discount o kodzie, który istnieje (D01)**********************");
            loyaltyClubMember01.createDiscount(0.2, "Purpose01", "D01");
        } catch (NotNullException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}