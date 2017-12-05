package ua.rd.service.intergation;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ua.rd.domain.entities.customer.Address;
import ua.rd.domain.entities.customer.Customer;
import ua.rd.domain.entities.order.Order;
import ua.rd.domain.entities.order.StateChangeable;
import ua.rd.domain.entities.order.state.CancelState;
import ua.rd.domain.entities.order.state.DoneState;
import ua.rd.domain.entities.order.state.InProgressState;
import ua.rd.domain.entities.order.state.NewState;
import ua.rd.domain.entities.order.state.OrderStateContext;
import ua.rd.repository.PizzaRepository;
import ua.rd.service.OrderService;
import ua.rd.service.impl.SimpleOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/repositoryH2Context.xml")
public class SimpleOrderServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Autowired
    private PizzaRepository pizzaRep;
    

    
    @Test
    public void placeNewOrderTest() {
        
//        pizzaRep.save(new Pizza("Principessa", 5.45,PizzaType.MEAT));
//        pizzaRep.save(new Pizza("Quatro-Formaggi", 7.35,PizzaType.SEA));
//        pizzaRep.save(new Pizza("German", 3.55,PizzaType.MEAT));
//        pizzaRep.save(new Pizza("Salmonella", 4.25,PizzaType.SEA));
//        pizzaRep.save(new Pizza("AmoreMio", 4.25,PizzaType.MEAT));
        
        Customer customer = applicationContext.getBean(Customer.class);
        Address address = applicationContext.getBean(Address.class);
        OrderStateContext stateContext = applicationContext.getBean(OrderStateContext.class);
        Order order = orderService.placeNewOrder(customer, address, stateContext);

        final String sql = "SELECT * FROM ORDERS";

        List<Order> orders = jdbcTemplate.query(sql, new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                Order order = applicationContext.getBean(Order.class);
                Customer customer = applicationContext.getBean(Customer.class);
                Address address = applicationContext.getBean(Address.class);
                order.setId(rs.getLong("id"));
                order.setPizzas(new HashMap());

                customer.setId(rs.getLong("customer_id"));
                address.setId(rs.getLong("delivery_address_id"));

                order.setCustomer(customer);
                order.setDeliveryAddress(address);
                return order;
            }
        });

        assertEquals(order, orders.get(0));
    }
}
