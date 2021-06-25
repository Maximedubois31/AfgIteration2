package fr.afg.iteration1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor

@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float value;
    private float rsp;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Product product;



 

    public Price(float value, LocalDate startDate, LocalDate endDate) {
        this.value = value;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
