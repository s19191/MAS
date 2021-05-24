package Xor;

import Atrybutu.Address;
import Atrybutu.NotNullException;

import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {
    private String name;
    private Address address;
    private List<String> menu = new ArrayList<>();

    private List<Company> sponsors = new ArrayList<>();

    public List<Company> getSponsors() {
        return sponsors;
    }

    public void addSponsor(Company newSponsor) throws Exception {
        if (!newSponsor.checkWhoSponsoring(XorRole.COFFEESHOP)) {
            throw new Exception("Can't add sponsor, because this sponsor is sponsoring contests!");
        }
        if (newSponsor == null) {
            throw new NotNullException("Can't add value of barista, value can not be null");
        }
        if (!sponsors.contains(newSponsor)) {
            sponsors.add(newSponsor);
            newSponsor.addSponsoredCoffeeShop(this);
        }
    }

    public void removeSponsor(Company oldSponsor) throws Exception {
        if (!oldSponsor.checkWhoSponsoring(XorRole.COFFEESHOP)) {
            throw new Exception("Can't remove sponsor, because this sponsor is sponsoring contests!");
        }
        if (sponsors.contains(oldSponsor)) {
            sponsors.remove(oldSponsor);
            oldSponsor.removeSponsoredCoffeeShop(this);
        }
    }

    private CoffeeShop(String name, Address address, List<String> menu) {
        this.name = name;
        this.address = address;
        this.menu = menu;
    }

    public static CoffeeShop createCoffeeShop(String name, Address address, List<String> menu) throws NotNullException {
        if (name == null || address == null || menu == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        CoffeeShop coffeeShop = new CoffeeShop(name, address, menu);
        return coffeeShop;
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

    public List<String> getMenu() {
        return menu;
    }

    public void setMenu(List<String> menu) throws NotNullException {
        if (menu == null) {
            throw new NotNullException("Can't set value of organizer, value can not be null");
        }
        this.menu = menu;
    }
}
