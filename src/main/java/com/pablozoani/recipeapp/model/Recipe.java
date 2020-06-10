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
    @Column(nullable = false)
    protected String description;

    @Getter @Setter
    @Column(name = "preparation_time", nullable = false)
    protected String prepTime;

    @Getter @Setter
    @Column(name = "cook_time")
    protected String cookTime;

    @Getter @Setter
    protected Integer servings;

    @Getter @Setter
    protected String source;

    @Getter @Setter
    protected String url;

    @Getter @Setter @Lob
    @Column(nullable = false)
    protected String directions;

    @Getter @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Difficulty difficulty;

    @Getter @Setter @Lob
    protected Byte[] image;

    // == relationships ==

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Notes notes;

    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "recipe")
    protected Set<Ingredient> ingredients = new HashSet<>();

    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_category",
               joinColumns = @JoinColumn(name = "recipe_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    protected Set<Category> categories = new HashSet<>();

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
