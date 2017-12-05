package ua.rd.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.rd.domain.entities.order.Order;
import ua.rd.repository.OrderRepository;


@Repository(value = "orderRepository")
public class JPAOrderRepository extends AbstractJPARepository<Order> implements OrderRepository{

    public JPAOrderRepository() {
        super();
    }

    public JPAOrderRepository(EntityManager em) {
        super(em);
    }

    @Override
    public Order getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Order save(Order entity) {
        return super.save(entity);
    }

    @Override
    public boolean remove(Long id) {
        return super.remove(id);
    }

    @Override
    public List<Order> findAll() {
        return super.findAll("findAllOrders");
    }


}
