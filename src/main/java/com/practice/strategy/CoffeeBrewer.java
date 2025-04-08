package com.practice.strategy;

import com.practice.order.Coffee;
import com.practice.order.CoffeeOrder;

public interface CoffeeBrewer {

    Coffee brew(CoffeeOrder coffeeOrder);
}
