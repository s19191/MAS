import com.google.common.base.Optional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Barista {
    private String name;
    private String surname;
    private Sex sex;
    private LocalDate dateOfEmployment;
    private Optional<LocalDate> dateOfFire = Optional.absent();
    //      asocjacja zwykła
    private List<Contest> contests = new ArrayList<>();
    //      asocjacja kwalifikowana
    private Map<Integer, Order> ordersQualif = new HashMap<>();

    private Barista(String name, String surname, Sex sex, LocalDate dateOfEmployment, LocalDate dateOfFire) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateOfEmployment = dateOfEmployment;
        this.dateOfFire = Optional.of(dateOfFire);
    }

    private Barista(String name, String surname, Sex sex, LocalDate dateOfEmployment) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateOfEmployment = dateOfEmployment;
    }

    public static Barista createBarista(String name, String surname, Sex sex, LocalDate dateOfEmployment, LocalDate dateOfFire) throws NotNullException {
        if (name == null || surname == null || sex == null || dateOfEmployment == null || dateOfFire == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Barista barista = new Barista(name, surname, sex, dateOfEmployment, dateOfFire);
        return barista;
    }

    public static Barista createBarista(String name, String surname, Sex sex, LocalDate dateOfEmployment) throws NotNullException {
        if (name == null || surname == null || sex == null || dateOfEmployment == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Barista barista = new Barista(name, surname, sex, dateOfEmployment);
        return barista;
    }

    //      Zarządzanie acocjacją
    public List<Contest> getContests() {
        return contests;
    }

    public void addContest(Contest newContest) throws NotNullException {
        if (newContest == null) {
            throw new NotNullException("Can't add value of newContest, value can not be null");
        }
        if (!contests.contains(newContest)) {
            contests.add(newContest);
            newContest.addBarista(this);
        }
    }

    public void removeContest(Contest oldContest) {
        if (contests.contains(oldContest)) {
            contests.remove(oldContest);
            oldContest.removeBarista(this);
        }
    }

    //      Zarządzanie acocjacją kwalifikowaną
    public void addOrderQualif(Order newOrder) throws NotNullException {
        if (newOrder == null) {
            throw new NotNullException("Can't add value of newOrder, value can not be null");
        }
        if (!ordersQualif.containsKey(newOrder.getOrderNr())) {
            ordersQualif.put(newOrder.getOrderNr(), newOrder);
            newOrder.addAssignedBarista(this);
        }
    }

    public void removeOrderQualif(Order oldOrder) {
        if (!ordersQualif.containsKey(oldOrder.getOrderNr())) {
            ordersQualif.remove(oldOrder.getOrderNr());
            oldOrder.removeBarista(this);
        }
    }

    public Order findOrderQualif(int orderNr) throws NotNullException{
        if (!ordersQualif.containsKey(orderNr)) {
            throw new NotNullException("Unable to find a order: " + orderNr);
        }
        return ordersQualif.get(orderNr);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NotNullException {
        if (name == null) {
            throw new NotNullException("Can't set value of name, value can not be null");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws NotNullException {
        if (surname == null) {
            throw new NotNullException("Can't set value of surname, value can not be null");
        }
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) throws NotNullException {
        if (sex == null) {
            throw new NotNullException("Can't set value of sex, value can not be null");
        }
        this.sex = sex;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) throws NotNullException {
        if (dateOfEmployment == null) {
            throw new NotNullException("Can't set value of dateOfEmployment, value can not be null");
        }
        this.dateOfEmployment = dateOfEmployment;
    }

    public LocalDate getDateOfFire() {
        if (dateOfFire.isPresent()) {
            return dateOfFire.get();
        } else {
            return null;
        }
    }

    public void setDateOfFire(LocalDate dateOfFire) {
        this.dateOfFire = Optional.of(dateOfFire);
    }

    @Override
    public String toString() {
        return "Barista{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", dateOfEmployment=" + dateOfEmployment +
                (dateOfFire.isPresent() ? ", dateOfFire=" + dateOfFire.get() : ", dateOfFire not set") + '\'' +
                '}';
    }
}