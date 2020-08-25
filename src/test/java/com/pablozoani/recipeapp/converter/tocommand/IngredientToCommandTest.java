package com.pablozoani.recipeapp.converter.tocommand;

import com.pablozoani.recipeapp.command.IngredientCommand;
import com.pablozoani.recipeapp.model.Ingredient;
import com.pablozoani.recipeapp.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToCommandTest {

    UnitOfMeasureToCommand unitOfMeasureToCommand;

    IngredientToCommand ingredientToCommand;

    @BeforeEach
    void setUp() {
        unitOfMeasureToCommand = new UnitOfMeasureToCommand();
        ingredientToCommand = new IngredientToCommand(unitOfMeasureToCommand);
    }

    @Test
    void testNullObject() {
        assertNull(ingredientToCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(ingredientToCommand.convert(new Ingredient()));
    }

    @Test
    void convert() {
        Ingredient input = new Ingredient();
        input.setId("25L");
        input.setDescription("description...");
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId("32L");
        input.setUnitOfMeasure(unitOfMeasure);
        input.setAmount(BigDecimal.valueOf(5L));

        IngredientCommand output = ingredientToCommand.convert(input);

        assertNotNull(output);
        assertEquals(input.getUnitOfMeasure().getId(), output.getUnitOfMeasure().getId());
        assertEquals(input.getAmount(), output.getAmount());
        assertEquals(input.getId(), output.getId());
        assertEquals(input.getDescription(), output.getDescription());
    }
}