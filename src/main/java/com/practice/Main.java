package com.practice;

import com.practice.machine.CoffeeMachine;
import com.practice.machine.CoffeeMachineCoordinator;
import com.practice.order.*;

import java.util.Set;

public class Main {

    public static void main(String[] args) {

        ResourceConfig config = getResourceConfig();

        CoffeeMachineCoordinator coordinator = new CoffeeMachineCoordinator(config);
        CoffeeMachine coffeeMachine = new CoffeeMachine(coordinator);

        while (true) {
            if (coordinator.isOperational()) {
                System.out.println("Status: " + coordinator.getStatus()); // Early feedback
                CoffeeOrder order = getCoffeeOrder();
                if (coordinator.canBeBrewed(order)) {
                    try {
                        CoffeeCommand command = new PrepareCoffeeCommand(coffeeMachine, order);
                        command.execute();
                        System.out.println("Coffee brewed!");
                    } catch (IllegalStateException e) {
                        System.out.println("Brewing failed: " + e.getMessage());
                    }
                } else {
                    System.out.println("Cannot brew: " + coordinator.getStatus());
                    waitForRefill(coordinator);
                }
            } else {
                waitForRefill(coordinator);
            }
        }
    }

    private static ResourceConfig getResourceConfig() {
       return new ResourceConfig(Map.of(
                "beans", new ResourceState(500, 50), // total, threshold
                "milk", new ResourceState(1000, 100),
                "sugar", new ResourceState(200, 20)
        ));
    }

    private static void waitForRefill(CoffeeMachineCoordinator coordinator) {
        int maxRetries = 3;
        int timeoutMs = 5000; // 5 seconds for demo (real: 5 min)
        int retriesLeft = maxRetries;

        System.out.println("Machine on standby: " + coordinator.getStatus());
        while (retriesLeft > 0) {
            try {
                refill(coordinator);
                Thread.sleep(timeoutMs);// Simulate timeout
                if (coordinator.isOperational()) {
                    System.out.println("Resources restored, resuming...");
                    return;
                }
                retriesLeft--;
                System.out.println("Retry " + (maxRetries - retriesLeft) + "/" + maxRetries + ": " + coordinator.getStatus());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Max retries reached. Machine stopped. Press enter to resume...");
        throw new RuntimeException("Invalid state");
    }

    private static CoffeeOrder getCoffeeOrder() {
        return new CoffeeOrder(CoffeeType.LATTE, Size.REGULAR, Set.of(Ingredient.SUGAR, Ingredient.MILK));
    }

    private static void refill(CoffeeMachineCoordinator coffeeMachineCoordinator) {
        if (coffeeMachineCoordinator.isMilkRefillRequired()) {
            coffeeMachineCoordinator.refillMilk();
        }
        if (coffeeMachineCoordinator.isSugarRefillRequired()) {
            coffeeMachineCoordinator.refillSugar();
        }
        if (coffeeMachineCoordinator.isCreamRefillRequired()) {
            coffeeMachineCoordinator.refillCream();
        }
        if (coffeeMachineCoordinator.isBeanRefillRequired()) {
            coffeeMachineCoordinator.refillBean();
        }
        if (coffeeMachineCoordinator.isGarbageBagFull()) {
            coffeeMachineCoordinator.emptyGarbageBin();
        }
    }
}