package com.practice.strategy;

import com.practice.order.Coffee;
import com.practice.order.CoffeeOrder;

public class EmptyCoffeeBrewer implements CoffeeBrewer{
    @Override
    public Coffee brew(CoffeeOrder coffeeOrder) {
        return null;
    }
}
