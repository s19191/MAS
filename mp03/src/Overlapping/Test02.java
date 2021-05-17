package Overlapping;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Test02 {
    public static void main(String[] args) {
        Vehicle v01 = new Vehicle("Tesla", "Tesla", 2000.0, 5, 2000, 14.7, LocalTime.of(3, 0), 55.5, 9.5, TypeOfGearbox.MANUAL);
        Vehicle v02 = new Vehicle("A1100", "Volvo", 2000.0, 5, 2000, 14.7, LocalTime.of(3, 0));
        Vehicle v03 = new Vehicle("Astra", "Opel", 1500.0, 5, 55.5, 9.5, TypeOfGearbox.MANUAL);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(v01);
        vehicles.add(v02);
        vehicles.add(v03);

        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
}