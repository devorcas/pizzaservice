/**
 * 
 */
package ua.rd.domain.entities.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accumulativeCards")
public class AccumulativeCard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double accumulativeAmount;

    @OneToOne( mappedBy="accCard")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccumulativeCard(Double accumulativeAmount) {
        super();
        this.accumulativeAmount = accumulativeAmount;
    }

    public AccumulativeCard() {
        super();
        this.accumulativeAmount = 0.0;
    }

    public Double getAccumulativeAmount() {
        return accumulativeAmount;
    }

    public void setAccumulativeAmount(Double accumulativeAmount) {
        this.accumulativeAmount = accumulativeAmount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accumulativeAmount == null) ? 0 : accumulativeAmount.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        AccumulativeCard other = (AccumulativeCard) obj;
        if (accumulativeAmount == null) {
            if (other.accumulativeAmount != null)
                return false;
        } else if (!accumulativeAmount.equals(other.accumulativeAmount))
            return false;
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
        return true;
    }

}
