package com.pablozoani.recipeapp.converter.tocommand;

import com.pablozoani.recipeapp.command.NotesCommand;
import com.pablozoani.recipeapp.model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToCommandTest {

    NotesToCommand notesToCommand;

    @BeforeEach
    void setUp() {
        notesToCommand = new NotesToCommand();
    }

    @Test
    void testNullObject() {
        assertNull(notesToCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(notesToCommand.convert(new Notes()));
    }

    @Test
    void convert() {
        Notes input = new Notes();
        input.setId(12l);
        input.setRecipeNotes("recipe notes...");

        NotesCommand output = notesToCommand.convert(input);

        assertNotNull(output);
        assertEquals(input.getId(), output.getId());
        assertEquals(input.getRecipeNotes(), output.getRecipeNotes());
    }
}