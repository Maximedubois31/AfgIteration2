package fr.afg.iteration1.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor

@Entity
@Table
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate creationDate;
    private LocalDate wantedDeliveryDate;
    private LocalDate deliveryDate;
    private LocalDate preparationDate;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User customer;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User creator;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User preparator;
 

    @OneToMany(mappedBy = "purchaseOrder")
    private List<CommandLine> lines;

    public PurchaseOrder(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", creationDate=" + creationDate + ", wantedDeliveryDate="
				+ wantedDeliveryDate + ", deliveryDate=" + deliveryDate + ", preparationDate=" + preparationDate + "]";
	}

    
}
