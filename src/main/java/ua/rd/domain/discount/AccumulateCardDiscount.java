package ua.rd.domain.discount;

import ua.rd.domain.entities.customer.AccumulativeCard;
import ua.rd.domain.entities.order.Order;

/**
 * Calculates discount value from Accumulative Card of customer, if one is
 * present. Discount is 10% from the Accumulative Card amount but not more that
 * 30% from the whole order cost.
 * 
 *
 */
public class AccumulateCardDiscount extends AbstractDiscount {

	private static final Double DISCOUNT_BY_ACCUMULATIVE_CARD_VALUE = 0.1;
	private static final Double MAX_RANGE_DISCOUNT = 0.7;

	private AccumulativeCard accCard;
	
	public AccumulateCardDiscount(Order order) {
		super(order);
	}
	
	public AccumulateCardDiscount(AbstractDiscount discount, AccumulativeCard accCard){
		this(discount.order);
		this.previousDiscount = discount;
		this.accCard = accCard;
	}
	
	public Double calcCurrentDiscount() {
		if (accCard == null) {
			System.out.println("acc ");
			return 0.0d;
		}
		
		Double accumulativeAmount = accCard.getAccumulativeAmount();
		Double accumulativeAmountDisc = accumulativeAmount * DISCOUNT_BY_ACCUMULATIVE_CARD_VALUE;

		Double orderPriceWithQuantDisc = getPreviousDiscount().calcPriceWithDiscount();
		
		if (accumulativeAmountDisc / orderPriceWithQuantDisc > (1 - MAX_RANGE_DISCOUNT)) {
			accumulativeAmountDisc = orderPriceWithQuantDisc * (1-MAX_RANGE_DISCOUNT);
		}
		return accumulativeAmountDisc;
	}

	public Double calcPriceWithDiscount() {
		return getPreviousDiscount().calcPriceWithDiscount() - calcCurrentDiscount();
		
	}
}
