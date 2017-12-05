package ua.rd.repository;

import java.util.List;

import ua.rd.domain.entities.pizza.Pizza;

public interface PizzaRepository extends GenericRepository<Pizza>{

    List<Pizza> findAll();

}
