package com.pablozoani.recipeapp.converter.tocommand;

import com.pablozoani.recipeapp.command.RecipeCommand;
import com.pablozoani.recipeapp.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToCommandTest {

    NotesToCommand notesToCommand;

    CategoryToCommand categoryToCommand;

    UnitOfMeasureToCommand unitOfMeasureToCommand;

    IngredientToCommand ingredientToCommand;

    RecipeToCommand recipeToCommand;

    @BeforeEach
    void setUp() {
        notesToCommand = new NotesToCommand();
        categoryToCommand = new CategoryToCommand();
        unitOfMeasureToCommand = new UnitOfMeasureToCommand();
        ingredientToCommand = new IngredientToCommand(unitOfMeasureToCommand);
        recipeToCommand = new RecipeToCommand(notesToCommand, categoryToCommand, ingredientToCommand);
    }

    @Test
    void testNullObject() {
        assertNull(recipeToCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(recipeToCommand.convert(new Recipe()));
    }

    @Test
    void convert() {
        Recipe input = new Recipe();
        input.setDescription("description...");
        input.setSource("source...");
        input.setCookTime("12 minutes");
        input.setPrepTime("20 minutes");
        input.setDifficulty(Difficulty.HARD);
        input.setDirections("directions...");
        input.setUrl("url...");
        input.setId("25L");
        Notes notes = new Notes();
        notes.setId("32L");
        input.setNotes(notes);
        input.getIngredients().add(new Ingredient());
        input.getIngredients().add(new Ingredient());
        input.getCategories().add(new Category());
        input.getCategories().add(new Category());

        RecipeCommand output = recipeToCommand.convert(input);

        assertNotNull(output);
        assertEquals(input.getIngredients().size(), output.getIngredients().size());
        assertEquals(input.getCategories().size(), output.getCategories().size());
        assertEquals(input.getCookTime(), output.getCookTime());
        assertEquals(input.getPrepTime(), output.getPrepTime());
        assertEquals(input.getDescription(), output.getDescription());
        assertEquals(input.getDifficulty(), output.getDifficulty());
        assertEquals(input.getUrl(), output.getUrl());
        assertEquals(input.getNotes().getId(), output.getNotes().getId());
        assertEquals(input.getSource(), output.getSource());
        assertEquals(input.getServings(), output.getServings());
    }
}