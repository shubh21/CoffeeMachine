package com.practice.machine;

import com.practice.order.Coffee;
import com.practice.order.CoffeeOrder;
import com.practice.strategy.CoffeeBrewer;
import com.practice.strategy.CoffeeBrewerFactory;
import com.practice.strategy.EmptyCoffeeBrewer;

public class CoffeeMachine {

    private final CoffeeMachineCoordinator coffeeMachineCoordinator;

    public CoffeeMachine(CoffeeMachineCoordinator coffeeMachineCoordinator) {
        this.coffeeMachineCoordinator = coffeeMachineCoordinator;
    }


    public Coffee brewCoffee(CoffeeOrder order){

        if(coffeeMachineCoordinator.canBeBrewed(order)) {
            CoffeeBrewer coffeeBrewer = CoffeeBrewerFactory.getCoffeeBrewer(order);
            return coffeeBrewer.brew(order);
        }
        return new EmptyCoffeeBrewer().brew(order);
    }
}
