package Bag;

import Atrybutu.NotNullException;

public class Composition {
    private double quantity;
    private double temperature;
    private Beverage beverage;
    private Ingredient ingredient;

    private Composition(double quantity, double temperature, Beverage beverage, Ingredient ingredient) throws NotNullException {
        this.quantity = quantity;
        this.temperature = temperature;
        setBeverage(beverage);
        setIngredient(ingredient);
    }

    public static Composition createComposition(Double quantity, Double temperature, Beverage beverage, Ingredient ingredient) throws NotNullException {
        if (quantity == null || temperature == null || beverage == null || ingredient == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Composition composition = new Composition(quantity, temperature, beverage, ingredient);
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
        if (newBeverage != beverage) {
            if (beverage != null) {
                removeBeverage();
            }
            beverage = newBeverage;
            newBeverage.addComposition(this);
        }
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
        if (newIngredient != ingredient) {
            if (ingredient != null) {
                removeIngredient();
            }
            ingredient = newIngredient;
            newIngredient.addComposition(this);
        }
    }

    public void removeIngredient() {
        if (ingredient != null) {
            ingredient.removeComposition(this);
            ingredient = null;
        }
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

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) throws NotNullException {
        if (temperature == null) {
            throw new NotNullException("Can't set value of temperature, value can not be null");
        }
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Composition{" +
                "quantity=" + quantity +
                ", temperature=" + temperature +
                '}';
    }
}