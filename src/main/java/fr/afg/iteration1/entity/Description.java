package fr.afg.iteration1.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * The type Description.
 */
@Data
@Entity
@Table
public class Description {

    /**
     * The id of this description.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The description.
     */
    @Column(length = 500)
    private String description;
    /**
     * The date where the last modification has been done for this description.
     */
    private LocalDate lastModificationDate;
    /**
     * The product of this description.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Product product;

    /**
     * The language of this description.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Language language;
}
