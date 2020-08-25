package com.pablozoani.recipeapp.converter.tocommand;

import com.pablozoani.recipeapp.command.UnitOfMeasureCommand;
import com.pablozoani.recipeapp.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToCommandTest {

    UnitOfMeasureToCommand unitOfMeasureToCommand;

    @BeforeEach
    void setUp() {
        unitOfMeasureToCommand = new UnitOfMeasureToCommand();
    }

    @Test
    void testNullObject() {
        assertNull(unitOfMeasureToCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        unitOfMeasureToCommand.convert(new UnitOfMeasure());
    }

    @Test
    void convert() {
        UnitOfMeasure input = new UnitOfMeasure();
        input.setId("132L");
        input.setUnitOfMeasure("unit of measure...");

        UnitOfMeasureCommand output = unitOfMeasureToCommand.convert(input);

        assertNotNull(output);
        assertEquals(input.getId(), output.getId());
        assertEquals(input.getUnitOfMeasure(), output.getUnitOfMeasure());
    }
}