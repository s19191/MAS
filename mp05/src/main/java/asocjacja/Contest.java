package asocjacja;

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
    @Basic
    private String name;
    @Basic
    private int mainPrize;
    @Basic
    private int sumOfPrizes;
    @Basic
    private LocalDateTime dateOfTheEvent;
    @Basic
    private static LocalTime minTimeOfEvent = LocalTime.of(2, 30);
    //TODO: atrybut złożony
    private URL urlAddress;
    @ElementCollection
    private Set<String> organizer;
    @ManyToMany(mappedBy = "contests")
    private final List<Barista> baristas = new ArrayList<>();

    public Contest() { }

    public Contest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Set<String> organizer, URL urlAddress) throws MalformedURLException {
        this.name = name;
        this.mainPrize = mainPrize;
        this.sumOfPrizes = sumOfPrizes;
        this.dateOfTheEvent = dateOfTheEvent;
        this.organizer = organizer;
        this.urlAddress = urlAddress;
    }

    //    zarządzanie asocjacją zwykłą
    public List<Barista> getBaristas() {
        return baristas;
    }

    public void addBarista(Barista newBarista) throws NotNullException {
        if (newBarista == null) {
            throw new NotNullException("Can't add value of barista, value can not be null");
        }
        if (!baristas.contains(newBarista)) {
            baristas.add(newBarista);
            newBarista.addContest(this);
        }
    }

    public void removeBarista(Barista oldBarista) {
        if (baristas.contains(oldBarista)) {
            baristas.remove(oldBarista);
            oldBarista.removeContest(this);
        }
    }

    //    przeciążenie nr.1
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

    @Override
    public String toString() {
        return "asocjacja.Contest{" +
                "name='" + name + '\'' +
                ", mainPrize=" + mainPrize +
                ", sumOfPrizes=" + sumOfPrizes +
                ", dateOfTheEvent=" + dateOfTheEvent +
                ", organizer=" + organizer +
                ", urlAddress=" + urlAddress +
                '}';
    }
}