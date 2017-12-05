package ua.rd.service;

import java.util.List;

import ua.rd.domain.entities.customer.Address;
import ua.rd.domain.entities.customer.Customer;
import ua.rd.domain.entities.order.Order;
import ua.rd.domain.entities.order.state.OrderStateContext;
import ua.rd.domain.entities.pizza.Pizza;

public interface OrderService extends GenericService<Order> {

    Order placeNewOrder(Customer customer, Address address, OrderStateContext stateContext, Long... pizzasID);

    int addPizzasToOrder(Order order, List<Pizza> pizzas);
    
    int countPizzas(Order order);

    Order nextStatus(Order order);

    Order cancelOrderState(Order order);

}