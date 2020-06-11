package com.pablozoani.recipeapp.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "recipe")
public class Recipe {

    // == fields ==

    @Id
    @GeneratedValue(generator = "native")
    protected Long id;

    @Column(nullable = false)
    protected String description;

    @Column(name = "preparation_time", nullable = false)
    protected String prepTime;

    @Column(name = "cook_time")
    protected String cookTime;

    protected Integer servings;

    protected String source;

    protected String url;

    @Lob
    @Column(nullable = false)
    protected String directions;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Difficulty difficulty;

    @Lob
    protected Byte[] image;

    // == relationships ==

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Notes notes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "recipe")
    protected Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_category",
               joinColumns = @JoinColumn(name = "recipe_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    protected Set<Category> categories = new HashSet<>();

    public Recipe setNotes(@NonNull Notes notes) {
        if (notes.getRecipe() != null) {
            throw new RuntimeException("this note belongs to a recipe");
        }
        this.notes = notes;
        notes.setRecipe(this);
        return this;
    }

    public Recipe addIngredients(Ingredient... ingredients) {
        for (Ingredient ingredient : ingredients) {
            if (!(ingredient.getRecipe() == null)) {
                throw new RuntimeException("ingredient already belongs to a recipe");
            }
            ingredient.setRecipe(this);
            this.ingredients.add(ingredient);
        }
        return this;
    }

    public Recipe addCategories(Category... categories) {
        for (Category category : categories) {
            this.categories.add(category);
        }
        return this;
    }
}
