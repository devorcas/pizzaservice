package ua.rd.repository;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;
import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.domain.entities.pizza.PizzaType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/repositoryH2Context.xml")
// @Rollback(true)
public class PizzaRepositoryH2Test extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    public void getPizzaByIdTest() {

        final String sql = "INSERT INTO PIZZAS (name, price, pizzaType) VALUES ('Vegan', '5.24' , 'MEAT')";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
        }, keyHolder);
        
        Long id = (long) keyHolder.getKey().intValue();
        Pizza pizza = pizzaRepository.getById(id);
        assertNotNull(pizza);
        System.out.println(pizza);
    }

    @Test
    public void savePizzaTest() {
        Pizza pizza = new Pizza();
        pizza.setName("tratata");
        pizza.setPrice(3.25);
        pizza.setType(PizzaType.MEAT);
        pizza = pizzaRepository.save(pizza);
        assertNotNull(pizza.getId());
    }
}
