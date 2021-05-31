package klasa;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class LoyaltyClubMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id_LoyaltyClubMember;
    @Basic
    private String name;
    @Basic
    private String surname;
    @Enumerated
    private Sex sex;
    @Basic
    private LocalDate dateOfBirth;
    @Basic
    private String addressMail;
    @Basic
    private String phoneNumber;
    @Basic
    private LocalDate dateOfJoining;
    @Basic
    private int numberOfStars;

    public LoyaltyClubMember() { }

    public LoyaltyClubMember(String name, String surname, Sex sex, LocalDate dateOfBirth, String addressMail, String phoneNumber, LocalDate dateOfJoining, int numberOfStars) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.addressMail = addressMail;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        this.numberOfStars = numberOfStars;
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

    @Enumerated
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) throws NotNullException {
        if (sex == null) {
            throw new NotNullException("Can't set value of sex, value can not be null");
        }
        this.sex = sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) throws NotNullException {
        if (dateOfBirth == null) {
            throw new NotNullException("Can't set value of dateOfBirth, value can not be null");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddressMail() {
        return addressMail;
    }

    public void setAddressMail(String addressMail) throws NotNullException {
        if (addressMail == null) {
            throw new NotNullException("Can't set value of addressMail, value can not be null");
        }
        this.addressMail = addressMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws NotNullException {
        if (phoneNumber == null) {
            throw new NotNullException("Can't set value of phoneNumber, value can not be null");
        }
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) throws NotNullException {
        if (dateOfJoining == null) {
            throw new NotNullException("Can't set value of dateOfJoining, value can not be null");
        }
        this.dateOfJoining = dateOfJoining;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) throws NotNullException {
        if (numberOfStars == null) {
            throw new NotNullException("Can't set value of numberOfStars, value can not be null");
        }
        this.numberOfStars = numberOfStars;
    }

    @Override
    public String toString() {
        return "LoyaltyClubMember{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", dateOfBirth=" + dateOfBirth +
                ", addressMail='" + addressMail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                ", numberOfStars=" + numberOfStars +
                '}';
    }
}