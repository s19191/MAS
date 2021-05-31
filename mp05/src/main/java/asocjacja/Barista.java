package asocjacja;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Barista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id;
    @Basic
    private String name;
    @Basic
    private String surname;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Basic
    private LocalDate dateOfEmployment;
    @ManyToMany(
            mappedBy = "contest",
            cascade = CascadeType.ALL
    )
    private final List<Contest> contests = new ArrayList<>();

    public Barista() { }

    public Barista(String name, String surname, Sex sex, LocalDate dateOfEmployment) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateOfEmployment = dateOfEmployment;
    }

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

    @Enumerated(EnumType.STRING)
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

    @Override
    public String toString() {
        return "asocjacja.Barista{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", dateOfEmployment=" + dateOfEmployment +
                ", contests=" + contests +
                '}';
    }
}