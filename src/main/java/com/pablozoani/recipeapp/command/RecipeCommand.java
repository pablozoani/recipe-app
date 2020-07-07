package com.pablozoani.recipeapp.command;

import com.pablozoani.recipeapp.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Set<CategoryCommand> categories = new HashSet<>();

    private String cookTime;

    private String description;

    private Difficulty difficulty;

    private String directions;

    private Long id;

    private Set<IngredientCommand> ingredients = new HashSet<>();

    private NotesCommand notes;

    private String prepTime;

    private Integer servings;

    private String source;

    private String url;
}
