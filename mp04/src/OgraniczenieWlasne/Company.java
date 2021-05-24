package OgraniczenieWlasne;

import Atrybutu.Address;
import Atrybutu.NotNullException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Company {
    private String name;
    private Address address;
    private String NIP;

    private Company(String name, Address address, String NIP) {
        this.name = name;
        this.address = address;
        this.NIP = NIP;
    }

    public static Company createCompany(String name, Address address, String NIP) throws Exception {
        if (name == null || address == null || NIP == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        if (!checkNIP(NIP)) {
            throw new Exception(String.format("Can't create object, NIP: %s is incorrect!", NIP));
        }
        Company company = new Company(name, address, NIP);
        return company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NotNullException {
        if (name == null) {
            throw new NotNullException("Can't set value of organizer, value can not be null");
        }
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) throws NotNullException {
        if (address == null) {
            throw new NotNullException("Can't set value of organizer, value can not be null");
        }
        this.address = address;
    }

    public String getNIP() {
        return NIP;
    }

    private static boolean checkNIP(String NIP) {
        Pattern pattern = Pattern.compile("PL\\d{9}");
        Matcher matcher = pattern.matcher(NIP);
        return matcher.matches();
    }

    public void setNIP(String NIP) throws Exception {
        if (NIP == null) {
            throw new NotNullException("Can't set value of organizer, value can not be null");
        }
        if (!checkNIP(NIP)) {
            throw new Exception(String.format("Can't set NIP: %s because it's incorrect!", NIP));
        }
        this.NIP = NIP;
    }
}
