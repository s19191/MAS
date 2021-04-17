import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Contest implements Serializable {
    String name;
    int mainPrize;
    int sumOfPrizes;
    LocalDateTime dateOfTheEvent;
    String description;
//    atrybut złożony
    Address address;
//    atrybut powtarzalny
    Set<String> organizer;
//    atrybut opcjonalny
    String urlAddress;
//    ekstensja
    private static List<Contest> extent = new ArrayList<>();

//    konstruktory
    public Contest(String name, int mainPrize, int sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, String urlAddress, String description) {
        this.name = name;
        this.mainPrize = mainPrize;
        this.sumOfPrizes = sumOfPrizes;
        this.dateOfTheEvent = dateOfTheEvent;
        this.address = address;
        this.organizer = organizer;
        this.urlAddress = urlAddress;
        this.description = description;
        addContest(this);
    }

    public Contest(String name, int mainPrize, int sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, String description) {
        this.name = name;
        this.mainPrize = mainPrize;
        this.sumOfPrizes = sumOfPrizes;
        this.dateOfTheEvent = dateOfTheEvent;
        this.address = address;
        this.organizer = organizer;
        this.description = description;
        addContest(this);
    }

//    przeciążenie nr.1
    public void addOrganizer(String organizer) {
        this.organizer.add(organizer);
    }

    public void addOrganizer(List<String> organizer) {
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

//    atrybut pochodny
    public int getAmountOfRestOfThePrizes() {
        return sumOfPrizes - mainPrize;
    }

//    gettery i settery
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMainPrize() {
        return mainPrize;
    }

    public void setMainPrize(int mainPrize) {
        this.mainPrize = mainPrize;
    }

    public int getSumOfPrizes() {
        return sumOfPrizes;
    }

    public void setSumOfPrizes(int sumOfPrizes) {
        this.sumOfPrizes = sumOfPrizes;
    }

    public LocalDateTime getDateOfTheEvent() {
        return dateOfTheEvent;
    }

    public void setDateOfTheEvent(LocalDateTime dateOfTheEvent) {
        this.dateOfTheEvent = dateOfTheEvent;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<String> getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Set<String> organizer) {
        this.organizer = organizer;
    }

    public String getUrlAddress() {
        return (urlAddress != null ? urlAddress : "urlAddress not set");
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                ", urlAddress='" + getUrlAddress() + '\'' +
                '}';
    }
}