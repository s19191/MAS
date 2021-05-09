import com.google.common.base.Optional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Barista {
    private String name;
    private String surname;
    private Sex sex;
    private LocalDate dateOfEmployment;
    private Optional<LocalDate> dateOfFire = Optional.absent();
    private List<ContestResult> contestResults = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

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

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order newOrder) {
        if (!orders.contains(newOrder)) {
            orders.add(newOrder);
            newOrder.setAssignedBarista(this);
        }
    }

    public void removeOrder(Order oldOrder) {
        if (orders.contains(oldOrder)) {
            orders.remove(oldOrder);
            oldOrder.removeBarista();
        }
    }

    public List<ContestResult> getContestResults() {
        return contestResults;
    }

    public void addContestResult(ContestResult newContestResult) {
        if (!contestResults.contains(newContestResult)) {
            contestResults.add(newContestResult);
            newContestResult.setBarista(this);
        }
    }

    //    TODO: Tu coś jest nie tak, bo skoro ma być 1, no to nie możemy tak o usunąć tego
    public void removeContestResult(ContestResult oldContestResult) {
        if (!contestResults.contains(oldContestResult)) {
            contestResults.remove(oldContestResult);
            oldContestResult.removeBarista();
        }
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
                ", contestResults=" + contestResults +
                ", orders=" + orders +
                '}';
    }
}