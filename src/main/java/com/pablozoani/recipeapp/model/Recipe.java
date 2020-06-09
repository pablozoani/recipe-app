package com.pablozoani.recipeapp.model;

import java.util.HashSet;
import java.util.Set;

public class Recipe {

    // == fields ==
    private String description;
    private String prepTime;
    private String cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private Byte[] image;

    // == relationships ==
    private Notes notes;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Set<Category> categories = new HashSet<>();
}
