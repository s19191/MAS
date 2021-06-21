package classes;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Recipe> recipes = new HashSet<>();

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
        return new Ingredient(name, quantityOnStock, unit);
    }

    public Set<Recipe> getCompositions() {
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

    public Long getId_Ingredient() {
        return id_Ingredient;
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
        return "classes.Ingredient{" +
                "name='" + name + '\'' +
                ", quantityOnStock=" + quantityOnStock +
                ", unit='" + unit + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Ingredient i = (Ingredient) obj;
        if (id_Ingredient != null && i.getId_Ingredient() != null) {
            return id_Ingredient.equals(i.getId_Ingredient());
        } else {
            return super.equals(obj);
        }
    }
}