package com.practice.order;

import com.practice.machine.CoffeeMachine;

public class PrepareCoffeeCommand implements CoffeeCommand{

    private final CoffeeMachine coffeeMachine;
    private final CoffeeOrder coffeeOrder;

    public PrepareCoffeeCommand(CoffeeMachine coffeeMachine, CoffeeOrder coffeeOrder) {
        this.coffeeMachine = coffeeMachine;
        this.coffeeOrder = coffeeOrder;
    }

    @Override
    public void execute() {
        coffeeMachine.brewCoffee(coffeeOrder);
    }
}
