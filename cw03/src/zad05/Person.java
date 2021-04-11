package zad05;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Person {
    String name;
    String surname;
    Sex sex;
    ZonedDateTime dateOfBirth;

    public Person(String name, String surname, Sex sex, ZonedDateTime dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }

    ZonedDateTime caltulateAge(){
        ZonedDateTime today = LocalDateTime.now().atZone(ZoneId.of("Europe/Warsaw"));
        ZonedDateTime age = today.minusYears(dateOfBirth.getYear()).minusMonths(dateOfBirth.getMonthValue()).minusDays(dateOfBirth.getDayOfMonth());
        return age;
    }
    static String showAge(ZonedDateTime age){
        return "years: " + age.getYear() + ", mounth: " + age.getMonthValue() + ", day: " + age.getDayOfMonth() + 1;
    }
}