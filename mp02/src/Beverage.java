import java.util.ArrayList;
import java.util.List;

public class Beverage {
    private String name;
    private double price;
    private String code;
    private List<Composition> compositions = new ArrayList<>();

    private Beverage(String name, double price, String code) {
        this.name = name;
        this.price = price;
        this.code = code;
    }

    public static Beverage createBeverage(String name, Double price, String code) throws NotNullException {
        if (name == null || price == null || code == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Beverage beverage = new Beverage(name, price, code);
        return beverage;
    }

    public List<Composition> getCompositions() {
        return compositions;
    }

    //    zarządzanie asocjacją z atrybutem
    public void addComposition(Composition newComposition) throws NotNullException {
        if (newComposition == null) {
            throw new NotNullException("Can't set value of newComposition, value can not be null");
        }
        if (!compositions.contains(newComposition)) {
            compositions.add(newComposition);
            newComposition.setBeverage(this);
        }
    }

    public void removeComposition(Composition oldComposition) {
        if (compositions.contains(oldComposition)) {
            compositions.remove(oldComposition);
            oldComposition.removeBeverage();
        }
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

    public void setCode(String code) throws NotNullException {
        if (code == null) {
            throw new NotNullException("Can't set value of code, value can not be null");
        }
        this.code = code;
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", code='" + code + '\'' +
                ", compositions=" + compositions +
                '}';
    }
}