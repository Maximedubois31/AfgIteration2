package fr.afg.iteration1.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * The type Language.
 */
@Data
@Entity
@Table
public class Language {

    /**
     * The id of this language.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    /**
     * The list of ingredients in this language.
     */
    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;
    /**
     * The list of description in this language.
     */
    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    private List<Description> descriptions;
}
