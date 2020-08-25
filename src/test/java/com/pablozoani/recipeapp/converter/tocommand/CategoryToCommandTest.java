package com.pablozoani.recipeapp.converter.tocommand;

import com.pablozoani.recipeapp.command.CategoryCommand;
import com.pablozoani.recipeapp.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCommandTest {

    CategoryToCommand categoryToCommand;

    @BeforeEach
    void setUp() {
        categoryToCommand = new CategoryToCommand();
    }

    @Test
    void testNullObject() {
        assertNull(categoryToCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(categoryToCommand.convert(new Category()));
    }

    @Test
    void convert() {
        Category input = new Category();
        input.setId("43l");
        input.setCategoryName("category name...");

        CategoryCommand output = categoryToCommand.convert(input);

        assertNotNull(output);
        assertEquals(input.getId(), output.getId());
        assertEquals(input.getCategoryName(), output.getCategoryName());
    }
}