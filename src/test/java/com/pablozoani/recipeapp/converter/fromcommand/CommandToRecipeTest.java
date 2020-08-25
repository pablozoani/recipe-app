package com.pablozoani.recipeapp.converter.fromcommand;

import com.pablozoani.recipeapp.command.CategoryCommand;
import com.pablozoani.recipeapp.command.IngredientCommand;
import com.pablozoani.recipeapp.command.NotesCommand;
import com.pablozoani.recipeapp.command.RecipeCommand;
import com.pablozoani.recipeapp.model.Difficulty;
import com.pablozoani.recipeapp.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandToRecipeTest {

    CommandToNotes commandToNotes;

    CommandToCategory commandToCategory;

    CommandToUnitOfMeasure commandToUnitOfMeasure;

    CommandToIngredient commandToIngredient;

    CommandToRecipe commandToRecipe;

    @BeforeEach
    void setUp() {
        commandToNotes = new CommandToNotes();
        commandToCategory = new CommandToCategory();
        commandToUnitOfMeasure = new CommandToUnitOfMeasure();
        commandToIngredient = new CommandToIngredient(commandToUnitOfMeasure);
        commandToRecipe = new CommandToRecipe(commandToNotes, commandToCategory, commandToIngredient);
    }

    @Test
    void testNullObject() {
        assertNull(commandToRecipe.convert(null));
    }

    @Test
    void testEmptyObject() {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setNotes(new NotesCommand());
        assertNotNull(commandToRecipe.convert(recipeCommand));
    }

    @Test
    void convert() {
        RecipeCommand input = new RecipeCommand();
        input.setDirections("directions...");
        input.setDifficulty(Difficulty.EASY);
        input.setDescription("description...");
        input.setCookTime("12 minutes");
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId("abc");
        notesCommand.setRecipeNotes("recipe notes...");
        input.setNotes(notesCommand);
        input.setId("bcd");
        input.setPrepTime("20 minutes");
        input.setServings(3);
        input.setSource("source...");
        input.setUrl("url...");
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId("cdb");
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId("cde");
        input.getCategories().add(categoryCommand);
        input.getCategories().add(categoryCommand2);
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId("def");
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId("efg");
        input.getIngredients().add(ingredientCommand);
        input.getIngredients().add(ingredientCommand2);

        Recipe output = commandToRecipe.convert(input);

        assertNotNull(output);
        assertEquals(input.getCategories().size(), output.getCategories().size());
        assertEquals(input.getIngredients().size(), output.getIngredients().size());
        assertEquals(input.getCookTime(), output.getCookTime());
        assertEquals(input.getDescription(), output.getDescription());
        assertEquals(input.getDifficulty(), output.getDifficulty());
        assertEquals(input.getDirections(), output.getDirections());
        assertEquals(input.getId(), output.getId());
        assertEquals(input.getNotes().getId(), output.getNotes().getId());
        assertEquals(input.getPrepTime(), output.getPrepTime());
        assertEquals(input.getServings(), output.getServings());
        assertEquals(input.getSource(), output.getSource());
        assertEquals(input.getUrl(), output.getUrl());
    }
}