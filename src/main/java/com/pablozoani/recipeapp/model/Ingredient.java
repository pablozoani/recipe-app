package com.pablozoani.recipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@Table(name = "ingredient")
public class Ingredient {

    // == fields ==

    @Id
    @GeneratedValue(generator = "native")
    protected Long id;

    @Column(nullable = false)
    protected String description;

    @Column(nullable = false)
    protected BigDecimal amount;

    // == relationships ==

    @ManyToOne
    protected Recipe recipe;

    @OneToOne
    protected UnitOfMeasure unitOfMeasure;
}
