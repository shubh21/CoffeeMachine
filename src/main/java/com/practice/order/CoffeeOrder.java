package com.practice.order;

import java.util.Set;

public class CoffeeOrder {
    private final CoffeeType coffeeType;
    private final Size size;
    private final Set<Ingredient> ingredients;


    public CoffeeOrder(CoffeeType coffeeType, Size size, Set<Ingredient> ingredients) {
        this.coffeeType = coffeeType;
        this.size = size;
        this.ingredients = ingredients;
        if (!isValid()) {
            throw new IllegalArgumentException("Invalid order: " + coffeeType + " with size " + size + " and ingredients " + ingredients);
        }
    }

    private boolean isValid() {
        return coffeeType.isSizeValid(size) && coffeeType.areIngredientsValid(ingredients);
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public Size getSize() {
        return size;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }
}
