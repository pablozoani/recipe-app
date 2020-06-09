package com.pablozoani.recipeapp.model;

import java.util.HashSet;
import java.util.Set;

public class Recipe {

    private Difficulty difficulty;
    private Notes notes;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Set<Category> categories = new HashSet<>();
}
