import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id_Ingredient;
    private String name;
    private double quantityOnStock;
    private String unit;
    @OneToMany(
            mappedBy = "ingredient"
    )
    private List<Recipe> recipes = new ArrayList<>();

    public Ingredient() {}

    private Ingredient(String name, double quantityOnStock, String unit) {
        this.name = name;
        this.quantityOnStock = quantityOnStock;
        this.unit = unit;
    }

    public static Ingredient createIngredient(String name, Double quantityOnStock, String unit) throws NotNullException {
        if (name == null || quantityOnStock == null || unit == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Ingredient ingredient = new Ingredient(name, quantityOnStock, unit);
        return ingredient;
    }

    //    zarządzanie asocjacją z atrybutem
    public List<Recipe> getCompositions() {
        return recipes;
    }

    public void addComposition(Recipe newRecipe) throws NotNullException {
        if (newRecipe == null) {
            throw new NotNullException("Can't set value of newComposition, value can not be null");
        }
        if (!recipes.contains(newRecipe)) {
            recipes.add(newRecipe);
            newRecipe.setIngredient(this);
        }
    }

    //    TODO: Tu coś jest nie tak, bo skoro ma być 1, no to nie możemy tak o usunąć tego
    public void removeComposition(Recipe oldRecipe) {
        if (recipes.contains(oldRecipe)) {
            recipes.remove(oldRecipe);
            oldRecipe.removeIngredient();
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

    public double getQuantityOnStock() {
        return quantityOnStock;
    }

    public void setQuantityOnStock(Double quantityOnStock) throws NotNullException {
        if (quantityOnStock == null) {
            throw new NotNullException("Can't set value of quantityOnStock, value can not be null");
        }
        this.quantityOnStock = quantityOnStock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) throws NotNullException {
        if (unit == null) {
            throw new NotNullException("Can't set value of unit, value can not be null");
        }
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", quantityOnStock=" + quantityOnStock +
                ", compositions=" + recipes +
                '}';
    }
}