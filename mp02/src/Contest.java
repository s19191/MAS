import com.google.common.base.Optional;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Contest implements Serializable {
    private String name;
    private int mainPrize;
    private int sumOfPrizes;
    private LocalDateTime dateOfTheEvent;
    private static LocalTime minTimeOfEvent = LocalTime.of(2, 30);
    private Address address;
    //    atrybut złożony
    private URL urlAddress;
    //    atrybut powtarzalny
    private Set<String> organizer;
    //    atrybut opcjonalny
    private Optional<String> description = Optional.absent();
    //    ekstensja
    private static List<Contest> extent = new ArrayList<>();
    //    asocjacja zwykła
    private List<Barista> baristas = new ArrayList<>();

    //    konstruktory
    private Contest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, URL urlAddress, String description) throws MalformedURLException {
        this.name = name;
        this.mainPrize = mainPrize;
        this.sumOfPrizes = sumOfPrizes;
        this.dateOfTheEvent = dateOfTheEvent;
        this.address = address;
        this.organizer = organizer;
        this.urlAddress = urlAddress;
        this.description = Optional.of(description);
        addContest(this);
    }

    private Contest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, URL urlAddress) throws MalformedURLException {
        this.name = name;
        this.mainPrize = mainPrize;
        this.sumOfPrizes = sumOfPrizes;
        this.dateOfTheEvent = dateOfTheEvent;
        this.address = address;
        this.organizer = organizer;
        this.urlAddress = urlAddress;
        addContest(this);
    }

    public static Contest createContest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, URL urlAddress, String description) throws NotNullException {
        if (name == null || mainPrize == null || sumOfPrizes == null || dateOfTheEvent == null || address == null || organizer == null || description == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Contest contest = null;
        try {
            contest = new Contest(name, mainPrize, sumOfPrizes, dateOfTheEvent, address, organizer, urlAddress, description);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return contest;
    }

    public static Contest createContest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, URL urlAddress) throws NotNullException {
        if (name == null || mainPrize == null || sumOfPrizes == null || dateOfTheEvent == null || address == null || organizer == null || urlAddress == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Contest contest = null;
        try {
            contest = new Contest(name, mainPrize, sumOfPrizes, dateOfTheEvent, address, organizer, urlAddress);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return contest;
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

    public String getDescription() {
        if (description.isPresent()) {
            return description.get();
        } else {
            return "description not set";
        }
    }

    public void setDescription(String description) {
        this.description = Optional.of(description);
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) throws NotNullException {
        if (address == null) {
            throw new NotNullException("Can't set value of address, value can not be null");
        }
        this.address = address;
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
                (description.isPresent() ? ", description=" + description.get() : ", description not set") + '\'' +
                ", address=" + address +
                ", organizer=" + organizer +
                ", urlAddress=" + urlAddress +
                '}';
    }


//    @Override
//    public String toString() {
//        return "Contest{" +
//                "name='" + name + '\'' +
//                ", mainPrize=" + mainPrize +
//                ", sumOfPrizes=" + sumOfPrizes +
//                ", dateOfTheEvent=" + dateOfTheEvent +
//                ", address=" + address +
//                ", urlAddress=" + urlAddress +
//                ", organizer=" + organizer +
//                (description.isPresent() ? ", description=" + description.get() : ", description not set") + '\'' +
//                ", baristas=" + baristas +
//                '}';
//    }
}