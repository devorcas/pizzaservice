package ua.rd.repository.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import ua.rd.domain.entities.customer.Customer;
import ua.rd.repository.CustomerRepository;

@Repository("customerRepository")
public class JPACustomerRepository extends AbstractJPARepository<Customer> implements CustomerRepository{

    @Override
    public Customer getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Customer save(Customer entity) {
        return super.save(entity);
    }

    @Override
    public boolean remove(Long id) {
       return super.remove(id);
    }

    @Override
    public List<Customer> findAll() {
        return super.findAll("findAllCustomers");
    }
}
