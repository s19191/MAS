package Xor;

import Atrybutu.Address;
import Atrybutu.NotNullException;
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
    private Address address;
    private URL urlAddress;
    private Set<String> organizer;
    private Optional<String> description = Optional.absent();
    private static List<Contest> extent = new ArrayList<>();

    private LocalTime timeOfEvent;

    private final static LocalTime minTimeOfEvent = LocalTime.of(2, 30);
    private final static LocalTime maxTimeOfEvent = LocalTime.of(8,0);

    List<Company> sponsors = new ArrayList<>();

    public List<Company> getSponsors() {
        return sponsors;
    }

    public void addSponsor(Company newSponsor) throws Exception {
        if (!newSponsor.checkWhoSponsoring(XorRole.CONTEST)) {
            throw new Exception("Can't add sponsor, because this sponsor is sponsoring coffee shops!");
        }
        if (newSponsor == null) {
            throw new NotNullException("Can't add value of barista, value can not be null");
        }
        if (!sponsors.contains(newSponsor)) {
            sponsors.add(newSponsor);
            newSponsor.addSponsoredContest(this);
        }
    }

    public void removeSponsor(Company oldSponsor) throws Exception {
        if (!oldSponsor.checkWhoSponsoring(XorRole.CONTEST)) {
            throw new Exception("Can't remove sponsor, because this sponsor is sponsoring coffee shops!");
        }
        if (sponsors.contains(oldSponsor)) {
            sponsors.remove(oldSponsor);
            oldSponsor.removeSponsoredContest(this);
        }
    }

    private Contest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, URL urlAddress, String description, LocalTime timeOfEvent) throws Exception {
        this.name = name;
        this.mainPrize = mainPrize;
        this.sumOfPrizes = sumOfPrizes;
        this.dateOfTheEvent = dateOfTheEvent;
        this.address = address;
        this.organizer = organizer;
        this.urlAddress = urlAddress;
        this.description = Optional.of(description);
        setTimeOfEvent(timeOfEvent);
        addContest(this);
    }

    private Contest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, URL urlAddress, LocalTime timeOfEvent) throws Exception {
        this.name = name;
        this.mainPrize = mainPrize;
        this.sumOfPrizes = sumOfPrizes;
        this.dateOfTheEvent = dateOfTheEvent;
        this.address = address;
        this.organizer = organizer;
        this.urlAddress = urlAddress;
        setTimeOfEvent(timeOfEvent);
        addContest(this);
    }

    public static Contest createContest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, URL urlAddress, String description, LocalTime timeOfEvent) throws NotNullException {
        if (name == null || mainPrize == null || sumOfPrizes == null || dateOfTheEvent == null || address == null || organizer == null || description == null || timeOfEvent == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Contest contest = null;
        try {
            contest = new Contest(name, mainPrize, sumOfPrizes, dateOfTheEvent, address, organizer, urlAddress, description, timeOfEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contest;
    }

    public static Contest createContest(String name, Integer mainPrize, Integer sumOfPrizes, LocalDateTime dateOfTheEvent, Address address, Set<String> organizer, URL urlAddress, LocalTime timeOfEvent) throws NotNullException {
        if (name == null || mainPrize == null || sumOfPrizes == null || dateOfTheEvent == null || address == null || organizer == null || urlAddress == null || timeOfEvent == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Contest contest = null;
        try {
            contest = new Contest(name, mainPrize, sumOfPrizes, dateOfTheEvent, address, organizer, urlAddress, timeOfEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contest;
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

    public static List<Contest> getContests(LocalDateTime since, LocalDateTime to) {
        return extent.stream().filter(contest -> contest.dateOfTheEvent.isAfter(since.minusDays(1)) && contest.dateOfTheEvent.isBefore(to)).collect(Collectors.toList());
    }

    public static List<Contest> getContests(LocalDateTime since) {
        return extent.stream().filter(contest -> contest.dateOfTheEvent.isAfter(since.minusDays(1)) && contest.dateOfTheEvent.isBefore(LocalDateTime.now())).collect(Collectors.toList());
    }

    public int getAmountOfRestOfThePrizes() {
        return sumOfPrizes - mainPrize;
    }

    public LocalDateTime getPredictedEndTime() {
        return dateOfTheEvent.plusHours(minTimeOfEvent.getHour()).plusMinutes(minTimeOfEvent.getMinute());
    }

    public String getBasicInformation() {
        return "Name of contest: " + getName() + ", description: " + getDescription() + ", url address: " + getUrlAddress();
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

    public String getDescription() {
        if (description.isPresent()) {
            return description.get();
        } else {
            return "description not set";
        }
    }

    public void setDescription(String description) throws NotNullException {
        this.description = Optional.of(description);
    }

    public static LocalTime getMinTimeOfEvent() {
        return minTimeOfEvent;
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

    public LocalTime getTimeOfEvent() {
        return timeOfEvent;
    }

    public void setTimeOfEvent(LocalTime timeOfEvent) throws Exception {
        if (timeOfEvent.isAfter(maxTimeOfEvent)) {
            throw new Exception(String.format("The timeOfEvent can not be longer than %x hours, %x minutes.", maxTimeOfEvent.getHour(), maxTimeOfEvent.getMinute()));
        }

        if (timeOfEvent.isBefore(minTimeOfEvent)) {
            throw new Exception(String.format("The timeOfEvent can not be shorter than %x hours, %x minutes.", minTimeOfEvent.getHour(), maxTimeOfEvent.getMinute()));
        }

        this.timeOfEvent = timeOfEvent;
    }

    private static void addContest(Contest contest) {
        extent.add(contest);
    }

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

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws  IOException, ClassNotFoundException {
        extent = (ArrayList<Contest>) stream.readObject();
    }

    @Override
    public String toString() {
        return "Contest{" +
                "name='" + name + '\'' +
                ", mainPrize=" + mainPrize +
                ", sumOfPrizes=" + sumOfPrizes +
                ", dateOfTheEvent=" + dateOfTheEvent +
                (description.isPresent() ? ", description=" + description.get() : ", description not set") + '\'' +
                ", description='" + description + '\'' +
                ", address=" + address +
                ", organizer=" + organizer +
                ", urlAddress=" + urlAddress +
                '}';
    }
}