package ua.rd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import ua.rd.domain.entities.customer.Address;
import ua.rd.repository.AddressRepository;
import ua.rd.service.AddressService;

public class SimpleAddressService implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public SimpleAddressService() {
        super();
    }

    @Override
    public Address createAddress() {
        return new Address();
    }

    @Override
    public Address createAddress(String strAddress) {
        Address address = createAddress();
        address.setStrAddress(strAddress);
        return address;
    }

    @Override
    public Address getById(Long id) {
        return addressRepository.getById(id);
    }

    @Override
    public Address save(Address entity) {
        return addressRepository.save(entity);
    }

    @Override
    public boolean remove(Long id) {
        return addressRepository.remove(id);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
