package ua.rd.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.repository.PizzaRepository;

@Repository("pizzaRepository")
public class JPAPizzaRepository extends AbstractJPARepository<Pizza> implements PizzaRepository{

    public JPAPizzaRepository() {
        super();
    }

    public JPAPizzaRepository(EntityManager em) {
        super(em);
    }
    
    @Override
    public Pizza getById(Long id) {
        return super.getById(id);
    }

    @Override
    @Transactional
    public Pizza save(Pizza entity) {
        return super.save(entity);
    }

    @Override
    @Transactional
    public boolean remove(Long id) {
        return super.remove(id);
    }

    @Override
    public List<Pizza> findAll() {
        return super.findAll("findAllPizzas");
    }

    
}
