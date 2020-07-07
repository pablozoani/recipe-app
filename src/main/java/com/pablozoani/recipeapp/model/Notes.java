package com.pablozoani.recipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@Table(name = "notes")
public class Notes {

    // == fields ==

    @Id
    @GeneratedValue(generator = "native")
    protected Long id;

    @Lob
    @Column(name = "recipe_notes", nullable = false)
    protected String recipeNotes;

    // == relationships ==

    @OneToOne(mappedBy = "notes")
    protected Recipe recipe;

    public Notes setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
        return this;
    }
}
