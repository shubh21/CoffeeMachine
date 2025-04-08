package com.practice.strategy;

import com.practice.order.CoffeeOrder;

public class CoffeeBrewerFactory {

    public static CoffeeBrewer getCoffeeBrewer(CoffeeOrder order){

        switch(order.getCoffeeType()){
            case LATTE -> {
                return new LatteCoffeeBrewer();
            }
            case CAPPUCCINO ->{
                return new CappuccinoCoffeeBrewer();
            }
            case ESPRESSO -> {
                return new EspressoCoffeeBrewer();
            }
            case AMERICANO -> {
                return new AmericanoCoffeeBrewer();
            }
            case FILTER_COFFEE -> {
                return new FilterCoffeeBrewer();
            }
            default ->{
                return new EmptyCoffeeBrewer();
            }

        }
    }
}
