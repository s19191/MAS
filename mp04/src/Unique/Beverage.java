package Unique;

import Atrybutu.NotNullException;

import java.util.HashMap;
import java.util.Map;

public class Beverage {
    private String name;
    private double price;
    private String code;
    private static Map<String, Beverage> codeBeverage = new HashMap<>();

    private Beverage(String name, double price, String code) {
        this.name = name;
        this.price = price;
        this.code = code;
        codeBeverage.put(code, this);
    }

    public static Beverage createBeverage(String name, Double price, String code) throws Exception {
        if (name == null || price == null || code == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        if (codeBeverage.containsKey(code)) {
            throw new Exception(String.format("Can't create object, another beverage has code: %s", code));
        }
        Beverage beverage = new Beverage(name, price, code);
        return beverage;
    }

    public static Beverage findByCode(String code) throws Exception {
        if (!codeBeverage.containsKey(code)) {
            throw new Exception(String.format("There are no beverage with code: %s", code));
        }
        return codeBeverage.get(code);
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

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) throws NotNullException {
        if (price == null) {
            throw new NotNullException("Can't set value of ingredient, value can not be null");
        }
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws Exception {
        if (code == null) {
            throw new NotNullException("Can't set value of code, value can not be null");
        }
        if (codeBeverage.containsKey(code)) {
            throw new Exception(String.format("Another beverage have code: %s, unable to change", code));
        }
        codeBeverage.remove(this.code);
        this.code = code;
        codeBeverage.put(code, this);
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", code='" + code + '\'' +
                '}';
    }
}