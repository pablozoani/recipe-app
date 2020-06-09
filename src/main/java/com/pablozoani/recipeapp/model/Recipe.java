package com.pablozoani.recipeapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe {

    // == fields ==

    @Id @Getter
    @GeneratedValue(generator = "native")
    protected Long id;

    @Getter @Setter
    @Column(name = "description", nullable = false)
    protected String description;

    @Getter @Setter
    @Column(name = "preparation_time", nullable = false)
    protected String prepTime;

    @Getter @Setter
    @Column(name = "cook_time")
    protected String cookTime;

    @Getter @Setter
    @Column(name = "servings")
    protected Integer servings;

    @Getter @Setter
    @Column(name = "source")
    protected String source;

    @Getter @Setter
    @Column(name = "url")
    protected String url;

    @Getter @Setter
    @Column(name = "directions", nullable = false)
    protected String directions;

    @Getter @Setter
    @Column(name = "difficulty", nullable = false)
    @Enumerated(EnumType.STRING)
    protected Difficulty difficulty;

    @Getter @Setter
    @Column(name = "image")
    protected Byte[] image;

    // == relationships ==

    protected Notes notes;
    protected Set<Ingredient> ingredients = new HashSet<>();
    protected Set<Category> categories = new HashSet<>();
}
