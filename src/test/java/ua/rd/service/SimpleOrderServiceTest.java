package ua.rd.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.rd.domain.entities.customer.Address;
import ua.rd.domain.entities.customer.Customer;
import ua.rd.domain.entities.order.Order;
import ua.rd.domain.entities.order.state.CancelState;
import ua.rd.domain.entities.order.state.DoneState;
import ua.rd.domain.entities.order.state.InProgressState;
import ua.rd.domain.entities.order.state.NewState;
import ua.rd.domain.entities.order.state.OrderStateContext;
import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.infrastructure.TooManyPizzasInOrderException;
import ua.rd.repository.OrderRepository;
import ua.rd.service.impl.SimpleOrderService;

@RunWith(MockitoJUnitRunner.class)
public class SimpleOrderServiceTest {

    private OrderService orderService = new SimpleOrderService();

    @Mock
    private PizzaService mockPizzaService;
    @Mock
    private OrderRepository mockOrderRepository;
    @Mock
    private Customer mockCustomer;
    @Mock
    private Pizza mockPizza;
    @Mock
    private Address mockAddress;
    @Mock
    private OrderStateContext mockStateContext;

    @Test
    public void cancelStatusTest(){
        Order order1 = new Order();
       orderService.cancelOrderState(order1);
        assertEquals(order1.getStatusContext().getState(), new CancelState());
    }
    
    @Test 
    public void nextStatusSequenceTest(){
        
        Order order = new Order();
        assertEquals(order.getStatusContext().getState(), new NewState());
        
        orderService.nextStatus(order);
        assertEquals(order.getStatusContext().getState(), new InProgressState());

        orderService.nextStatus(order);
        assertEquals(order.getStatusContext().getState(), new DoneState());   
    }
    
    @Test
    public void placeNewOrderTest() {
        orderService = new SimpleOrderService(mockPizzaService, mockOrderRepository);

        when(mockPizzaService.getById(anyObject())).thenReturn(mockPizza);
        when(mockOrderRepository.save(anyObject())).thenReturn(new Order());

        Order order = orderService.placeNewOrder(mockCustomer, mockAddress, mockStateContext, 1L, 2L, 3L, 4L);

        assertNotNull(order);
    }

    @Test(expected = TooManyPizzasInOrderException.class)
    public void addPizzasTooManyPizzasInOrderExceotionTest() {
        
        orderService = new SimpleOrderService(mockPizzaService, mockOrderRepository);
        Order order = new Order();
        List<Pizza> pizzas = new ArrayList<>();

        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        
        orderService.addPizzasToOrder(order, pizzas);
    }

    @Test
    public void countPizzasTest() {

        orderService = new SimpleOrderService(mockPizzaService, mockOrderRepository);
        Order order = new Order();
        List<Pizza> pizzas = new ArrayList<>();

        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);
        pizzas.add(mockPizza);

        orderService.addPizzasToOrder(order, pizzas);
        
        assertEquals(10, orderService.countPizzas(order));
    }

}
