package ua.rd.repository.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import ua.rd.domain.entities.customer.Address;
import ua.rd.repository.AddressRepository;

@Repository("addressRepository")
public class JPAAddressRepository extends AbstractJPARepository<Address> implements AddressRepository{

    @Override
    public Address getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Address save(Address entity) {
        return super.save(entity);
    }

    @Override
    public boolean remove(Long id) {
        return super.remove(id);
    }

    @Override
    public List<Address> findAll() {
        return super.findAll("findAllAddresses");
    }

}
