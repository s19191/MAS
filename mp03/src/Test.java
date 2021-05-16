import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Electric e01 = new Electric("Tesla", "Tesla", 2000.0, 5, 100000, 14.7, LocalTime.of(3,0));
        InternalCombustion iC01 = new InternalCombustion("Astra", "Opel", 1500.0, 5, 55.5, 9.5, TypeOfGearbox.MANUAL);
        Hybrid h01 = new Hybrid("Corolla", "Toyota", 1500.0, 5, 40.2, 4.5, 3000, 14.7);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(e01);
        vehicles.add(iC01);
        vehicles.add(h01);

        for (Vehicle v : vehicles) {
            System.out.println("Average monthly cost of " + v.getMark() + ": " + v.calculateAverageMonthlyCost());
        }
    }
}