package com.pablozoani.recipeapp.converter.fromcommand;

import com.pablozoani.recipeapp.command.UnitOfMeasureCommand;
import com.pablozoani.recipeapp.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandToUnitOfMeasureTest {

    CommandToUnitOfMeasure commandToUnitOfMeasure;

    @BeforeEach
    void setUp() {
        commandToUnitOfMeasure = new CommandToUnitOfMeasure();
    }

    @Test
    void testNullObject() {
        assertNull(commandToUnitOfMeasure.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(commandToUnitOfMeasure.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
        UnitOfMeasureCommand input = new UnitOfMeasureCommand();
        input.setId("abcde");
        input.setUnitOfMeasure("teaspoon");

        UnitOfMeasure output = commandToUnitOfMeasure.convert(input);

        assertNotNull(output);
        assertEquals(input.getId(), output.getId());
        assertEquals(input.getUnitOfMeasure(), output.getUnitOfMeasure());
    }
}