package ua.rd.domain.entities.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


public class AddressState  implements Serializable{
    @Column(name="StateName")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;

    public AddressState() {
        super();
        state="";
    }

    public AddressState(String state) {
        super();
        this.state = state;
    }
}
