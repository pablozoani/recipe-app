package com.pablozoani.recipeapp.model;

import java.math.BigDecimal;

public class Ingredient {

    // == fields ==
    private String description;
    private BigDecimal amount;

    // == relationships ==
    private Recipe recipe;
    private UnitOfMeasure unitOfMeasure;
}
