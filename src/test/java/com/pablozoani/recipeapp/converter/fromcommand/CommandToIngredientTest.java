package com.pablozoani.recipeapp.converter.fromcommand;

import com.pablozoani.recipeapp.command.IngredientCommand;
import com.pablozoani.recipeapp.command.UnitOfMeasureCommand;
import com.pablozoani.recipeapp.model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CommandToIngredientTest {

    CommandToUnitOfMeasure commandToUnitOfMeasure;

    CommandToIngredient commandToIngredient;

    @BeforeEach
    void setUp() {
        commandToUnitOfMeasure = new CommandToUnitOfMeasure();
        commandToIngredient = new CommandToIngredient(commandToUnitOfMeasure);
    }

    @Test
    void testNullObject() {
        assertNull(commandToIngredient.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(commandToIngredient.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        IngredientCommand input = new IngredientCommand();
        input.setId(14L);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(6L);
        unitOfMeasureCommand.setUnitOfMeasure("cups");
        input.setUnitOfMeasure(unitOfMeasureCommand);
        input.setAmount(BigDecimal.valueOf(5L));
        input.setDescription("description...");

        Ingredient output = commandToIngredient.convert(input);

        assertEquals(14L, output.getId());
        assertEquals(BigDecimal.valueOf(5L), output.getAmount());
        assertEquals("description...", output.getDescription());
        assertEquals("cups", output.getUnitOfMeasure().getUnitOfMeasure());
        assertEquals(6L, output.getUnitOfMeasure().getId());
    }

    @Test
    void convertWithNullUnitOfMeasure() {
        IngredientCommand input = new IngredientCommand();
        input.setId(14L);
        input.setAmount(BigDecimal.valueOf(5L));
        input.setDescription("description...");

        Ingredient output = commandToIngredient.convert(input);

        assertNull(output.getUnitOfMeasure());
        assertEquals(14L, output.getId());
        assertEquals(BigDecimal.valueOf(5L), output.getAmount());
        assertEquals("description...", output.getDescription());
    }
}