package ua.rd.service.impl;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.domain.entities.pizza.PizzaType;
import ua.rd.repository.PizzaRepository;
import ua.rd.service.PizzaService;

@Service("pizzaService")
public class SimplePizzaService implements PizzaService, ApplicationContextAware {

    @Autowired
    private PizzaRepository pizzaRepsitory;

    private ApplicationContext applicationContext;

    public SimplePizzaService() {
        super();
    }

    @Override
    public Pizza createPizza(String name, Double price, PizzaType type) {
        Pizza pizza = applicationContext.getBean(Pizza.class);
        pizza.setName(name);
        pizza.setPrice(price);
        pizza.setType(type);
        return pizza;
    }

    @Override
    public Pizza getById(Long id) {
        return pizzaRepsitory.getById(id);
    }

    @Transactional
    @Override
    public Pizza save(Pizza entity) {
        return pizzaRepsitory.save(entity);
    }

    @Transactional
    @Override
    public boolean remove(Long id) {
        return pizzaRepsitory.remove(id);
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepsitory.findAll();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
