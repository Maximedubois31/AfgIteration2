package fr.afg.iteration1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Command line.
 */
@Getter @Setter @NoArgsConstructor

@Entity
@Table
public class CommandLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * The id of this CommandLine.
     */
    private Long id;
    /**
     * The orderedQuantity of this CommandLine.
     */
    private Float orderedQuantity;
    /**
     * The desiredQuantity of this CommandLine.
     */
    private Float desiredQuantity;
    /**
     * The discount of this CommandLine.
     */
    private Float discount;
    /**
     * The activePrice of this CommandLine.
     */
    private Float activePrice;

    @ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Product product;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
	private PurchaseOrder purchaseOrder;

	@Override
	public String toString() {
		return "CommandLine [id=" + id + ", orderedQuantity=" + orderedQuantity + ", desiredQuantity=" + desiredQuantity
				+ ", discount=" + discount + "]";
	}

    
}
