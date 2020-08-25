package com.pablozoani.recipeapp.command;

import com.pablozoani.recipeapp.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private List<CategoryCommand> categories = new ArrayList<>();

    @Min(1)
    @Max(999)
    private String cookTime;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    private Difficulty difficulty;

    private String directions;

    private String id;

    private List<IngredientCommand> ingredients = new ArrayList<>();

    private NotesCommand notes;

    @Min(1)
    @Max(999)
    private String prepTime;

    @Min(1)
    @Max(100)
    private Integer servings;

    private String source;

    @URL
    @NotBlank
    private String url;

    private Byte[] image;
}
