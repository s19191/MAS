import java.io.Serializable;
import java.util.Optional;

public class Address implements Serializable {
    private String country;
    private String voivodeship;
    private String district;
    private String community;
    private String town;
    private String street;
    private int houseNr;
    private Optional<Integer> apartmentNr = Optional.empty();
    private String postalCode;

    private Address(String country, String voivodeship, String district, String community, String town, String street, Integer houseNr, Integer apartmentNr, String postalCode) {
        this.country = country;
        this.voivodeship = voivodeship;
        this.district = district;
        this.community = community;
        this.town = town;
        this.street = street;
        this.houseNr = houseNr;
        this.apartmentNr = Optional.of(apartmentNr);
        this.postalCode = postalCode;
    }

    private Address(String country, String voivodeship, String district, String community, String town, String street, Integer houseNr, String postalCode) {
        this.country = country;
        this.voivodeship = voivodeship;
        this.district = district;
        this.community = community;
        this.town = town;
        this.street = street;
        this.houseNr = houseNr;
        this.postalCode = postalCode;
    }

    public static Address createAddress(String country, String voivodeship, String district, String community, String town, String street, Integer houseNr, Integer apartmentNr, String postalCode) throws NotNullException {
        if (country == null || voivodeship == null || district == null || community == null || town == null || street == null || houseNr == null || postalCode == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Address address = new Address(country, voivodeship, district, community, town, street, houseNr, apartmentNr, postalCode);
        return address;
    }

    public static Address createAddress(String country, String voivodeship, String district, String community, String town, String street, Integer houseNr, String postalCode) throws NotNullException {
        if (country == null || voivodeship == null || district == null || community == null || town == null || street == null || houseNr == null || postalCode == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Address address = new Address(country, voivodeship, district, community, town, street, houseNr, postalCode);
        return address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws NotNullException {
        if (country == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.country = country;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) throws NotNullException {
        if (voivodeship == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.voivodeship = voivodeship;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) throws NotNullException {
        if (district == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.district = district;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) throws NotNullException {
        if (community == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.community = community;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) throws NotNullException {
        if (town == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) throws NotNullException {
        if (street == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.street = street;
    }

    public int getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(Integer houseNr) throws NotNullException {
        if (houseNr == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.houseNr = houseNr;
    }

    public Integer getApartmentNr() {
        return apartmentNr.isPresent() ? apartmentNr.get() : null;
    }

    public void setApartmentNr(Optional<Integer> apartmentNr) {
        this.apartmentNr = apartmentNr;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) throws NotNullException {
        if (postalCode == null) {
            throw new NotNullException("Can't create object, value can not be null");
        }
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "country='" + country + '\'' +
                ", voivodeship='" + voivodeship + '\'' +
                ", district='" + district + '\'' +
                ", community='" + community + '\'' +
                ", town='" + town + '\'' +
                ", street='" + street + '\'' +
                ", houseNr=" + houseNr + '\'' +
                (apartmentNr.isPresent() ? ", apartmentNr=" + apartmentNr.get() : ", apartmentNr not set") + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}