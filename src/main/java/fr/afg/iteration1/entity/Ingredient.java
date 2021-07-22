package fr.afg.iteration1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Ingredient.
 */
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table
public class Ingredient {

    /**
     * The id of this ingredient.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The name of this ingredient.
     */
    private String name;
    /**
     * The allergen of this ingredient.
     */
    private String allergen;

    /**
     * The list of products for this ingredient.
     */
    @ManyToMany(mappedBy = "ingredients", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<Product>();
    /**
     * The language of this ingredient.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Language language;
}
