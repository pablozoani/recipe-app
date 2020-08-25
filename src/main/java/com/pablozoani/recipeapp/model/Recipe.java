package com.pablozoani.recipeapp.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Document
public class Recipe {

    // == fields ==

    @Id
    protected String id;

    protected String description;

    protected String prepTime;

    protected String cookTime;

    protected Integer servings;

    protected String source;

    protected String url;

    protected String directions;

    protected Difficulty difficulty;

    protected Byte[] image;

    // == relationships ==

    protected Notes notes;

    protected Set<Ingredient> ingredients = new HashSet<>();

    @DBRef
    protected Set<Category> categories = new HashSet<>();

    public Recipe setNotes(@NonNull Notes notes) {
        if (notes.getRecipe() != null) {
            throw new RuntimeException("this note belongs to a recipe");
        }
        this.notes = notes;
        //notes.setRecipe(this);
        return this;
    }

    public Recipe addIngredients(Ingredient... ingredients) {
        for (Ingredient ingredient : ingredients) {
            if (!(ingredient.getRecipe() == null)) {
                throw new RuntimeException("ingredient already belongs to a recipe");
            }
            //ingredient.setRecipe(this);
            this.ingredients.add(ingredient);
        }
        return this;
    }

    public Recipe deleteIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
        ingredient.setRecipe(null);
        return this;
    }

    public Recipe addCategories(Category... categories) {
        for (Category category : categories) {
            this.categories.add(category);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Recipe{" +
               "id=" + id +
               ", description='" + description + '\'' +
               ", prepTime='" + prepTime + '\'' +
               ", cookTime='" + cookTime + '\'' +
               ", servings=" + servings +
               ", source='" + source + '\'' +
               ", url='" + url + '\'' +
               ", difficulty=" + difficulty +
               '}';
    }
}
