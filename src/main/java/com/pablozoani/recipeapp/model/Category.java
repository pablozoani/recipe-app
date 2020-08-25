package com.pablozoani.recipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@NoArgsConstructor
@Document
public class Category {

    // == fields ==

    @Id
    protected String id;

    protected String categoryName;

    // == relationships ==

    @DBRef
    protected Set<Recipe> recipes = new HashSet<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}