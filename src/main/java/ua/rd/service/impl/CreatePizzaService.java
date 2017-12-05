package ua.rd.service.impl;

import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.domain.entities.pizza.PizzaType;

public class CreatePizzaService {
    
    public Pizza createPizza(Long id, String name, Double price, PizzaType type){  
        return new Pizza( id,  name,  price,  type);
    }
}       
