package ua.rd.domain.entities.order.state;

import ua.rd.domain.entities.order.StateChangeable;

public class OrderStateContext {

    private StateChangeable state;

    public OrderStateContext() {
        super();
        state = new NewState();
    }

    public StateChangeable getState() {
        return state;
    }

    public void setState(StateChangeable state) {
        this.state = state;
    }
}
