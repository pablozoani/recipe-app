package com.pablozoani.recipeapp.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

    private String id;

    private String description;

    private BigDecimal amount;

    private UnitOfMeasureCommand unitOfMeasure;

    private String recipeId;
}
