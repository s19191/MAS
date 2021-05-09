public class Composition {
    private String unit;
    private double quantity;
    private Beverage beverage;
    private Ingredient ingredient;

    private Composition(String unit, double quantity, Beverage beverage, Ingredient ingredient) {
        this.unit = unit;
        this.quantity = quantity;
        this.beverage = beverage;
        this.ingredient = ingredient;
    }

    public static Composition createOrder(String unit, Double quantity, Beverage beverage, Ingredient ingredient) throws NotNullException {
        if (unit == null || quantity == null || beverage == null || ingredient == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Composition composition = new Composition(unit, quantity, beverage, ingredient);
        return composition;
    }

    //    zarządzanie asocjacją z atrybutem - Baverage
    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage newBeverage) throws NotNullException {
        if (newBeverage == null) {
            throw new NotNullException("Can't set value of beverage, value can not be null");
        }
        if (beverage != null) {
            removeBeverage();
        }
        beverage = newBeverage;
        newBeverage.addComposition(this);
    }

    public void removeBeverage() {
        if (beverage != null) {
            beverage.removeComposition(this);
            beverage = null;
        }
    }

    //    zarządzanie asocjacją z atrybutem - Ingredient
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient newIngredient) throws NotNullException {
        if (newIngredient == null) {
            throw new NotNullException("Can't set value of ingredient, value can not be null");
        }
        if (ingredient != null) {
            removeIngredient();
        }
        ingredient = newIngredient;
        newIngredient.addComposition(this);
    }

    public void removeIngredient() {
        if (ingredient != null) {
            ingredient.removeComposition(this);
            ingredient = null;
        }
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) throws NotNullException {
        if (quantity == null) {
            throw new NotNullException("Can't set value of quantity, value can not be null");
        }
        this.quantity = quantity;
    }
}
