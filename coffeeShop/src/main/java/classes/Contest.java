package classes;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id_Contest;
    private String name;
    private int mainPrize;
    private int sumOfPrizes;
    private LocalDateTime dateOfTheEvent;
    private static LocalTime minTimeOfEvent = LocalTime.of(2, 30);
    @Embedded
    private Address address;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> organizer;
    private URL urlAddress;
    private String description;

    @ManyToMany(
            mappedBy = "contests",
            fetch = FetchType.EAGER
    )
    private List<Person> baristas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Person winner;

    public Contest() {}

    private Contest(String name, int mainPrize, int sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, URL urlAddress, String description) {
        this.name = name;
        this.mainPrize = mainPrize;
        this.sumOfPrizes = sumOfPrizes;
        this.dateOfTheEvent = dateOfTheEvent;
        this.address = address;
        this.organizer = organizer;
        this.urlAddress = urlAddress;
        this.description = description;
    }

    public static Contest createContest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, URL urlAddress, String description) throws Exception {
        if (name == null || mainPrize == null || sumOfPrizes == null || dateOfTheEvent == null || address == null || urlAddress == null || organizer == null || description == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkDescriptionLength(description);
        return new Contest(name, mainPrize, sumOfPrizes, dateOfTheEvent, address, organizer, urlAddress, description);
    }

    public List<Person> getBaristas() {
        return baristas;
    }

    public void addBarista(Person newBarista) throws Exception {
        if (newBarista == null) {
            throw new NotNullException("Can't add value of barista, value can not be null");
        }
        if (!newBarista.getPersonKind().contains(PersonType.BARISTA)) {
            throw new Exception("Can't add barista, because this person it's not Barista!");
        }
        if (!baristas.contains(newBarista)) {
            baristas.add(newBarista);
            newBarista.addContest(this);
        }
    }

    public void removeBarista(Person oldBarista) throws Exception {
        if (baristas.contains(oldBarista)) {
            baristas.remove(oldBarista);
            oldBarista.removeContest(this);
        }
    }

    public Person getWinner() {
        return winner;
    }

    public void setWinner(Person newWinner) throws Exception {
        if (newWinner == null) {
            throw new NotNullException("Can't add value of barista, value can not be null");
        }
        if (!newWinner.getPersonKind().contains(PersonType.BARISTA)) {
            throw new Exception("Can't set winner, because this person it's not Barista!");
        }
        if (!baristas.contains(newWinner)) {
            throw new Exception(String.format("Can not set barista: %s as winner, because he was not participant", newWinner));
        }
        if (newWinner != winner) {
            if (winner != null) {
                removeWinner();
            }
            winner = newWinner;
            newWinner.addContestWon(this);
        }
    }

    public void removeWinner() throws Exception {
        if (winner != null) {
            winner.removeContestWon(this);
            winner = null;
        }
    }

    public void addOrganizer(String organizer) throws NotNullException {
        if (organizer == null) {
            throw new NotNullException("Can't add organizer, value can not be null");
        }
        this.organizer.add(organizer);
    }

    public void addOrganizer(List<String> organizer) throws NotNullException {
        if (organizer == null) {
            throw new NotNullException("Can't add organizer, value can not be null");
        }
        this.organizer.addAll(organizer);
    }

    public void removeOrganizer(String organizer) {
        this.organizer.remove(organizer);
    }

    public void removeOrganizer(List<String> organizer) {
        this.organizer.removeAll(organizer);
    }

    @Transient
    public int getAmountOfRestOfThePrizes() {
        return sumOfPrizes - mainPrize;
    }

    public static void checkDescriptionLength(String description) throws Exception {
        if (description.length() > 300) {
            throw new Exception(String.format("Opis: %s jest przekracza długość 300 znaków", description));
        }
    }

    @Transient
    public LocalDateTime getPredictedEndTime() {
        return dateOfTheEvent.plusHours(minTimeOfEvent.getHour()).plusMinutes(minTimeOfEvent.getMinute());
    }

    @Transient
    public String getBasicInformation() {
        return "Name of contest: " + getName() + ", url address: " + getUrlAddress();
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

    public int getMainPrize() {
        return mainPrize;
    }

    public void setMainPrize(Integer mainPrize) throws NotNullException {
        if (mainPrize == null) {
            throw new NotNullException("Can't set value of mainPrize, value can not be null");
        }
        this.mainPrize = mainPrize;
    }

    public int getSumOfPrizes() {
        return sumOfPrizes;
    }

    public void setSumOfPrizes(Integer sumOfPrizes) throws NotNullException {
        if (sumOfPrizes == null) {
            throw new NotNullException("Can't set value of sumOfPrizes, value can not be null");
        }
        this.sumOfPrizes = sumOfPrizes;
    }

    public LocalDateTime getDateOfTheEvent() {
        return dateOfTheEvent;
    }

    public void setDateOfTheEvent(LocalDateTime dateOfTheEvent) throws NotNullException {
        if (dateOfTheEvent == null) {
            throw new NotNullException("Can't set value of dateOfTheEvent, value can not be null");
        }
        this.dateOfTheEvent = dateOfTheEvent;
    }

    public static LocalTime getMinTimeOfEvent() {
        return minTimeOfEvent;
    }

    public static void setMinTimeOfEvent(LocalTime minTimeOfEvent) throws NotNullException {
        if (minTimeOfEvent == null) {
            throw new NotNullException("Can't set value of minTimeOfEvent, value can not be null");
        }
        Contest.minTimeOfEvent = minTimeOfEvent;
    }

    public Set<String> getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Set<String> organizer) throws NotNullException {
        if (organizer == null) {
            throw new NotNullException("Can't set value of organizer, value can not be null");
        }
        this.organizer = organizer;
    }

    public String getUrlAddress() {
        return urlAddress.toString();
    }

    public void setUrlAddress(String urlAddress) throws NotNullException, MalformedURLException {
        if (urlAddress == null) {
            throw new NotNullException("Can't set value of organizer, value can not be null");
        }
        this.urlAddress = new URL(urlAddress);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws Exception {
        if (description == null) {
            throw new NotNullException("Can't set value of description, value can not be null");
        }
        checkDescriptionLength(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return "Konkurs: " + name + '\'' +
                ", główna nagroda: " + mainPrize +
                ", kiedy się odbywa: " + dateOfTheEvent;
    }
}