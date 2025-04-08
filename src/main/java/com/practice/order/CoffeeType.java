package com.practice.order;

import java.util.Collections;
import java.util.Set;

public enum CoffeeType {

    LATTE(Set.of(Size.GRANDE, Size.REGULAR, Size.TALL), Set.of(Ingredient.MILK), Set.of(Ingredient.SUGAR)),
    CAPPUCCINO(Set.of(Size.GRANDE, Size.REGULAR, Size.TALL), Set.of(Ingredient.MILK), Set.of(Ingredient.SUGAR)),
    FILTER_COFFEE(Set.of(Size.GRANDE, Size.REGULAR, Size.TALL), Set.of(), Set.of(Ingredient.MILK, Ingredient.SUGAR)),
    AMERICANO(Set.of(Size.GRANDE, Size.REGULAR, Size.TALL), Set.of(), Set.of()),
    ESPRESSO(Set.of(Size.GRANDE, Size.REGULAR), Collections.EMPTY_SET, Collections.EMPTY_SET);

    private final Set<Size> validSizes;
    private final Set<Ingredient> requiredIngredients;
    private final Set<Ingredient> optionalIngredients;

    CoffeeType(Set<Size> validSizes, Set<Ingredient> requiredIngredients, Set<Ingredient> optionalIngredients) {
        this.validSizes = validSizes;
        this.requiredIngredients = requiredIngredients;
        this.optionalIngredients = optionalIngredients;
    }

    public Set<Size> getValidSizes() {
        return validSizes;
    }

    public Set<Ingredient> getRequiredIngredients() {
        return requiredIngredients;
    }

    public Set<Ingredient> getOptionalIngredients() {
        return optionalIngredients;
    }

    public boolean isSizeValid(Size size){
        return validSizes.contains(size);
    }

    public boolean isIngredientRequired(Ingredient ingredient) {
        return requiredIngredients.contains(ingredient);
    }

    public boolean isIngredientOptional(Ingredient ingredient) {
        return optionalIngredients.contains(ingredient);
    }

    public boolean areIngredientsValid(Set<Ingredient> selectedIngredients) {
        // Must include all required ingredients
        if (!selectedIngredients.containsAll(requiredIngredients)) {
            return false;
        }
        // Can only include required or optional ingredients
        for (Ingredient ingredient : selectedIngredients) {
            if (!requiredIngredients.contains(ingredient) && !optionalIngredients.contains(ingredient)) {
                return false;
            }
        }
        return true;
    }
}
