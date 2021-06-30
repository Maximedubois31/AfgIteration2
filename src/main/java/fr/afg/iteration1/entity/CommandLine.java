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

@Getter @Setter @NoArgsConstructor

@Entity
@Table
public class CommandLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float orderedQuantity;
    private Float desiredQuantity;
    private Float discount;
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
