import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.common.base.Optional;

public class Contest implements Serializable {
    private String name;
    private int mainPrize;
    private int sumOfPrizes;
    private LocalDateTime dateOfTheEvent;
    private String description;
    private static LocalTime minTimeOfEvent = LocalTime.of(2, 30);
//    atrybut złożony
    private Address address;
//    atrybut powtarzalny
    private Set<String> organizer;
//    atrybut opcjonalny
    private Optional<String> urlAddress = Optional.absent();
//    ekstensja
    private static List<Contest> extent = new ArrayList<>();

//    konstruktory
    private Contest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, String urlAddress, String description) {
        this.name = name;
        this.mainPrize = mainPrize;
        this.sumOfPrizes = sumOfPrizes;
        this.dateOfTheEvent = dateOfTheEvent;
        this.address = address;
        this.organizer = organizer;
        this.urlAddress = Optional.of(urlAddress);
        this.description = description;
        addContest(this);
    }

    private Contest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, String description) {
        this.name = name;
        this.mainPrize = mainPrize;
        this.sumOfPrizes = sumOfPrizes;
        this.dateOfTheEvent = dateOfTheEvent;
        this.address = address;
        this.organizer = organizer;
        this.description = description;
        addContest(this);
    }

    public static Contest createContest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, String urlAddress, String description) throws NotNullException {
        if (name == null || mainPrize == null || sumOfPrizes == null || dateOfTheEvent == null || address == null || organizer == null || description == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Contest contest = new Contest(name, mainPrize, sumOfPrizes, dateOfTheEvent, address, organizer, urlAddress, description);
        return contest;
    }

    public static Contest createContest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, String description) throws NotNullException {
        if (name == null || mainPrize == null || sumOfPrizes == null || dateOfTheEvent == null || address == null || organizer == null || description == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Contest contest = new Contest(name, mainPrize, sumOfPrizes, dateOfTheEvent, address, organizer, description);
        return contest;
    }

//    przeciążenie nr.1
    public void addOrganizer(String organizer) throws NotNullException {
        if (organizer == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.organizer.add(organizer);
    }

    public void addOrganizer(List<String> organizer) throws NotNullException {
        if (organizer == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.organizer.addAll(organizer);
    }

    public void removeOrganizer(String organizer) {
        this.organizer.remove(organizer);
    }

    public void removeOrganizer(List<String> organizer) {
        this.organizer.removeAll(organizer);
    }

//    przeciążenie nr.2 + metody klasowe
    public static List<Contest> getContests(LocalDateTime since, LocalDateTime to) {
        return extent.stream().filter(contest -> contest.dateOfTheEvent.isAfter(since.minusDays(1)) && contest.dateOfTheEvent.isBefore(to)).collect(Collectors.toList());
    }

    public static List<Contest> getContests(LocalDateTime since) {
        return extent.stream().filter(contest -> contest.dateOfTheEvent.isAfter(since.minusDays(1)) && contest.dateOfTheEvent.isBefore(LocalDateTime.now())).collect(Collectors.toList());
    }

//    atrybut pochodny nr.1
    public int getAmountOfRestOfThePrizes() {
        return sumOfPrizes - mainPrize;
    }

//    atrybut pochodny nr.2
    public LocalDateTime getPredictedEndTime() {
        return dateOfTheEvent.plusHours(minTimeOfEvent.getHour()).plusMinutes(minTimeOfEvent.getMinute());
    }

    public String getBasicInformation() {
        return "Name of contest: " + getName() + ", description: " + getDescription() + ", url address: " + getUrlAddress();
    }

//    gettery i settery
    public String getName() {
        return name;
    }

    public void setName(String name) throws NotNullException {
        if (name == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.name = name;
    }

    public int getMainPrize() {
        return mainPrize;
    }

    public void setMainPrize(Integer mainPrize) throws NotNullException {
        if (mainPrize == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.mainPrize = mainPrize;
    }

    public int getSumOfPrizes() {
        return sumOfPrizes;
    }

    public void setSumOfPrizes(Integer sumOfPrizes) throws NotNullException {
        if (sumOfPrizes == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.sumOfPrizes = sumOfPrizes;
    }

    public LocalDateTime getDateOfTheEvent() {
        return dateOfTheEvent;
    }

    public void setDateOfTheEvent(LocalDateTime dateOfTheEvent) throws NotNullException {
        if (dateOfTheEvent == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.dateOfTheEvent = dateOfTheEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws NotNullException {
        if (description == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.description = description;
    }

    public static LocalTime getMinTimeOfEvent() {
        return minTimeOfEvent;
    }

    public static void setMinTimeOfEvent(LocalTime minTimeOfEvent) throws NotNullException {
        if (minTimeOfEvent == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        Contest.minTimeOfEvent = minTimeOfEvent;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) throws NotNullException {
        if (address == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.address = address;
    }

    public Set<String> getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Set<String> organizer) throws NotNullException {
        if (organizer == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.organizer = organizer;
    }

    public String getUrlAddress() {
        if (urlAddress.isPresent()) {
            return urlAddress.get();
        } else {
            return "urlAddress not set";
        }
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = Optional.of(urlAddress);
    }

//    dodawanie do ekstensji
    private static void addContest(Contest contest) {
        extent.add(contest);
    }

//    usuwanie z ekstensji
    private static void removeContest(Contest contest) {
        extent.remove(contest);
    }

    public static void showExtent() {
        System.out.println("Extent of the class: " + Contest.class.getName());

        if (extent.isEmpty()) {
            System.out.println("Extent is empty");
        } else {
            for (Contest contest : extent) {
                System.out.println(contest);
            }
        }
    }

    public static void clearExtent() {
        extent.clear();
    }

//    utrwalenie ekstensji
    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

//    odczytywanie ekstensji
    public static void readExtent(ObjectInputStream stream) throws  IOException, ClassNotFoundException {
        extent = (ArrayList<Contest>) stream.readObject();
    }

//    przesłonięcie
    @Override
    public String toString() {
        return "Contest{" +
                "name='" + name + '\'' +
                ", mainPrize=" + mainPrize +
                ", sumOfPrizes=" + sumOfPrizes +
                ", dateOfTheEvent=" + dateOfTheEvent +
                ", description='" + description + '\'' +
                ", address=" + address +
                ", organizer=" + organizer +
                (urlAddress.isPresent() ? ", urlAddress=" + urlAddress.get() : ", urlAddress not set") + '\'' +
                '}';
    }
}