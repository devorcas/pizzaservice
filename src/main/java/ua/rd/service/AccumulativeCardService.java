package ua.rd.service;

import ua.rd.domain.entities.customer.AccumulativeCard;
import ua.rd.domain.entities.customer.Customer;

public interface AccumulativeCardService extends GenericService<AccumulativeCard>{

    AccumulativeCard createAccumulativeCard(Customer customer, Double accumulativeAmount);

    AccumulativeCard createAccumulativeCard();
	
}
