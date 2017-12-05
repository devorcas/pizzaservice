package ua.rd.domain.discount;

import java.util.List;
import java.util.Map;

import ua.rd.domain.entities.order.Order;
import ua.rd.domain.entities.pizza.Pizza;

/**
 * Calculates discount value of the order by quantity of ordered pizzas
 * 
 *
 */
public class QuantityPizzaDiscount extends AbstractDiscount {

    public QuantityPizzaDiscount(Order order) {
        super(order);
    }

    private static final Double DISCOUNT_BY_QUANTITY_VALUE = 0.3;

    /**
     * 
     * @param order
     * @return
     */
    public Double calcCurrentDiscount() {
        Map<Pizza, Integer> pizzas =  this.getOrder().getPizzas();
        if (pizzas.size() < 4) {
            return 0.0d;
        }
        Double currPrice = 0.0d;
        Double maxPrice = 0.0d;
        
        for (Map.Entry<Pizza, Integer> entry : pizzas.entrySet()) {
            currPrice = entry.getKey().getPrice();
            if (currPrice > maxPrice) {
                maxPrice = currPrice;
            }
        }       
        return maxPrice * DISCOUNT_BY_QUANTITY_VALUE;
    }

    public Double calcPriceWithDiscount() {
        return getOrder().calcPrice() - calcCurrentDiscount();
    }
}
