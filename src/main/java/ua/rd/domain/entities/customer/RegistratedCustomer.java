package ua.rd.domain.entities.customer;

import java.util.Date;
import java.util.Set;

public class RegistratedCustomer extends Customer {

    private Date creationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public RegistratedCustomer() {
        super();
    }

    public RegistratedCustomer(String name, Set<Address> add) {
        super(name, add);
        this.creationDate = new Date();
    }

}
