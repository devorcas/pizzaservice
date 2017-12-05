package ua.rd.domain.entities.order;

import ua.rd.domain.entities.order.state.OrderStateContext;

public interface StateChangeable {
	StateChangeable changeState (OrderStateContext	context);
}
