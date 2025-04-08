package com.practice.machine;

import com.practice.order.CoffeeOrder;
import com.practice.order.Ingredient;
import com.practice.order.Size;

public class CoffeeMachineCoordinator {

    private int coffeeBeans;
    private int milk;
    private int sugar;
    private int cream;
    private boolean garbageBinFull;

    public CoffeeMachineCoordinator(int coffeeBeans, int milk, int sugar, boolean garbageBinFull) {
        this.coffeeBeans = coffeeBeans;
        this.milk = milk;
        this.sugar = sugar;
        this.garbageBinFull = garbageBinFull;
    }

    public boolean canBeBrewed(CoffeeOrder order) {
        int beansNeeded = order.getSize() == Size.GRANDE ? 20 : 10;
        int milkNeeded = order.getIngredients().contains(Ingredient.MILK) ? (order.getSize() == Size.GRANDE ? 200 : 100) : 0;
        int sugarNeeded = order.getIngredients().contains(Ingredient.SUGAR) ? 10 : 0;
        return coffeeBeans >= beansNeeded && milk >= milkNeeded && sugar >= sugarNeeded && !garbageBinFull;
    }

    public void useResources(CoffeeOrder order) {
        int beansUsed = order.getSize() == Size.GRANDE ? 20 : 10;
        int milkUsed = order.getIngredients().contains(Ingredient.MILK) ? (order.getSize() == Size.GRANDE ? 200 : 100) : 0;
        int sugarUsed = order.getIngredients().contains(Ingredient.SUGAR) ? 10 : 0;
        coffeeBeans -= beansUsed;
        milk -= milkUsed;
        sugar -= sugarUsed;
        if (coffeeBeans < 50 || milk < 100) garbageBinFull = true;
    }

    public String getStatus() {
        if (coffeeBeans < 50) return "Low coffee beans";
        if (milk < 10) return "Low milk";
        if (sugar < 10) return "Low sugar";
        if (garbageBinFull) return "Empty garbage bin";
        return "Ready";
    }

    public boolean isMilkRefillRequired(){
        return milk < 10;
    }

    public boolean isSugarRefillRequired(){
        return sugar < 10;
    }

    public boolean isCreamRefillRequired(){
        return cream<5;
    }

    public boolean isBeanRefillRequired(){
        return coffeeBeans<10;
    }

    public void refillMilk(){

    }

    public void refillCream(){

    }

    public void refillBean(){

    }

    public void refillSugar(){

    }

    public void emptyGarbageBin(){

    }

    public boolean isGarbageBagFull() {
        return garbageBinFull;
    }

    public boolean isOperational() {
    }
}
