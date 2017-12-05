package ua.rd.repository.inmem;

import java.util.ArrayList;
import java.util.List;

import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.domain.entities.pizza.PizzaType;
import ua.rd.infrastructure.BenchMark;

import ua.rd.repository.GenericRepository;

public class InMemPizzaRepository implements GenericRepository<Pizza> {

    private List<Pizza> pizzas = new ArrayList<Pizza>();

    public void cookPizzas() {
        // this.pizzas.add(new Pizza(1, "Pizza 1", 5.39, PizzaType.VEGETERIAN));
        // this.pizzas.add(new Pizza(2, "Pizza 2", 4.45, PizzaType.SEA));
        // this.pizzas.add(new Pizza(3, "Pizza 3", 6.46, PizzaType.MEAT));
        // this.pizzas.add(new Pizza(4, "Pizza 4", 6.46, PizzaType.MEAT));
        // this.pizzas.add(new Pizza(5, "Pizza 5", 6.46, PizzaType.MEAT));
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @BenchMark
    public Pizza getById(Long id) {
        long index = id - 1;
        return pizzas.get((int)index);
    }

    @Override
    public Pizza save(Pizza pizza) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Pizza> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

  
}

