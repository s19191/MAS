package Wieloaspektowe;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Test03 {
    public static void main(String[] args) {
        Vehicle v01 = new Electric("Tesla", "Tesla", 2000.0, 5, 4, 2000, 14.7, LocalTime.of(3, 0));
        Vehicle v02 = new Electric("A1100", "Statek", 2000.0, 5, 2000.0, BodyOfWater.INLAND, 2000, 14.7, LocalTime.of(3, 0));
        Vehicle v03 = new InternalCombustion("Astra", "Opel", 1500.0, 5, 1500.0, BodyOfWater.MARINE, 55.5, 9.5, TypeOfGearbox.MANUAL);

        try {
            v01.getBodyOfWater();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(v01);
        vehicles.add(v02);
        vehicles.add(v03);

        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
}