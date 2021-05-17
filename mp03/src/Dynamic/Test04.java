package Dynamic;

import java.time.LocalTime;

public class Test04 {
    public static void main(String[] args) {
        Plant p01 = new Plant("Bratek", TypeOfSoil.ACID, LocalTime.of(3,0), 20.0);
        System.out.println(p01);
        p01.changeToOutdoor(true, true);
        System.out.println(p01);
        Plant p02 = new Plant("Bratek", TypeOfSoil.ACID, false, false);
        System.out.println(p02);
        p02.changeToIndoor(LocalTime.of(2,0), 15.0);
        System.out.println(p02);

        try {
            System.out.println(p01.getPotDiameter());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(p02.isIfPruning());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}