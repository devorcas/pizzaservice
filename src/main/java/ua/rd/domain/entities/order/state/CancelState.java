package ua.rd.domain.entities.order.state;

import ua.rd.domain.entities.order.StateChangeable;
import ua.rd.infrastructure.StateChangeException;

public class CancelState implements StateChangeable {

    public StateChangeable changeState(OrderStateContext context) {

        StateChangeable currentState = context.getState();

        if (currentState instanceof DoneState || currentState == null) {
            throw new StateChangeException();
        } else {
            context.setState(this);
            return this;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }
}
