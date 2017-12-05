package ua.rd.service.impl;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import ua.rd.domain.entities.customer.AccumulativeCard;
import ua.rd.domain.entities.customer.Customer;
import ua.rd.repository.AccumulativeCardRepository;
import ua.rd.service.AccumulativeCardService;

public class SimpleAccumulativeCardService implements AccumulativeCardService {

    @Autowired
    private AccumulativeCardRepository accumulativeCardRepository;

    @Override
    public AccumulativeCard createAccumulativeCard() {
        return new AccumulativeCard();
    }

    @Override
    public AccumulativeCard createAccumulativeCard(Customer customer, Double accumulativeAmount) {
        AccumulativeCard accCard = createAccumulativeCard();
        accCard.setAccumulativeAmount(accumulativeAmount);
        accCard.setCustomer(customer);
        return accCard;
    }

    @Override
    public AccumulativeCard getById(Long id) {
        return accumulativeCardRepository.getById(id);
    }

    @Override
    public AccumulativeCard save(AccumulativeCard entity) {
        return accumulativeCardRepository.save(entity);
    }

    @Override
    public boolean remove(Long id) {
        return accumulativeCardRepository.remove(id);
    }

    @Override
    public List<AccumulativeCard> findAll() {
        return accumulativeCardRepository.findAll();
    }
}
