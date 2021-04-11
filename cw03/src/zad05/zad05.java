package zad05;

import java.time.*;

public class zad05 {
    public static void main(String[] args) {
        Person person01 = new Person("Jan", "Kwasowski", Sex.MALE, ZonedDateTime.of(LocalDateTime.of(LocalDate.of(1998,9,23), LocalTime.MIDNIGHT), ZoneId.of("Europe/Warsaw")));
        Person person02 = new Person("Jan", "Kwasowski", Sex.MALE, ZonedDateTime.of(LocalDateTime.of(LocalDate.of(1998,9,23), LocalTime.MIDNIGHT), ZoneId.of("Europe/Warsaw")));
        Person person03 = new Person("Jan", "Kwasowski", Sex.MALE, ZonedDateTime.of(LocalDateTime.of(LocalDate.of(1998,9,23), LocalTime.MIDNIGHT), ZoneId.of("Europe/Warsaw")));
        System.out.println(Person.showAge(person01.caltulateAge()));
    }
}
