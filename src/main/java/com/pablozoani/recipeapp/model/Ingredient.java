package com.pablozoani.recipeapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    // == fields ==
    private String description;
    private BigDecimal amount;

    // == relationships ==
    private Recipe recipe;
    private UnitOfMeasure unitOfMeasure;
}
