package com.pablozoani.recipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {

    // == fields ==

    @Id
    protected String id;

    protected String recipeNotes;

    // == relationships ==

    protected Recipe recipe;

    public Notes setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
        return this;
    }
}
