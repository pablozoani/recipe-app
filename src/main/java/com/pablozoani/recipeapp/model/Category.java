package com.pablozoani.recipeapp.model;

import java.util.HashSet;
import java.util.Set;

public class Category {

    // == fields ==
    private String categoryName;

    // == relationships ==
    private Set<Recipe> recipes = new HashSet<>();
}