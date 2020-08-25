package com.pablozoani.recipeapp.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getId() {
        String idValue = "4L";
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    void getCategoryName() {
    }

    @Test
    void getRecipes() {
    }
}