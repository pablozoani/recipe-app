package com.pablozoani.recipeapp.converter.fromcommand;

import com.pablozoani.recipeapp.command.NotesCommand;
import com.pablozoani.recipeapp.model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandToNotesTest {

    CommandToNotes commandToNotes;

    @BeforeEach
    void setUp() {
        commandToNotes = new CommandToNotes();
    }

    @Test
    void testNullObject() {
        assertNull(commandToNotes.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(commandToNotes.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        NotesCommand input = new NotesCommand();
        input.setId("abc");
        input.setRecipeNotes("notes...");

        Notes output = commandToNotes.convert(input);

        assertNotNull(output);
        assertEquals("abc", output.getId());
        assertEquals("notes...", output.getRecipeNotes());
    }
}