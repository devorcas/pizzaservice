package ua.rd.domain.entities.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
@NamedQueries(@NamedQuery(name = "findAllAddresses", query = "SELECT c FROM Address c"))
public class Address {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    public Address() {
        super();
    }

    public Address(String strAdd) {
        super();
        this.street = strAdd;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrAddress() {
        return street;
    }

    public void setStrAddress(String strAddress) {
        this.street = strAddress;
    }

    @Override
    public String toString() {
        return "DeliveryAddress [strAddress=" + street + "]";
    }
}