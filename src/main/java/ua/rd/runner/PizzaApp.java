package ua.rd.runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.rd.domain.discount.AccumulateCardDiscount;
import ua.rd.domain.discount.QuantityPizzaDiscount;
import ua.rd.domain.entities.customer.AccumulativeCard;
import ua.rd.domain.entities.customer.Address;
import ua.rd.domain.entities.customer.Customer;
import ua.rd.domain.entities.order.Order;
import ua.rd.domain.entities.order.state.OrderStateContext;
import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.repository.GenericRepository;
import ua.rd.service.Discountable;
import ua.rd.service.OrderService;

public class PizzaApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext("repositoryContext.xml");
        repositoryContext.getEnvironment().setActiveProfiles("dev");
        repositoryContext.refresh();

        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[] { "appContext.xml" }, false);

        appContext.setParent(repositoryContext);
        appContext.refresh();

        Customer customer = (Customer) appContext.getBean("customer");
        OrderService orderService = (OrderService) appContext.getBean("orderService");
        GenericRepository<Pizza> pizzaService = (GenericRepository<Pizza>) appContext.getBean("pizzaRepository");

        // add to container
        Address address = new Address("k14");
        OrderStateContext stateContext = new OrderStateContext();
        Order order = orderService.placeNewOrder(customer, address, stateContext, 1L, 2L, 3L, 4L, 5L);

        AccumulativeCard accCard = (AccumulativeCard) appContext.getBean("accCard");
        Discountable discount = new AccumulateCardDiscount(new QuantityPizzaDiscount(order), accCard);

        System.out.println(order);
        System.out.println("Price without discounts " + order.calcPrice());
        System.out.println("Price with discounts " + discount.calcPriceWithDiscount());

        Pizza pizza = appContext.getBean(Pizza.class);
        System.out.println(pizza);

        ApplicationContext parent = appContext.getParent();
        System.out.println("Parent context: " + parent);

        Customer customerApp = (Customer) appContext.getBean("customer");
        Customer customerRep = (Customer) repositoryContext.getBean("customer");

        System.out.println("customerApp : " + customerApp);
        System.out.println("customerRep : " + customerRep);

        appContext.close();
        repositoryContext.close();
    }
}
