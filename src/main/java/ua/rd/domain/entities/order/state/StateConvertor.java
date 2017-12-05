package ua.rd.domain.entities.order.state;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StateConvertor implements AttributeConverter<OrderStateContext, String> {

    @Override
    public String convertToDatabaseColumn(OrderStateContext attribute) {
        if (attribute == null) {
            return "null";
        } else {
            return attribute.getState().getClass().getSimpleName().toLowerCase();
        }
    }

    @Override
    public OrderStateContext convertToEntityAttribute(String dbData) {
        OrderStateContext context = new OrderStateContext();
        switch (dbData) {
        case "cancelstate":
            context.setState(new CancelState());
            return context;
        case "inprogressstate":
            context.setState(new InProgressState());
            return context;
        case "donestate":
            context.setState(new DoneState());
            return context;
        default:
            return context;
        }
    }
}
