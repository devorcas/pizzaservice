package ua.rd.repository.inmem;

import java.util.ArrayList;
import java.util.List;

import org.mockito.internal.matchers.Or;
import org.omg.CORBA.RepositoryIdHelper;

import ua.rd.domain.entities.order.Order;

import ua.rd.repository.GenericRepository;

public class InMemOrderRepository implements GenericRepository<Order> {

    private List<Order> orders = new ArrayList<Order>();

    public Order save(Order order) {
        orders.add(order);
        return order;
    }

    @Override
    public Order getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Order> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
