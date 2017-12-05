package ua.rd.service;

import ua.rd.domain.entities.customer.Address;

public interface AddressService extends GenericService<Address>{

    Address createAddress();

    Address createAddress(String strAddress);

}
