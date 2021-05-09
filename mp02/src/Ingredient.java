import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private String name;
    private double quantityOnStock;
    private List<Composition> compositions = new ArrayList<>();

    private Ingredient(String name, double quantityOnStock) {
        this.name = name;
        this.quantityOnStock = quantityOnStock;
    }

    public static Ingredient createIngredient(String name, Double quantityOnStock) throws NotNullException {
        if (name == null || quantityOnStock == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Ingredient ingredient = new Ingredient(name, quantityOnStock);
        return ingredient;
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
            newComposition.setIngredient(this);
        }
    }

    //    TODO: Tu coś jest nie tak, bo skoro ma być 1, no to nie możemy tak o usunąć tego
    public void removeComposition(Composition oldComposition) {
        if (!compositions.contains(oldComposition)) {
            compositions.remove(oldComposition);
            oldComposition.removeIngredient();
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
}
