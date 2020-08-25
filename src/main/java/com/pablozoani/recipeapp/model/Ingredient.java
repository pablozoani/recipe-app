package com.pablozoani.recipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
public class Ingredient {

    // == fields ==

    protected String id = UUID.randomUUID().toString();

    protected String description;

    protected BigDecimal amount;

    // == relationships ==

    protected Recipe recipe;

    @DBRef
    protected UnitOfMeasure unitOfMeasure;
}
