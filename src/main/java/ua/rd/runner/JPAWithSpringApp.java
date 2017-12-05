package ua.rd.runner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.rd.domain.entities.customer.AccumulativeCard;
import ua.rd.domain.entities.customer.Customer;
import ua.rd.domain.entities.customer.Address;
import ua.rd.domain.entities.order.Order;
import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.domain.entities.pizza.PizzaType;
import ua.rd.repository.GenericRepository;
import ua.rd.repository.OrderRepository;
import ua.rd.repository.PizzaRepository;
import ua.rd.service.OrderService;
import ua.rd.service.PizzaService;

public class JPAWithSpringApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext("repositoryMySQLContext.xml");
        //repositoryContext.getEnvironment().setActiveProfiles("dev");
        repositoryContext.refresh();

        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[] { "appContext.xml" }, false);

        appContext.setParent(repositoryContext);
        appContext.refresh();
        
        OrderService orderSer =   (OrderService) appContext.getBean("orderService");
        PizzaService pizzaSer =  (PizzaService) appContext.getBean("pizzaService");
        
        pizzaSer.save(new Pizza("Principessa", 5.45,PizzaType.MEAT));
        pizzaSer.save(new Pizza("Quatro-Formaggi", 7.35,PizzaType.SEA));
        pizzaSer.save(new Pizza("German", 3.55,PizzaType.MEAT));
        pizzaSer.save(new Pizza("Salmonella", 4.25,PizzaType.SEA));
        pizzaSer.save(new Pizza("AmoreMio", 4.25,PizzaType.MEAT));
        
        Set<Address> addresses = new HashSet<>();
        
        addresses.add(new Address("k14"));
        addresses.add(new Address("f30"));
        
        Customer customer = new Customer("Vasja",addresses, new AccumulativeCard(100.0));
        
        Map<Pizza, Integer> pizzaMap = new HashMap<>();
        
        pizzaMap.put(pizzaSer.getById(1L),2);
        pizzaMap.put(pizzaSer.getById(2L),1);
        pizzaMap.put(pizzaSer.getById(4L),2);
        pizzaMap.put(pizzaSer.getById(5L),2);
        
        Order order = appContext.getBean(Order.class);
        order.setCustomer(customer);
        
        order.setDeliveryAddress(new Address("k14"));
       
        order.calcPrice();
        orderSer.save(order);
        List<Pizza> pizzas = pizzaSer.findAll();
        System.out.println(pizzas);
       
    }
}
