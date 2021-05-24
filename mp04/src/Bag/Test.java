package Bag;

import Atrybutu.NotNullException;

public class Test {
    public static void main(String[] args) {
        Beverage beverage01 = null;
        Beverage beverage02 = null;
        Ingredient ingredient01 = null;
        Ingredient ingredient02 = null;
        try {
            beverage01 = Beverage.createBeverage("Late", 20.0, "C01");
            beverage02 = Beverage.createBeverage("Cappuccino", 21.0, "C02");
            ingredient01 = Ingredient.createIngredient("Robusta", 20.0, "kg");
            ingredient02 = Ingredient.createIngredient("Arabica", 10.0, "kg");
            Composition composition01 = Composition.createComposition(0.1, 100.0, beverage01, ingredient01);
            Composition composition02 = Composition.createComposition(0.2, 200.0, beverage01, ingredient01);
        } catch (NotNullException e) {
            e.printStackTrace();
        }
    }
}
