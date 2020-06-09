package com.pablozoani.recipeapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    // == fields ==
    private String categoryName;

    // == relationships ==
    private Set<Recipe> recipes = new HashSet<>();
}
