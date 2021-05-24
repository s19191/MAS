package Xor;

import Atrybutu.Address;
import Atrybutu.NotNullException;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;
    private Address address;
    private String NIP;

    private XorRole xorRole;

    public boolean checkWhoSponsoring(XorRole xorRole) {
        if (xorRole == this.xorRole || this.xorRole == null) {
            return true;
        } else {
            return false;
        }
    }

    private List<CoffeeShop> sponsoredCoffeeShops = new ArrayList<>();
    private List<Contest> sponsoredContests = new ArrayList<>();

    public List<CoffeeShop> getSponsoredCoffeeShops() throws Exception {
        if (xorRole == XorRole.CONTEST) {
            throw new Exception("Can't get sponsored coffee shops, because this sponsor is sponsoring contests!");
        }
        if (xorRole == null) {
            throw new Exception("Not set who the company is sponsoring!");
        }
        return sponsoredCoffeeShops;
    }

    public void addSponsoredCoffeeShop(CoffeeShop newCoffeeShop) throws Exception {
        if (xorRole == XorRole.CONTEST) {
            throw new Exception("Can't add coffee shop to sponsored, because this sponsor is sponsoring contests!");
        }
        if (newCoffeeShop == null) {
            throw new NotNullException("Can't add value of barista, value can not be null");
        }
        if (xorRole == null) {
            xorRole = XorRole.COFFEESHOP;
        }
        if (!sponsoredCoffeeShops.contains(newCoffeeShop)) {
            sponsoredCoffeeShops.add(newCoffeeShop);
            newCoffeeShop.addSponsor(this);
        }
    }

    public void removeSponsoredCoffeeShop(CoffeeShop oldCoffeeShop) throws Exception {
        if (xorRole == XorRole.CONTEST) {
            throw new Exception("Can't remove sponsored coffee shops, because this sponsor is sponsoring contests!");
        }
        if (xorRole == null) {
            throw new Exception("Not set who the company is sponsoring!");
        }
        if (sponsoredCoffeeShops.contains(oldCoffeeShop)) {
            sponsoredCoffeeShops.remove(oldCoffeeShop);
            oldCoffeeShop.removeSponsor(this);
        }
    }

    public List<Contest> getSponsoredContests() throws Exception {
        if (xorRole == XorRole.COFFEESHOP) {
            throw new Exception("Can't get sponsored contests, because this sponsor is sponsoring coffee shops!");
        }
        if (xorRole == null) {
            throw new Exception("Not set who the company is sponsoring!");
        }
        return sponsoredContests;
    }

    public void addSponsoredContest(Contest newContest) throws Exception {
        if (xorRole == XorRole.COFFEESHOP) {
            throw new Exception("Can't add contest to sponsored, because this sponsor is sponsoring coffee shops!");
        }
        if (newContest == null) {
            throw new NotNullException("Can't add value of barista, value can not be null");
        }
        if (xorRole == null) {
            xorRole = XorRole.CONTEST;
        }
        if (!sponsoredContests.contains(newContest)) {
            sponsoredContests.add(newContest);
            newContest.addSponsor(this);
        }
    }

    public void removeSponsoredContest(Contest oldContest) throws Exception {
        if (xorRole == XorRole.COFFEESHOP) {
            throw new Exception("Can't remove sponsored contests, because this sponsor is sponsoring coffee shops!");
        }
        if (xorRole == null) {
            throw new Exception("Not set who the company is sponsoring!");
        }
        if (sponsoredContests.contains(oldContest)) {
            sponsoredContests.remove(oldContest);
            oldContest.removeSponsor(this);
        }
    }

    private Company(String name, Address address, String NIP) {
        this.name = name;
        this.address = address;
        this.NIP = NIP;
    }

    public static Company createCompany(String name, Address address, String NIP) throws NotNullException {
        if (name == null || address == null || NIP == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
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

    public void setNIP(String NIP) throws NotNullException {
        if (NIP == null) {
            throw new NotNullException("Can't set value of organizer, value can not be null");
        }
        this.NIP = NIP;
    }
}
