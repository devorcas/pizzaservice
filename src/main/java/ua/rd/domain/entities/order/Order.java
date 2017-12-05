package ua.rd.domain.entities.order;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ua.rd.domain.entities.customer.Address;
import ua.rd.domain.entities.customer.Customer;
import ua.rd.domain.entities.order.state.OrderStateContext;
import ua.rd.domain.entities.order.state.StateConvertor;
import ua.rd.domain.entities.pizza.Pizza;



@Entity
@Table(name = "orders")
@NamedQueries(@NamedQuery(name = "findAllOrders", query = "SELECT c FROM Order c"))
public class Order {

    @Transient
    private static long stId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "orders_pizzas", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "pizza_id")
    @Column(name = "pizzas_count")
    private Map<Pizza, Integer> pizzas;

    private Double price;

    @ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "delivery_address_id", nullable = false)
    private Address deliveryAddress;

    @Column(name = "status")
    @Convert(converter = StateConvertor.class)
    private OrderStateContext statusContext;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Order() {
        super();
        this.date = Calendar.getInstance().getTime();
        this.statusContext = new OrderStateContext();
    }

    /**
     * @param id
     * @param customer
     * @param pizza
     */
    public Order(Customer customer, Map<Pizza, Integer> pizza) {
        super();
        this.id = stId++;
        this.customer = customer;
        this.pizzas = pizza;
        this.date = Calendar.getInstance().getTime();
    }

    /**
     * Count price of stored pizzas in order
     * 
     * @param order
     * @return
     */
    public Double calcPrice() {
        Double price = 0.0d;
        if (pizzas == null) {

        } else {
            for (Map.Entry<Pizza, Integer> entry : pizzas.entrySet()) {
                double pizzaPrice = entry.getKey().getPrice();
                int pizzaCount = entry.getValue().intValue();
                price += pizzaPrice * pizzaCount;

            }
        }
        this.price = price;
        return price;
    }

    public Order(Customer customer) {
        super();
        this.customer = customer;
        this.date = Calendar.getInstance().getTime();
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", customer=" + customer + ", pizzas=" + pizzas + ", status=" + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((pizzas == null) ? 0 : pizzas.hashCode());
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
        Order other = (Order) obj;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (pizzas == null) {
            if (other.pizzas != null)
                return false;
        } else if (!pizzas.equals(other.pizzas))
            return false;
        return true;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Pizza, Integer> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Map<Pizza, Integer> pizzas) {
        this.pizzas = pizzas;
    }

    public StateChangeable getStatus() {
        return statusContext.getState();
    }

    public OrderStateContext getStatusContext() {
        return statusContext;
    }

    public void setStatusContext(OrderStateContext statusContext) {
        this.statusContext = statusContext;
    }
}
