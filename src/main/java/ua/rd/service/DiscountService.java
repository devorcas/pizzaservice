package ua.rd.service;

import java.util.List;

import ua.rd.domain.entities.order.Order;

public interface DiscountService extends GenericService<Discountable> {
	List<Discountable> getDiscounts(Order order);

	Double applyDiscounts(List<Discountable> discounts);
}
