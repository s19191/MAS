import java.io.Serializable;

public class Address implements Serializable {
    private String country;
    private String voivodeship;
    private String district;
    private String community;
    private String town;
    private String street;
    private int houseNr;
    private Integer apartmentNr;
    private String postalCode;

    public Address(String country, String voivodeship, String district, String community, String town, String street, int houseNr, Integer apartmentNr, String postalCode) {
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

    public Address(String country, String voivodeship, String district, String community, String town, String street, int houseNr, String postalCode) {
        this.country = country;
        this.voivodeship = voivodeship;
        this.district = district;
        this.community = community;
        this.town = town;
        this.street = street;
        this.houseNr = houseNr;
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(int houseNr) {
        this.houseNr = houseNr;
    }

    public Integer getApartmentNr() {
        return apartmentNr;
    }

    public void setApartmentNr(Integer apartmentNr) {
        this.apartmentNr = apartmentNr;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
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
                (getApartmentNr() != null ? ", apartmentNr=" + getApartmentNr() : ", apartmentNr not set") + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}