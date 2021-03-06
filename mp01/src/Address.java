import java.io.Serializable;

public class Address implements Serializable {
    private String country;
    private String voivodeship;
    private String district;
    private String community;
    private String town;
    private String street;
    private int houseNr;
    private Integer apartmentNr = null;
    private String postalCode;

    private Address(String country, String voivodeship, String district, String community, String town, String street, Integer houseNr, Integer apartmentNr, String postalCode) {
        this.country = country;
        this.voivodeship = voivodeship;
        this.district = district;
        this.community = community;
        this.town = town;
        this.street = street;
        this.houseNr = houseNr;
        this.apartmentNr = apartmentNr;
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
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.country = country;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) throws NotNullException {
        if (voivodeship == null) {
            throw new NotNullException("Can't set value of voivodeship, value can not be null");
        }
        this.voivodeship = voivodeship;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) throws NotNullException {
        if (district == null) {
            throw new NotNullException("Can't set value of district, value can not be null");
        }
        this.district = district;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) throws NotNullException {
        if (community == null) {
            throw new NotNullException("Can't set value of community, value can not be null");
        }
        this.community = community;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) throws NotNullException {
        if (town == null) {
            throw new NotNullException("Can't set value of town, value can not be null");
        }
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) throws NotNullException {
        if (street == null) {
            throw new NotNullException("Can't set value of street, value can not be null");
        }
        this.street = street;
    }

    public int getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(Integer houseNr) throws NotNullException {
        if (houseNr == null) {
            throw new NotNullException("Can't set value of houseNr, value can not be null");
        }
        this.houseNr = houseNr;
    }

    public Integer getApartmentNr() {
        return apartmentNr != null ? apartmentNr : null;
    }

    public void setApartmentNr(Integer apartmentNr) {
        this.apartmentNr = apartmentNr;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) throws NotNullException {
        if (postalCode == null) {
            throw new NotNullException("Can't set value of postalCode, value can not be null");
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
                (apartmentNr != null ? ", apartmentNr=" + apartmentNr : ", apartmentNr not set") + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}