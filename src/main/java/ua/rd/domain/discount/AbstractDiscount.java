package ua.rd.domain.discount;

import java.util.List;

import ua.rd.domain.entities.order.Order;
import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.service.Discountable;

public abstract class AbstractDiscount implements Discountable {

	protected Order order;
	protected Discountable previousDiscount;

	public abstract Double calcCurrentDiscount();

	public abstract Double calcPriceWithDiscount();

	public AbstractDiscount(Order order) {
		super();
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Discountable getPreviousDiscount() {
		return previousDiscount;
	}

	public void setPreviousDiscount(Discountable previousDiscount) {
		this.previousDiscount = previousDiscount;
	}

}
