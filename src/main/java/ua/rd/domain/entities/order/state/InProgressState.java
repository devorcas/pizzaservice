package ua.rd.domain.entities.order.state;

import ua.rd.domain.entities.order.StateChangeable;
import ua.rd.infrastructure.StateChangeException;

public class InProgressState implements StateChangeable {

    public StateChangeable changeState(OrderStateContext context) {

        StateChangeable currentState = context.getState();
        if (currentState == null || currentState instanceof NewState) {
            context.setState(this);
            return this;
        } else {
            throw new StateChangeException();
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
