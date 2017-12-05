package ua.rd.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface GenericRepository<E> {
    
	E getById(Long id);
	@Transactional
	E save(E entity);
	@Transactional
	boolean remove(Long id);
	List<E> findAll();
}
