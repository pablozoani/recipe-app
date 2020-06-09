package com.pablozoani.recipeapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    // == fields ==

    @Id @Getter
    @GeneratedValue(generator = "native")
    protected Long id;

    @Getter @Setter
    @Column(name = "description", nullable = false)
    protected String description;

    @Getter @Setter
    @Column(name = "amount", nullable = false)
    protected BigDecimal amount;

    // == relationships ==

    protected Recipe recipe;
    protected UnitOfMeasure unitOfMeasure;
}
