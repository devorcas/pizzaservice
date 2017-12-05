package ua.rd.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.domain.entities.customer.Address;
import ua.rd.domain.entities.customer.Customer;
import ua.rd.domain.entities.order.Order;
import ua.rd.domain.entities.order.StateChangeable;
import ua.rd.domain.entities.order.state.CancelState;
import ua.rd.domain.entities.order.state.DoneState;
import ua.rd.domain.entities.order.state.InProgressState;
import ua.rd.domain.entities.order.state.NewState;
import ua.rd.domain.entities.order.state.OrderStateContext;
import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.infrastructure.BenchMark;
import ua.rd.infrastructure.TooManyPizzasInOrderException;
import ua.rd.repository.OrderRepository;
import ua.rd.service.OrderService;
import ua.rd.service.PizzaService;

@Service("orderService")
public class SimpleOrderService implements OrderService {

    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private OrderRepository orderRepository;

    public SimpleOrderService() {
        super();
    }

    public SimpleOrderService(PizzaService pizzaService, OrderRepository orderRepository) {
        super();
        this.pizzaService = pizzaService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order cancelOrderState(Order order) {
        OrderStateContext stateContext = order.getStatusContext();
        StateChangeable state = new CancelState();
        state.changeState(stateContext);
        return order;
    }

    @Override
    public Order nextStatus(Order order) {
        OrderStateContext stateContext = order.getStatusContext();
        StateChangeable state = stateContext.getState();
        if (state instanceof NewState || state == null) {
            state = new InProgressState();
        } else if (state instanceof InProgressState) {
            state = new DoneState();
        }
        state.changeState(stateContext);
        return order;
    }

    @BenchMark
    @Override
    public Order placeNewOrder(Customer customer, Address address, OrderStateContext stateContext, Long... pizzasID) {
        Map<Pizza, Integer> pizzas = pizzasByArrOfId(pizzasID);
        Order order = createOrder();
        order.setStatusContext(stateContext);
        order.setDeliveryAddress(address);
        order.setCustomer(customer);
        order.setPizzas(pizzas);
        return orderRepository.save(order); // set Order Id and save Order to
                                            // in-memory list
    }

    /**
     * Adds pizzas until order is full (pizzas count == 10)
     * 
     * @return quantity of added pizzas
     */

    @Override
    public int addPizzasToOrder(Order order, List<Pizza> pizzasToAdd) {
        Map<Pizza, Integer> pizzasInOrder = order.getPizzas();
        if (pizzasInOrder == null) {
            pizzasInOrder = new HashMap<>();
            order.setPizzas(pizzasInOrder);
        }
        int pizzasCount = countPizzas(pizzasInOrder);
        for (Pizza newPizza : pizzasToAdd) {
            if (pizzasCount < 10) {
                pizzasCount += addPizzaToMapAndAdjustQua(pizzasInOrder, newPizza);
            } else {
                throw new TooManyPizzasInOrderException();
            }
        }
        return pizzasCount;
    }

    private int countPizzas(Map<Pizza, Integer> pizzasInOrder) {
        int pizzasCount = 0;
        for (Map.Entry<Pizza, Integer> entry : pizzasInOrder.entrySet()) {
            pizzasCount += entry.getValue().intValue();
        }
        return pizzasCount;
    }

    @Override
    public int countPizzas(Order order) {
        int pizzasCount = 0;
        Map<Pizza, Integer> pizzasInOrder = order.getPizzas();
        if (pizzasInOrder == null) {
            return pizzasCount;
        }
        return countPizzas(pizzasInOrder);
    }

    @BenchMark
    protected Order createOrder() {
        return new Order();
    }

    private Map<Pizza, Integer> pizzasByArrOfId(Long... pizzasID) {

        Map<Pizza, Integer> pizzas = new HashMap<>();

        for (Long id : pizzasID) {
            Pizza pizzaToAdd = pizzaService.getById(id);
            addPizzaToMapAndAdjustQua(pizzas, pizzaToAdd);
        }
        return pizzas;
    }

    private int addPizzaToMapAndAdjustQua(Map<Pizza, Integer> pizzas, Pizza pizzaToAdd) {
        if (pizzas.containsKey(pizzaToAdd)) {
            int presentPizzaCount = pizzas.get(pizzaToAdd).intValue();
            pizzas.put(pizzaToAdd, ++presentPizzaCount);
        } else {
            pizzas.put(pizzaToAdd, 1);
        }
        return 1;
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public Order save(Order entity) {
        return orderRepository.save(entity);
    }

    @Override
    public boolean remove(Long id) {
        return orderRepository.remove(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
