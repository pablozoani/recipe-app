package com.pablozoani.recipeapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    // == fields ==

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
