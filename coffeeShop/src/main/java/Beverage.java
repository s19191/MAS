import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Beverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id_Beverage;
    private String name;
    private double price;
    private String code;
    @OneToMany(
            mappedBy = "beverage",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Recipe> recipes = new ArrayList<>();

    @ManyToMany(mappedBy = "beverages")
    private List<Order> orders = new ArrayList<>();

    public Beverage() {}

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

    public List<Recipe> getCompositions() {
        return recipes;
    }

    //    zarządzanie asocjacją z atrybutem
    public void addComposition(Recipe newRecipe) throws NotNullException {
        if (newRecipe == null) {
            throw new NotNullException("Can't set value of newComposition, value can not be null");
        }
        if (!recipes.contains(newRecipe)) {
            recipes.add(newRecipe);
            newRecipe.setBeverage(this);
        }
    }

    //    TODO: Tu coś jest nie tak, bo skoro ma być 1, no to nie możemy tak o usunąć tego
    public void removeComposition(Recipe oldRecipe) {
        if (recipes.contains(oldRecipe)) {
            recipes.remove(oldRecipe);
            oldRecipe.removeBeverage();
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order newOrder) throws Exception {
        if (newOrder == null) {
            throw new NotNullException("Can't add value of order, value can not be null");
        }
        if (!orders.contains(newOrder)) {
            orders.add(newOrder);
            newOrder.addBeverage(this);
        }
    }

    public void removeOrder(Order oldOrder) {
        if (orders.contains(oldOrder)) {
            orders.remove(oldOrder);
            oldOrder.removeOrder(this);
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
                ", compositions=" + recipes +
                '}';
    }
}