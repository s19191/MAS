import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id_Person;
    public String firstName;
    public String secondName = null;
    public String surname;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private LocalDate dateOfBirth;
    @Embedded
    private Address address;

    @ElementCollection
    private EnumSet<PersonType> personKind;

    private LocalDate dateOfEmployment;
    private LocalDate dateOfFire;

    @Enumerated(EnumType.STRING)
    private BaristaRank baristaRank;

    private int keySetNumber;
    private static Map<Integer, Person> keySetNumberManager = new HashMap<>();

    public Person() {}

    private Person (String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, BaristaRank baristaRank) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.baristaRank = baristaRank;
        personKind.add(PersonType.BARISTA);
    }

    public static Person createBarista(String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, BaristaRank baristaRank) throws NotNullException {
        if (firstName == null || secondName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || baristaRank == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        return new Person(firstName, secondName, surname, sex, dateOfBirth, address, dateOfEmployment, baristaRank);
    }

    private Person (String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, BaristaRank baristaRank) {
        this.firstName = firstName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.baristaRank = baristaRank;
        personKind.add(PersonType.BARISTA);
    }

    public static Person createBarista(String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, BaristaRank baristaRank) throws NotNullException {
        if (firstName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || baristaRank == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        return new Person(firstName, surname, sex, dateOfBirth, address, dateOfEmployment, baristaRank);
    }

    private Person (String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, int keySetNumber) {
        this.firstName = firstName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.keySetNumber = keySetNumber;
        keySetNumberManager.put(keySetNumber, this);
        personKind.add(PersonType.MANAGER);
    }

    public static Person createManager(String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, Integer keySetNumber) throws Exception {
        if (firstName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || keySetNumber == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        if (keySetNumberManager.containsKey(keySetNumber)) {
            throw new Exception(String.format("Can't create object, another manager has keySetNumber: %s", keySetNumber));
        }
        return new Person(firstName, surname, sex, dateOfBirth, address, dateOfEmployment, keySetNumber);
    }

    private Person (String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, int keySetNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.keySetNumber = keySetNumber;
        keySetNumberManager.put(keySetNumber, this);
        personKind.add(PersonType.MANAGER);
    }

    public static Person createManager(String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, Integer keySetNumber) throws Exception {
        if (firstName == null || secondName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || keySetNumber == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        if (keySetNumberManager.containsKey(keySetNumber)) {
            throw new Exception(String.format("Can't create object, another manager has keySetNumber: %s", keySetNumber));
        }
        return new Person(firstName, surname, sex, dateOfBirth, address, dateOfEmployment, keySetNumber);
    }
}
