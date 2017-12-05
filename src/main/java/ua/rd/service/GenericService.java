package ua.rd.service;

import java.util.List;

import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;

import ua.rd.domain.entities.order.Order;

public interface GenericService<E> {
    
    E getById(Long id);

    @Transactional
    E save(E entity);

    @Transactional
    boolean remove(Long id);

    List<E> findAll();

  
}
