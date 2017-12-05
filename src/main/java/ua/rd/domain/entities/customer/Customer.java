/**
 * 
 */
package ua.rd.domain.entities.customer;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ua.rd.domain.entities.order.Order;


@Entity
@Table(name = "customers")
@NamedQueries({ @NamedQuery(name = "findAllCustomers",
query = "SELECT c FROM Customer c") })
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Transient
    private boolean accCardPresent;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name="accCard_id")
    private AccumulativeCard accCard;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "customers_addresses", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"))
    private Set<Address> addresses;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "customer")
    private List<Order> orders;

    public Customer() {
        super();
    }

    public Customer(String name, Set<Address> addresses, AccumulativeCard accCard) {
        super();
        this.name = name;
        this.accCard = accCard;
        this.addresses = addresses;
        if (accCard != null) {
            this.accCardPresent = true;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAccCardPresent() {
        return accCardPresent;
    }

    public void setAccCardPresent(boolean accCardPresent) {
        this.accCardPresent = accCardPresent;
    }

    public Boolean accCardIsPresent() {
        return accCardPresent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Customer(String name, Set<Address> addresses) {
        super();
        this.name = name;
        this.addresses = addresses;
    }

    public Customer(String name) {
        super();
        this.name = name;
    }

    public Customer(Long id, String name, Set<Address> addresses) {
        super();
        this.id = id;
        this.name = name;
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public AccumulativeCard getAccCard() {
        return accCard;
    }

    public void setAccCard(AccumulativeCard accCard) {
        this.accCard = accCard;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accCard == null) ? 0 : accCard.hashCode());
        result = prime * result + (accCardPresent ? 1231 : 1237);
        result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((orders == null) ? 0 : orders.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (accCard == null) {
            if (other.accCard != null)
                return false;
        } else if (!accCard.equals(other.accCard))
            return false;
        if (accCardPresent != other.accCardPresent)
            return false;
        if (addresses == null) {
            if (other.addresses != null)
                return false;
        } else if (!addresses.equals(other.addresses))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (orders == null) {
            if (other.orders != null)
                return false;
        } else if (!orders.equals(other.orders))
            return false;
        return true;
    }
    
}
