package ua.rd.service;

import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.domain.entities.pizza.PizzaType;

public interface PizzaService extends GenericService<Pizza> {
    
    Pizza createPizza(String name, Double price, PizzaType type);
}
