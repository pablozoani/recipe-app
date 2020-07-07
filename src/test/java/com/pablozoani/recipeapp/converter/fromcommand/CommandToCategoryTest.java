package com.pablozoani.recipeapp.converter.fromcommand;

import com.pablozoani.recipeapp.command.CategoryCommand;
import com.pablozoani.recipeapp.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandToCategoryTest {

    private CommandToCategory commandToCategory;

    @BeforeEach
    void setUp() {
        commandToCategory = new CommandToCategory();
    }

    @Test
    void testNullObject() {
        assertNull(commandToCategory.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(commandToCategory.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(12L);
        categoryCommand.setCategoryName("Italian");

        Category output = commandToCategory.convert(categoryCommand);

        assertEquals(12L, output.getId());
        assertEquals("Italian", output.getCategoryName());
    }
}