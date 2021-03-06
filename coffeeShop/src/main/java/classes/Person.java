package classes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id_Person;
    // Atrybuty osoby
    private String firstName;
    private String secondName = null;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private LocalDate dateOfBirth;
    @Embedded
    private Address address;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<PersonType> personKind = new HashSet<>();

    // Atrybuty pracownika
    private LocalDate dateOfEmployment;
    private LocalDate dateOfFire = null;

    // Atrybuty baristy
    @Enumerated(EnumType.STRING)
    private BaristaRank baristaRank;

    // Atrybuty kierwonika zmiany
    @Column(unique = true)
    private Integer keySetNumber;

    // Atrybuty menadzera
    private String businessPhoneNumber;

    // Atrybuty członka klubu lojanościowego
    private String e_mailAddress;
    private String phoneNumber;
    private LocalDate dateOfJoining;
    private LocalDate dateOfLeaving = null;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Barista_Contest",
            joinColumns = { @JoinColumn(name = "id_barista") },
            inverseJoinColumns = { @JoinColumn(name = "id_contest") }
    )
    private Set<Contest> contests = new TreeSet<>(Comparator.comparing(Contest::getDateOfTheEvent));

    @OneToMany(
            mappedBy = "winner",
            fetch = FetchType.EAGER
    )
    private Set<Contest> contestsWon = new HashSet<>();

    @OneToMany(mappedBy = "assignedBarista")
    private Set<Order> ordersAssigned = new HashSet<>();

    @OneToMany(
            mappedBy = "loyaltyClubMember",
            fetch = FetchType.EAGER
    )
    private Set<Order> ordersPlaced = new HashSet<>();

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "Discount_LoyaltyClubMember",
            joinColumns = { @JoinColumn(name = "id_loyaltyClubMember") },
            inverseJoinColumns = { @JoinColumn(name = "id_discount") }
    )
    private Set<Discount> discounts = new HashSet<>();

    public Person() {}

    // Konstrutkory do tworzenia Baristy
    private Person (String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, BaristaRank baristaRank) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.baristaRank = baristaRank;
        personKind.add(PersonType.EMPLOYEE);
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
        personKind.add(PersonType.EMPLOYEE);
        personKind.add(PersonType.BARISTA);
    }

    public static Person createBarista(String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, BaristaRank baristaRank) throws NotNullException {
        if (firstName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || baristaRank == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        return new Person(firstName, surname, sex, dateOfBirth, address, dateOfEmployment, baristaRank);
    }

    // Konstruktory do tworzenia Kierownika zmiany
    private Person (String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, int keySetNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        baristaRank = BaristaRank.MASTER;
        this.keySetNumber = keySetNumber;
        personKind.add(PersonType.EMPLOYEE);
        personKind.add(PersonType.BARISTA);
        personKind.add(PersonType.SHIFTMANAGER);
    }

    public static Person createShiftManager(String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, Integer keySetNumber) throws Exception {
        if (firstName == null || secondName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || keySetNumber == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        return new Person(firstName, secondName, surname, sex, dateOfBirth, address, dateOfEmployment, keySetNumber);
    }

    private Person (String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, int keySetNumber) {
        this.firstName = firstName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        baristaRank = BaristaRank.MASTER;
        this.keySetNumber = keySetNumber;
        personKind.add(PersonType.EMPLOYEE);
        personKind.add(PersonType.BARISTA);
        personKind.add(PersonType.SHIFTMANAGER);
    }

    public static Person createShiftManager(String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, Integer keySetNumber) throws Exception {
        if (firstName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || keySetNumber == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        return new Person(firstName, surname, sex, dateOfBirth, address, dateOfEmployment, keySetNumber);
    }

    // Konstruktory do tworzenia Managera
    private Person (String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, String businessPhoneNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.businessPhoneNumber = businessPhoneNumber;
        personKind.add(PersonType.EMPLOYEE);
        personKind.add(PersonType.MANAGER);
    }

    public static Person createManager(String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, String businessPhoneNumber) throws Exception {
        if (firstName == null || secondName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || businessPhoneNumber == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkPhoneNumber(businessPhoneNumber);
        return new Person(firstName, secondName, surname, sex, dateOfBirth, address, dateOfEmployment, businessPhoneNumber);
    }

    private Person (String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, String businessPhoneNumber) {
        this.firstName = firstName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.businessPhoneNumber = businessPhoneNumber;
        personKind.add(PersonType.EMPLOYEE);
        personKind.add(PersonType.MANAGER);
    }

    public static Person createManager(String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, String businessPhoneNumber) throws Exception {
        if (firstName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || businessPhoneNumber == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkPhoneNumber(businessPhoneNumber);
        return new Person(firstName, surname, sex, dateOfBirth, address, dateOfEmployment, businessPhoneNumber);
    }

    // Konstruktory do tworzenia Członka klubu lojanościowego
    private Person (String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.e_mailAddress = e_mailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        personKind.add(PersonType.LOYALTYCLUBMEMBER);
    }

    public static Person createLoyaltyClubMember(String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) throws Exception {
        if (firstName == null || secondName == null || surname == null || sex == null || dateOfBirth == null || address == null || e_mailAddress == null || phoneNumber == null || dateOfJoining == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkPhoneNumber(phoneNumber);
        checkE_mailAddress(e_mailAddress);
        return new Person(firstName, secondName, surname, sex, dateOfBirth, address, e_mailAddress, phoneNumber, dateOfJoining);
    }

    private Person (String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) {
        this.firstName = firstName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.e_mailAddress = e_mailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        personKind.add(PersonType.LOYALTYCLUBMEMBER);
    }

    public static Person createLoyaltyClubMember(String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) throws Exception {
        if (firstName == null || surname == null || sex == null || dateOfBirth == null || address == null || e_mailAddress == null || phoneNumber == null || dateOfJoining == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkPhoneNumber(phoneNumber);
        checkE_mailAddress(e_mailAddress);
        return new Person(firstName, surname, sex, dateOfBirth, address, e_mailAddress, phoneNumber, dateOfJoining);
    }

    // Konstrutkory do tworzenia Baristy + Członka klubu lojanościowego
    private Person (String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, BaristaRank baristaRank, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.baristaRank = baristaRank;
        this.e_mailAddress = e_mailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        personKind.add(PersonType.EMPLOYEE);
        personKind.add(PersonType.BARISTA);
        personKind.add(PersonType.LOYALTYCLUBMEMBER);
    }

    public static Person createBaristaLoyaltyClubMember(String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, BaristaRank baristaRank, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) throws Exception {
        if (firstName == null || secondName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || baristaRank == null || e_mailAddress == null || phoneNumber == null || dateOfJoining == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkPhoneNumber(phoneNumber);
        checkE_mailAddress(e_mailAddress);
        return new Person(firstName, secondName, surname, sex, dateOfBirth, address, dateOfEmployment, baristaRank, e_mailAddress, phoneNumber, dateOfJoining);
    }

    private Person (String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, BaristaRank baristaRank, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) {
        this.firstName = firstName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.baristaRank = baristaRank;
        this.e_mailAddress = e_mailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        personKind.add(PersonType.EMPLOYEE);
        personKind.add(PersonType.BARISTA);
        personKind.add(PersonType.LOYALTYCLUBMEMBER);
    }

    public static Person createBaristaLoyaltyClubMember(String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, BaristaRank baristaRank, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) throws Exception {
        if (firstName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || baristaRank == null || e_mailAddress == null || phoneNumber == null || dateOfJoining == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkPhoneNumber(phoneNumber);
        checkE_mailAddress(e_mailAddress);
        return new Person(firstName, surname, sex, dateOfBirth, address, dateOfEmployment, baristaRank, e_mailAddress, phoneNumber, dateOfJoining);
    }

    // Konstruktory do tworzenia Kierownika zmiany + Członka klubu lojanościowego
    private Person (String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, int keySetNumber, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        baristaRank = BaristaRank.MASTER;
        this.keySetNumber = keySetNumber;
        this.e_mailAddress = e_mailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        personKind.add(PersonType.EMPLOYEE);
        personKind.add(PersonType.BARISTA);
        personKind.add(PersonType.SHIFTMANAGER);
        personKind.add(PersonType.LOYALTYCLUBMEMBER);
    }

    public static Person createShiftManagerLoyaltyClubMember(String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, Integer keySetNumber, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) throws Exception {
        if (firstName == null || secondName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || keySetNumber == null || e_mailAddress == null || phoneNumber == null || dateOfJoining == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkPhoneNumber(phoneNumber);
        checkE_mailAddress(e_mailAddress);
        return new Person(firstName, secondName, surname, sex, dateOfBirth, address, dateOfEmployment, keySetNumber, e_mailAddress, phoneNumber, dateOfJoining);
    }

    private Person (String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, int keySetNumber, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) {
        this.firstName = firstName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        baristaRank = BaristaRank.MASTER;
        this.keySetNumber = keySetNumber;
        this.e_mailAddress = e_mailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        personKind.add(PersonType.EMPLOYEE);
        personKind.add(PersonType.BARISTA);
        personKind.add(PersonType.SHIFTMANAGER);
        personKind.add(PersonType.LOYALTYCLUBMEMBER);
    }

    public static Person createShiftManagerLoyaltyClubMember(String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, Integer keySetNumber, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) throws Exception {
        if (firstName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || keySetNumber == null || e_mailAddress == null || phoneNumber == null || dateOfJoining == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkPhoneNumber(phoneNumber);
        checkE_mailAddress(e_mailAddress);
        return new Person(firstName, surname, sex, dateOfBirth, address, dateOfEmployment, keySetNumber, e_mailAddress, phoneNumber, dateOfJoining);
    }

    // Konstrutkory do tworzenia Managera + Członka klubu lojanościowego
    private Person (String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, String businessPhoneNumber, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.businessPhoneNumber = businessPhoneNumber;
        this.e_mailAddress = e_mailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        personKind.add(PersonType.EMPLOYEE);
        personKind.add(PersonType.MANAGER);
        personKind.add(PersonType.LOYALTYCLUBMEMBER);
    }

    public static Person createManagerLoyaltyClubMember(String firstName, String secondName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, String businessPhoneNumber, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) throws Exception {
        if (firstName == null || secondName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || businessPhoneNumber == null || e_mailAddress == null || phoneNumber == null || dateOfJoining == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkPhoneNumber(phoneNumber);
        checkPhoneNumber(businessPhoneNumber);
        checkE_mailAddress(e_mailAddress);
        return new Person(firstName, secondName, surname, sex, dateOfBirth, address, dateOfEmployment, businessPhoneNumber, e_mailAddress, phoneNumber, dateOfJoining);
    }

    private Person (String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, String businessPhoneNumber, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) {
        this.firstName = firstName;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.businessPhoneNumber = businessPhoneNumber;
        this.e_mailAddress = e_mailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        personKind.add(PersonType.EMPLOYEE);
        personKind.add(PersonType.MANAGER);
        personKind.add(PersonType.LOYALTYCLUBMEMBER);
    }

    public static Person createManagerLoyaltyClubMember(String firstName, String surname, Sex sex, LocalDate dateOfBirth, Address address, LocalDate dateOfEmployment, String businessPhoneNumber, String e_mailAddress, String phoneNumber, LocalDate dateOfJoining) throws Exception {
        if (firstName == null || surname == null || sex == null || dateOfBirth == null || address == null || dateOfEmployment == null || businessPhoneNumber == null || e_mailAddress == null || phoneNumber == null || dateOfJoining == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkPhoneNumber(phoneNumber);
        checkPhoneNumber(businessPhoneNumber);
        checkE_mailAddress(e_mailAddress);
        return new Person(firstName, surname, sex, dateOfBirth, address, dateOfEmployment, businessPhoneNumber, e_mailAddress, phoneNumber, dateOfJoining);
    }

    public Set<Contest> getContests() throws Exception {
        checkIfBarista();
        return contests;
    }

    public void addContest(Contest newContest) throws Exception {
        checkIfBarista();
        if (newContest == null) {
            throw new NotNullException("Can't add value of newContest, value can not be null");
        }
        if (!contests.contains(newContest)) {
            contests.add(newContest);
            newContest.addBarista(this);
        }
    }

    public void removeContest(Contest oldContest) throws Exception {
        checkIfBarista();
        if (contests.contains(oldContest)) {
            contests.remove(oldContest);
            oldContest.removeBarista(this);
        }
    }

    public Set<Contest> getContestsWon() throws Exception {
        checkIfBarista();
        return contestsWon;
    }

    public void addContestWon(Contest newWonContest) throws Exception {
        checkIfBarista();
        if (newWonContest == null) {
            throw new NotNullException("Can't add value of newContest, value can not be null");
        }
        if (!contests.contains(newWonContest)) {
            throw new Exception(String.format("Can not set contest: %s as won contest, because barista was not participant", newWonContest));
        }
        if (!contestsWon.contains(newWonContest)) {
            contestsWon.add(newWonContest);
            newWonContest.setWinner(this);
        }
    }

    public void removeContestWon(Contest oldWonContest) throws Exception {
        checkIfBarista();
        if (contestsWon.contains(oldWonContest)) {
            contestsWon.remove(oldWonContest);
            oldWonContest.removeWinner();
        }
        if (contestsWon.contains(oldWonContest)) {
            removeContestWon(oldWonContest);
        }
    }

    public Set<Order> getOrdersAssigned() throws Exception {
        checkIfBarista();
        return ordersAssigned;
    }

    public void addAssignedOrder(Order newOrder) throws Exception {
        checkIfBarista();
        if (newOrder == null) {
            throw new NotNullException("Can't add value of newOrder, value can not be null");
        }
        if (!ordersAssigned.contains(newOrder)) {
            ordersAssigned.add(newOrder);
            newOrder.setAssignedBarista(this);
        }
    }

    public void removeAssignedOrder(Order oldOrder) throws Exception {
        checkIfBarista();
        if (ordersAssigned.contains(oldOrder)) {
            ordersAssigned.remove(oldOrder);
            oldOrder.removeAssignedBarista();
        }
    }

    public Set<Order> getOrdersPlaced() throws Exception {
        checkIfLoyaltyClubMember();
        return ordersPlaced;
    }

    public void addOrderPlaced(Order newOrder) throws Exception {
        checkIfLoyaltyClubMember();
        if (newOrder == null) {
            throw new NotNullException("Can't add value of newOrder, value can not be null");
        }
        if (!ordersPlaced.contains(newOrder)) {
            ordersPlaced.add(newOrder);
            newOrder.setLoyaltyClubMember(this);
        }
    }

    public void removeOrderPlaced(Order oldOrder) throws Exception {
        checkIfLoyaltyClubMember();
        if (ordersPlaced.contains(oldOrder)) {
            ordersPlaced.remove(oldOrder);
            oldOrder.removeLoyaltyClubMember();
        }
    }

    public Set<Discount> getDiscounts() throws Exception {
        checkIfLoyaltyClubMember();
        return discounts;
    }

    public void addDiscount(Discount newDiscount) throws Exception {
        checkIfLoyaltyClubMember();
        if (newDiscount == null) {
            throw new NotNullException("Can't add value of newDiscount, value can not be null");
        }
        if (!discounts.contains(newDiscount)) {
            discounts.add(newDiscount);
            newDiscount.addLoyaltyClubMember(this);
        }
    }

    public void removeDiscount(Discount oldDiscount) throws Exception {
        checkIfLoyaltyClubMember();
        if (discounts.contains(oldDiscount)) {
            discounts.remove(oldDiscount);
            oldDiscount.removeLoyaltyClubMember(this);
        }
    }

    private void checkIfEmployee() throws Exception {
        if (!personKind.contains(PersonType.EMPLOYEE)) {
            throw new Exception("Can't make operation, this person is not Employee!");
        }
    }

    private void checkIfBarista() throws Exception {
        if (!personKind.contains(PersonType.BARISTA)) {
            throw new Exception("Can't make operation, this person is not Barista!");
        }
    }

    private void checkIfShiftManager() throws Exception {
        if (!personKind.contains(PersonType.SHIFTMANAGER)) {
            throw new Exception("Can't make operation, this person is not Shift manager!");
        }
    }

    private void checkIfManager() throws Exception {
        if (!personKind.contains(PersonType.MANAGER)) {
            throw new Exception("Can't make operation, this person is not Manager!");
        }
    }

    private void checkIfLoyaltyClubMember() throws Exception {
        if (!personKind.contains(PersonType.LOYALTYCLUBMEMBER)) {
            throw new Exception("Can't make operation, this person is not Loyalty club member!");
        }
    }

    private static void checkPhoneNumber(String phoneNumber) throws Exception {
        Pattern p = Pattern.compile("\\+\\d{11}");
        Matcher m = p.matcher(phoneNumber);
        if (!m.matches()) {
            throw new Exception(String.format("Invalid phoneNumber %s", phoneNumber));
        }
    }

    private static void checkE_mailAddress(String e_mailAddress) throws Exception {
        Pattern p = Pattern.compile("^\\S+@\\S+$");
        Matcher m = p.matcher(e_mailAddress);
        if (!m.matches()) {
            throw new Exception(String.format("Invalid e_mailAddress %s", e_mailAddress));
        }
    }

    // To chyba by działało lepiej, jak musi być bez kwalifikowanej to tak, ale to i tak lepsze niż w zniżce
    public Discount findDiscountByCode(String code) throws NotNullException {
        Discount result = null;
        for (Discount d : discounts) {
            if (d.getCode().equals(code)) {
                result = d;
            }
        }
        if (result == null) {
            throw new NotNullException(String.format("There are no discount with code: %s", code));
        }
        return result;
    }

    public static Set<Person> getActualLoyaltyClubMembers() {
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        Set<Person> result = new HashSet<>();

        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Person> queryPerson = criteriaBuilder.createQuery(Person.class);
            Root<Person> rootPerson = queryPerson.from(Person.class);
            queryPerson.select(rootPerson);

            List<Person> personList = session.createQuery(queryPerson).getResultList();

            for (Person p : personList) {
                if (p.getPersonKind().contains(PersonType.LOYALTYCLUBMEMBER) && p.getDateOfLeaving() != null) {
                    result.add(p);
                }
            }

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        finally {
            if(sessionFactory != null) {
                sessionFactory.close();
            }
        }
        return result;
    }

    public static Set<Person> getEmployees(LocalDate dateFrom, LocalDate dateTo) throws Exception {
        if (dateFrom.isAfter(dateTo)) {
            throw new Exception("Incorect dates, dateFrom is after dateTo");
        }

        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        Set<Person> result = new HashSet<>();

        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Person> queryPerson = criteriaBuilder.createQuery(Person.class);
            Root<Person> rootPerson = queryPerson.from(Person.class);
            queryPerson.select(rootPerson);

            List<Person> personList = session.createQuery(queryPerson).getResultList();

            for (Person p : personList) {
                if (p.getPersonKind().contains(PersonType.EMPLOYEE)) {
                    if (p.getDateOfFire() != null) {
                        if (!p.dateOfEmployment.isAfter(dateTo) && !p.getDateOfFire().isBefore(dateFrom)) {
                            result.add(p);
                        }
                    } else {
                        if (!p.dateOfEmployment.isAfter(dateTo)) {
                            result.add(p);
                        }
                    }
                }
            }

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        finally {
            if(sessionFactory != null) {
                sessionFactory.close();
            }
        }
        return result;
    }

    public static Set<Person> createBaristasStatement(LocalDate dateFrom, LocalDate dateTo) throws Exception {
        if (dateFrom.isAfter(dateTo)) {
            throw new Exception("Incorect dates, dateFrom is after dateTo");
        }

        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        Set<Person> result = new HashSet<>();

        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Person> queryPerson = criteriaBuilder.createQuery(Person.class);
            Root<Person> rootPerson = queryPerson.from(Person.class);
            queryPerson.select(rootPerson);

            List<Person> personList = session.createQuery(queryPerson).getResultList();

            for (Person p : personList) {
                if (p.getPersonKind().contains(PersonType.BARISTA)) {
                    if (p.getDateOfFire() != null) {
                        if (!p.dateOfEmployment.isAfter(dateTo) && !p.getDateOfFire().isBefore(dateFrom)) {
                            result.add(p);
                        }
                    } else {
                        if (!p.dateOfEmployment.isAfter(dateTo)) {
                            result.add(p);
                        }
                    }
                }
            }

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        finally {
            if(sessionFactory != null) {
                sessionFactory.close();
            }
        }
        return result;
    }

    @Transient
    public String getSeniority() throws Exception {
        checkIfEmployee();
        if (dateOfFire == null) {
            throw new NotNullException("Can't calculate it, because dateOfFire is not set, employee is still working!");
        }
        int years = dateOfFire.getYear() - dateOfEmployment.getYear();
        int month = dateOfFire.getMonthValue() - dateOfEmployment.getMonthValue();
        if (month < 0) {
            years -= 1;
            month = 12 - Math.abs(month);
        }
        return "Worked years: " + years + ", months: " + month;
    }

    public Long getId_Person() {
        return id_Person;
    }

    public Set<PersonType> getPersonKind() {
        return personKind;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws NotNullException {
        if (firstName == null) {
            throw new NotNullException("Can't set firstName, parameter is null");
        }
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws NotNullException {
        if (surname == null) {
            throw new NotNullException("Can't set surname, parameter is null");
        }
        this.surname = surname;
    }

    @Enumerated
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) throws NotNullException {
        if (sex == null) {
            throw new NotNullException("Can't set sex, parameter is null");
        }
        this.sex = sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) throws NotNullException {
        if (dateOfBirth == null) {
            throw new NotNullException("Can't set dateOfBirth, parameter is null");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) throws NotNullException {
        if (address == null) {
            throw new NotNullException("Can't set address, parameter is null");
        }
        this.address = address;
    }

    public LocalDate getDateOfEmployment() throws Exception {
        checkIfEmployee();
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) throws Exception {
        checkIfEmployee();
        if (dateOfEmployment == null) {
            throw new NotNullException("Can't set dateOfEmployment, parameter is null");
        }
        this.dateOfEmployment = dateOfEmployment;
    }

    public LocalDate getDateOfFire() throws Exception {
        checkIfEmployee();
        return dateOfFire;
    }

    public void setDateOfFire(LocalDate dateOfFire) throws Exception {
        checkIfEmployee();
        this.dateOfFire = dateOfFire;
    }

    public BaristaRank getBaristaRank() throws Exception {
        checkIfBarista();
        return baristaRank;
    }

    public void setBaristaRank(BaristaRank baristaRank) throws Exception {
        checkIfBarista();
        if (personKind.contains(PersonType.SHIFTMANAGER)) {
            throw new Exception("Can't set baristaRank, because it's Shift manager");
        }
        if (baristaRank == null) {
            throw new NotNullException("Can't set baristaRank, parameter is null");
        }
        this.baristaRank = baristaRank;
    }

    public int getKeySetNumber() throws Exception {
        checkIfShiftManager();
        return keySetNumber;
    }

    public void setKeySetNumber(Integer keySetNumber) throws Exception {
        checkIfShiftManager();
        if (keySetNumber == null) {
            throw new NotNullException("Can't set keySetNumber, parameter is null");
        }
        this.keySetNumber = keySetNumber;
    }

    public String getBusinessPhoneNumber() throws Exception {
        checkIfManager();
        return businessPhoneNumber;
    }

    public void setBusinessPhoneNumber(String businessPhoneNumber) throws Exception {
        checkIfManager();
        if (businessPhoneNumber == null) {
            throw new NotNullException("Can't set businessPhoneNumber, parameter is null");
        }
        this.businessPhoneNumber = businessPhoneNumber;
    }

    public String getE_mailAddress() throws Exception {
        checkIfLoyaltyClubMember();
        return e_mailAddress;
    }

    public void setE_mailAddress(String e_mailAddress) throws Exception {
        checkIfLoyaltyClubMember();
        if (e_mailAddress == null) {
            throw new NotNullException("Can't set e_mailAddress, parameter is null");
        }
        this.e_mailAddress = e_mailAddress;
    }

    public String getPhoneNumber() throws Exception {
        checkIfLoyaltyClubMember();
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws Exception {
        checkIfLoyaltyClubMember();
        if (phoneNumber == null) {
            throw new NotNullException("Can't set phoneNumber, parameter is null");
        }
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfJoining() throws Exception {
        checkIfLoyaltyClubMember();
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) throws Exception {
        checkIfLoyaltyClubMember();
        if (dateOfJoining == null) {
            throw new NotNullException("Can't set dateOfJoining, parameter is null");
        }
        this.dateOfJoining = dateOfJoining;
    }

    public LocalDate getDateOfLeaving() throws Exception {
        checkIfLoyaltyClubMember();
        return dateOfLeaving;
    }

    public void setDateOfLeaving(LocalDate dateOfLeaving) throws Exception {
        checkIfLoyaltyClubMember();
        if (dateOfLeaving == null) {
            throw new NotNullException("Can't set dateOfLeaving, parameter is null");
        }
        this.dateOfLeaving = dateOfLeaving;
    }

    @Override
    public String toString() {
        return "Pierwsze imie: " + firstName +
                (secondName!=null ? secondName : "") +
                ", nazwisko: " + surname;
    }

    @Override
    public boolean equals(Object obj) {
        Person p = (Person) obj;
        if (id_Person != null && p.getId_Person() != null) {
            return id_Person.equals(p.getId_Person());
        } else {
            return super.equals(obj);
        }
    }
}